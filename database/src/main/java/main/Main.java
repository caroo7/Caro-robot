package main;

import config.DatabaseConfiguration;
import entities.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookRepository;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository repo = ctx.getBean(BookRepository.class);

        Book book = repo.findAll().get(0);
        int a = 10;
    }

}
