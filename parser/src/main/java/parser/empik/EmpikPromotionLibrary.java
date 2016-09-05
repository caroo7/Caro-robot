package parser.empik;

import mapper.Mapper;
import parser.PageLoader;
import parser.PromotionLibrary;


public class EmpikPromotionLibrary extends PromotionLibrary{

    public EmpikPromotionLibrary(PageLoader pageLoader, Mapper genreMapper, Mapper tagMapper) {
        super(new EmpikUrlCrawler(pageLoader),new EmpikBookCrawler(pageLoader));
        this.genreMapper = genreMapper;
        this.tagMapper = tagMapper;
    }

}
