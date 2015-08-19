package com.minhhop.crawler

import com.minhhop.crawler.Crawler.Kind
import com.minhhop.crawler.Crawler.Proxy;

/**
 * @author Giang Le
 */
abstract class JCrawler implements Crawler {
    List<EventLog> log = new ArrayList<EventLog>()

    public static Crawler setup (Kind kind, Proxy proxy) {
        if (kind == Crawler.Kind.WEBCLIENT) {
            return new WebClientBot().setup(proxy);
        }
        return new WebDriverBot().setup(proxy);
    }

    abstract Crawler setup(Proxy proxy);

    public def addEvent(EventLevel level, String msg) {
        log << new EventLog(level: level, date: new Date(), msg: msg)
    }
    public def printLog() {
        log.each {
            println it
        }
    }
}
