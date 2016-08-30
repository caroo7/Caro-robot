package parser;

import DTO.BookDetails;
import mapper.Mapper;

import java.util.List;
import java.util.Set;

/**
 * Created by Grzesiek on 2016-08-28.
 */
public abstract class PromotionLibrary {
    private IUrlCrawler urlCrawler;
    private BookCrawler bookCrawler;

    protected Mapper genreMapper;
    protected Mapper tagMapper;

    public PromotionLibrary(IUrlCrawler urlCrawler,BookCrawler bookCrawler){
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
