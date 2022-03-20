# Web Crawler
A Web Crawler Application

## Steps to Run

- mvn install 
- mvn clean compile assembly:single
- java -cp target/webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar com.monzo.webcrawler.WebCrawlerApplication <url> <maximum depth>
- eg: java -cp target/webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar com.monzo.webcrawler.WebCrawlerApplication https://monzo.com 3
- default url : https://monzo.com, default depth: 3
- Run test case : mvn test

## TradeOffs

## Future Scope


