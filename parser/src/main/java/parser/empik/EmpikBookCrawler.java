package parser.empik;

import parser.BookCrawler;
import parser.PageLoader;

class EmpikBookCrawler extends BookCrawler {

    EmpikBookCrawler(PageLoader pageLoader){
        super(pageLoader,new EmpikBookParser());
    }

}
