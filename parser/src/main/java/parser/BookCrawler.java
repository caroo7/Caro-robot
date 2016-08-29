package parser;

import parser.DTO.Book;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Grzesiek on 2016-08-28.
 */
public abstract class BookCrawler {

    private final IBookParser bookParser;
    private final PageLoader pageLoader;

    public BookCrawler(PageLoader pageLoader,IBookParser bookParser){
        this.pageLoader=pageLoader;
        this.bookParser=bookParser;
    }

    public List<Book> getBooks(List<String> urlsToBookDetails) {
        return urlsToBookDetails.parallelStream().map(s -> bookParser.parse(pageLoader.getPage(s))).collect(Collectors.toList());
    }
}
