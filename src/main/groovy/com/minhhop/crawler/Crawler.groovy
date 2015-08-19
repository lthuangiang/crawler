package com.minhhop.crawler

/**
 * <p>A Web crawler is an Internet bot which systematically browses the World Wide Web, typically for the purpose of Web indexing. A Web crawler may also be called a Web spider,[1] an ant, an automatic indexer,[2] or (in the FOAF software context) a Web scutter</p>
 * <p>This is how you start using Crawler with HtmlUnit or Selenium
 *
 * <pre> JCrawler crawler = JCrawler.setup(Crawler.Kind.WEBCLIENT, null);
 *       crawler.go "http://google.com"
 * </pre>
 *
 * <p>The interfaces in this packages are trying to cover as much as possible of HtmlUnit/Selenium</p>
 *
 * @author Giang Le (lthuangiang@gmail.com)
 * @version 0.1
 */
interface Crawler {
    def quit();
    def go (String url);
    def sleep (long time);
    def get(String xpath);
    def click (String xpath);
    def submit(String xpath);
    def javascript(boolean js);
    def printLog();
    def url();

    enum Kind {
        WEBDRIVER,
        WEBCLIENT
    }

    public final class Proxy {
        int port
        String address
    }
}