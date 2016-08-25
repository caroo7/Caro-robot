package main;

import config.DatabaseConfiguration;
import entities.Book;
import entities.Genre;
import entities.Library;
import entities.Tag;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookRepository;

import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class ParserMain {

    public static void main(String[] args) {

        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository repo = ctx.getBean(BookRepository.class);

        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag("nowosc"));
        tags.add(new Tag("bestseller"));

        List<Genre> genres = new ArrayList<>();
        genres.add(new Genre("prawnicza"));
        genres.add(new Genre("sensacja"));

        Book book = new Book("Firma", "John Grisham", "Swietna ksiazka", "30%", "29zl", new Timestamp(System.currentTimeMillis()), tags, genres);
        repo.save(book);

        List<Book> books = new ArrayList<>();
        books.add(book);
        Library lib = new Library("Library", "test", books);



        int a = 10;

    }

}