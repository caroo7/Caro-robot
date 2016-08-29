package parser;

import parser.DTO.Book;

import java.util.List;

/**
 * Created by Grzesiek on 2016-08-28.
 */
public abstract class PromotionLibrary {
    private IUrlCrawler urlCrawler;
    private BookCrawler bookCrawler;

    public PromotionLibrary(IUrlCrawler urlCrawler,BookCrawler bookCrawler){
        this.urlCrawler=urlCrawler;
        this.bookCrawler=bookCrawler;
    }

    public List<Book> collect(){
        return bookCrawler.getBooks(urlCrawler.getLinksToAllBooks());
    }

}
