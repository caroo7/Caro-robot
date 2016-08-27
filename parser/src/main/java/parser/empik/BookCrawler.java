package parser.empik;

import parser.DTO.Book;
import parser.IBookParser;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class BookCrawler {

    private final IBookParser bookParser;
    private final PageLoader pageLoader;

    public BookCrawler(PageLoader pageLoader){
        this.pageLoader=pageLoader;
        this.bookParser=new BookParser();
    }

    public List<Book> getBooks(List<String> urlsToBookDetails) {
        return urlsToBookDetails.parallelStream().map(s -> bookParser.parse(pageLoader.getPage(s))).collect(Collectors.toList());
    }
}
