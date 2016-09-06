package parser;

import DTO.BookDetails;
import mapper.Mapper;

import java.util.Set;

public abstract class PromotionLibrary {
    private UrlCrawler urlCrawler;
    private BookCrawler bookCrawler;

    protected Mapper genreMapper;
    protected Mapper tagMapper;

    public PromotionLibrary(UrlCrawler urlCrawler, BookCrawler bookCrawler){
        this.urlCrawler=urlCrawler;
        this.bookCrawler=bookCrawler;
    }

    public Set<BookDetails> collect(){
        return bookCrawler.getBooks(urlCrawler.getLinksToAllBooks());
    }

    public Mapper getGenreMapper() {
        return genreMapper;
    }

    public Mapper getTagMapper() {
        return tagMapper;
    }

}
