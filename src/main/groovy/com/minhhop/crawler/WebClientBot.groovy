package com.minhhop.crawler

import com.gargoylesoftware.htmlunit.BrowserVersion
import com.gargoylesoftware.htmlunit.Page
import com.gargoylesoftware.htmlunit.RefreshHandler
import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlButton
import com.gargoylesoftware.htmlunit.html.HtmlElement
import com.gargoylesoftware.htmlunit.html.HtmlForm
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.minhhop.crawler.Crawler.Proxy;

class WebClientBot extends JCrawler {
    private WebClient webClient
    HtmlPage page

    @Override
    def go(String url) {
        page = webClient.getPage(url)
        addEvent(EventLevel.DEBUG, "current page ${page?.url}")
    }

    @Override
    def sleep(long time) {
        try {
            Thread.sleep(time)
        } catch (InterruptedException ex1) {
            addEvent(EventLevel.ERROR, "ex msg: ${ex1.message}")
        }
    }

    @Override
    def get(String xpath) {
        HtmlElement el = page.getFirstByXPath(xpath)
        if (el) {
            return el
        } else {
            addEvent(EventLevel.ERROR, "cannot find element for $xpath url ${page.url.toString()}");
        }
    }

    @Override
    def click(String xpath) {
        page = ((HtmlElement) get(xpath)).click();
    }

    @Override
    def submit(String xpath) {
        HtmlForm form
        if (xpath == null) {
            form = page.getForms()[0]
        } else {
            form = page.getFirstByXPath(xpath)
        }
        if (form == null) {
            throw new IllegalStateException("The from ${xpath} does not exist!")
        }
        HtmlButton submitButton = (HtmlButton) page.createElement("button")
        submitButton.setAttribute "type", "submit";
        form.appendChild submitButton;
        addEvent(EventLevel.DEBUG, "create submit button for form ${form}")
        page = submitButton.click();
        addEvent(EventLevel.DEBUG, "current page ${page?.url}")
    }

    @Override
    def javascript(boolean js) {
        webClient.options.setJavaScriptEnabled(js)
    }

    @Override
    def url() {
        return page.url.toString();
    }

    def quit() {
        webClient?.closeAllWindows()
        webClient = null
    }

    @Override
    Crawler setup(Proxy proxy) {
        webClient = (proxy) ? new WebClient(BrowserVersion.FIREFOX_38, proxy.address, proxy.port) : new WebClient(BrowserVersion.FIREFOX_38)
        webClient.options.setJavaScriptEnabled(false)
        webClient.options.setThrowExceptionOnFailingStatusCode(false)
        webClient.options.setThrowExceptionOnScriptError(false)
        webClient.options.setUseInsecureSSL(true)
        webClient.options.setCssEnabled(true)
        webClient.options.setRedirectEnabled(true)
        webClient.options.setTimeout(60000 * 3)
        webClient.waitForBackgroundJavaScript(20000L)
        webClient.setJavaScriptTimeout(60000)
        webClient.setRefreshHandler(new RefreshHandler() {
            public void handleRefresh(Page page, URL url, int arg) throws IOException {
                // Ignore refresh page $page == URL $url
            }
        });
        return this;
    }
}
