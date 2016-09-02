package parser.publio;

import mapper.Mapper;
import parser.PageLoader;
import parser.PromotionLibrary;

public class PublioPromotionLibrary extends PromotionLibrary {

    public PublioPromotionLibrary(PageLoader pageLoader, Mapper genreMapper, Mapper tagMapper) {
        super(new PublioUrlCrawler(pageLoader),new PublioBookCrawler(pageLoader));
        this.genreMapper = genreMapper;
        this.tagMapper = tagMapper;
    }

}