package com.minhhop.crawler

import com.gargoylesoftware.htmlunit.html.HtmlOption
import com.gargoylesoftware.htmlunit.html.HtmlSelect
import org.junit.Test

class CrawlerBotTest {
    @Test
    public void crawl() {
        JCrawler crawler = JCrawler.setup(Crawler.Kind.WEBCLIENT, null);
        crawler.go "http://kissanime.com/Anime/Gunslinger-Girl-Il-Teatrino-Dub/Episode-013?id=61365"
        crawler.sleep(5000)
        crawler.submit("//*[@id='challenge-form']")
        if (!crawler.url().equals("http://kissanime.com/Anime/Gunslinger-Girl-Il-Teatrino-Dub/Episode-013?id=61365")) {
            crawler.go "http://kissanime.com/Anime/Gunslinger-Girl-Il-Teatrino-Dub/Episode-013?id=61365"
        }
        HtmlSelect select = crawler.get("//*[@id='selectQuality']")
        if (select) {
            for (HtmlOption option : select.getOptions()) {
                if ("1080p".equals(option.text)) {
                    println "1080p: " + option.getValueAttribute()
                } else if ("720p".equals(option.text)) {
                    println "720p: " + option.getValueAttribute()
                } else {
                    println option.getValueAttribute()
                }
            }
        }
    }
}
