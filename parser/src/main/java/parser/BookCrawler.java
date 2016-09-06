package parser;

import DTO.BookDetails;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BookCrawler {

    private final IBookParser bookParser;
    private final PageLoader pageLoader;

    public BookCrawler(PageLoader pageLoader,IBookParser bookParser){
        this.pageLoader=pageLoader;
        this.bookParser=bookParser;
    }

    public Set<BookDetails> getBooks(List<String> urlsToBookDetails) {
        return urlsToBookDetails.parallelStream().map(s -> bookParser.parse(pageLoader.getPage(s))).collect(Collectors.toSet());
    }
}
