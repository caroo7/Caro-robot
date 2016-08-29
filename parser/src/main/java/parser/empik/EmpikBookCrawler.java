package parser.empik;

import parser.BookCrawler;
import parser.PageLoader;

/**
 * Created by grzegorz_sledz on 25.08.16.
 */
public class EmpikBookCrawler extends BookCrawler {

    public EmpikBookCrawler(PageLoader pageLoader){
        super(pageLoader,new EmpikBookParser());
    }

}
