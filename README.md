# Web Crawler
A Multithreaded Java Web Crawler Application

- Given the start URL and Maximum depth
- Will crawl the child URLs asynchronously and store the List of unique URLs and out is stored in log file.

## Steps to Run

- mvn install 
- mvn clean compile assembly:single
- java -cp target/webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar com.monzo.webcrawler.WebCrawlerApplication <url> <maximum depth>
- eg: java -cp target/webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar com.monzo.webcrawler.WebCrawlerApplication https://monzo.com 3
- default url : https://monzo.com, default depth: 1

## Run Test

- mvn test
- Will create target/jacoco.exe

## Show test coverage in intellij

- Running test will create target/jacoco.exec
- Run -> Show Coverage Data --> select the jacoco.exec file

## Logs

- Log Location : logs/crawler.log
- Append set to false in log4j.properties, can be enabled to track all the crawling

## TradeOffs

- Considering the URLs starting with the base URL of the given start URL.
- Considered Maximum depth into consideration , Otherwise will be running for long time.
- Ignored interrupted and timeout calls to URLs while visiting.
- No Pause time specified for the future list empty check.
- Default executor service thread count is set to 4 (Considering URLs starting with a base URL and a maximum depth, 4 threads will be good enough).

## Future Scope

- Number of thread counts of executor service can be configurable.
- Can give maximum unique urls count a configurable one along with maximum depth.
- Can implement a Manager for processors to execute multiple domain crawls in parallel.
- Can be exposed as APIs using spring MVC architecture and integrate to a front end.
- Can write the output to a File in a specified format.
