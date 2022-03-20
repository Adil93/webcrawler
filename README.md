# Web Crawler
A Java MultiThreaded Web Crawler Application

- Given the start URL, The application will crawl all the child URLs and its recursive URLs asynchronously and store the list of unique URLs and out is stored in a log file.
- Made use of Jsoup Library for HTML parsing
- Have added a max depth variable, to prevent the program running for a long time.

## Steps to Run
From the project root folder, Execute below steps:
- mvn install 
- mvn clean compile assembly:single
- java -cp target/webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar com.monzo.webcrawler.WebCrawlerApplication {url} {maxDepth}
- eg: java -cp target/webcrawler-1.0-SNAPSHOT-jar-with-dependencies.jar com.monzo.webcrawler.WebCrawlerApplication https://monzo.com 3
- Default URL : https://monzo.com, Default depth: 2147483647 (Integer.MAX_VALUE)

## Run Test

- mvn test
- Will create target/jacoco.exe

## Show test coverage in intellij

- Running test will create target/jacoco.exec
- Run -> Show Coverage Data --> select the jacoco.exec file
- The Application has more than 90 percent test coverage.

## Logs

- Log Location : logs/crawler.log
- Append set to false in log4j.properties, can be enabled to track all the crawling.

## TradeOffs

- Ignored interrupted and timeout calls to URLs while visiting.
- No Pause time specified for the future list empty check.
- Default executor service thread count is set to 4 (Considering URLs starting with a base URL and a maximum depth, 4 threads will be good enough).
- Considered <url>, <url>/ and <url>/# the same, Only the <url> will be stored in the list.
- Not fetching URLs ending with .pdf extensions.

## Future Scope

- Number of thread counts of executor service can be configurable.
- Can give maximum unique urls count a configurable one along with maximum depth.
- Can implement a Manager for processors to execute multiple domain crawls in parallel.
- Can be exposed as APIs using spring MVC architecture and integrate to a front end.
- Can write the output to a File in a specified format.
- If we want to run the crawling more than the Integer.MAX_VALUE depth, we can skip the depth check, if depth is not specified.
