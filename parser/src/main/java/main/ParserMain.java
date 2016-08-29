package main;

import config.DatabaseConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import parser.DTO.Book;
import parser.PageLoader;
import parser.PromotionLibrary;
import parser.publio.PublioPromotionLibrary;

import java.util.List;

public class ParserMain {

    public static void main(String[] args) {

        PromotionLibrary publio=new PublioPromotionLibrary(new PageLoader());
        List<Book> books=publio.collect();
        books.stream().forEach(book -> System.out.println(book));

        System.out.println(books.size());
//        new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
    }

}