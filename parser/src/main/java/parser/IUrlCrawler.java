package parser;

import java.util.List;

@FunctionalInterface
public interface IUrlCrawler {
     List<String> getLinksToAllBooks();
}
