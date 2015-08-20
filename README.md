# Web Crawler with HtmlUnit/Selenium
<img src="http://www.pixelroaddesigns.com/wrdp/wp-content/uploads/2013/05/6-7-2013-1-35-19-PM-300x300.jpg" />

A Web crawler is an Internet bot which systematically browses the World Wide Web, typically for the purpose of Web indexing. A Web crawler may also be called a Web spider,[1] an ant, an automatic indexer,[2] or (in the FOAF software context) a Web scutter

Set of classes in `com.minhhop.crawler` package is
an object oriented of Crawler which try to cover as much as possible of HtmlUnit/Selenium


```java
public class Main {
  public static void main(String[] args) {
    Crawler crawler = JCrawler.setup(Crawler.Kind.WEBCLIENT, null);
    crawler.go ("http://google.com");
  }
}
```

# How to use (Maven)
- Add the following snippet to any project's pom that depends on your project
```xml
  <repository>
            <id>crawler-mvn-repo</id>
            <url>https://raw.github.com/lthuangiang/crawler/mvn-repo/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
  </repository>
```
- The dependency you need is
```xml
  <dependency>
            <groupId>com.minhhop.crawler</groupId>
            <artifactId>cralwer</artifactId>
            <version>1.2-SNAPSHOT</version>
  </dependency>
```
SYSTEM REQUIREMENTS
-------------------

Reucon-Commons needs a Java Virtual Machine of at least version 1.7 ([Java SE 7.0](http://www.oracle.com/technetwork/java/javase/downloads/index.html)).
If you want to build the jar from source, you will also need [Maven](http://maven.apache.org/).

