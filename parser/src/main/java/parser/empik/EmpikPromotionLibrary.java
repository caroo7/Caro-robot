package parser.empik;

import mapper.Mapper;
import parser.PageLoader;
import parser.PromotionLibrary;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class EmpikPromotionLibrary extends PromotionLibrary{

    public EmpikPromotionLibrary(PageLoader pageLoader, Mapper genreMapper, Mapper tagMapper) {
        super(new EmpikUrlCrawler(pageLoader),new EmpikBookCrawler(pageLoader));
        this.genreMapper = genreMapper;
        this.tagMapper = tagMapper;
    }

}
