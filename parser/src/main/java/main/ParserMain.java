package main;

import parser.DTO.Book;
import parser.PageLoader;
import parser.PromotionLibrary;
import parser.empik.EmpikPromotionLibrary;

import java.util.List;

public class ParserMain {

    public static void main(String[] args)  {
        List<Book> books;

        PageLoader pageLoader=new PageLoader();
        PromotionLibrary empik=new EmpikPromotionLibrary(pageLoader);

        books=empik.collect();
        books.stream().forEach(book -> System.out.println(book));
        System.out.println(books.size());

}

}