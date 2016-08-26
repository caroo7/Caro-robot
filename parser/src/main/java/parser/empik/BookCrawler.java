package parser.empik;

import parser.DTO.Book;
import parser.IBookParser;
import parser.PageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class BookCrawler {

    private final IBookParser bookParser=new BookParser();
    private final PageLoader pageLoader=new PageLoader();
    private final UrlCrawler urlCrowler=new UrlCrawler();

    public List<Book> getBooks() {

        ArrayList<Book> books=new ArrayList<>();

        List<String> urlsToBookDetails=urlCrowler.prepareLinksToAllBooks();
        for(String url: urlsToBookDetails){
            Book book= bookParser.parse(pageLoader.getPage(url));
            books.add(book);
        }
        return books;
    }
}
