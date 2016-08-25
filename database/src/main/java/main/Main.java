package main;

import config.DatabaseConfiguration;
import entities.Book;
import entities.Genre;
import entities.Library;
import entities.Tag;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookRepository;
import repositories.GenreRepository;
import repositories.LibraryRepository;
import repositories.TagRepository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository repoBook = ctx.getBean(BookRepository.class);
        LibraryRepository repoLibrary = ctx.getBean(LibraryRepository.class);
        TagRepository repoTag = ctx.getBean(TagRepository.class);
        GenreRepository repoGenre = ctx.getBean(GenreRepository.class);

        /*Tag tag1 = new Tag("nowosc");
        Tag tag2 = new Tag("bestseller");
        repoTag.save(tag1);
        repoTag.save(tag2);

        List<Tag> tags = new ArrayList<>();
        tags.add(tag1);
        tags.add(tag2);

        Genre genre1 = new Genre("prawnicza");
        Genre genre2 = new Genre("sensacja");
        repoGenre.save(genre1);
        repoGenre.save(genre2);

        List<Genre> genres = new ArrayList<>();
        genres.add(genre1);
        genres.add(genre2);

        Book book = new Book("Firma", "John Grisham", "Swietna ksiazka", "30%", "29zl", new Timestamp(System.currentTimeMillis()), tags, genres);
        repoBook.save(book);

        List<Book> books = new ArrayList<>();
        books.add(book);
        Library lib = new Library("Library", "test", books);
        repoLibrary.save(lib);*/

        List<Book> books = repoBook.findAll();
        List<Library> libraries = repoLibrary.findAll();

        Set<Tag> tags = books.get(0).getTags();
        Set<Genre> genres = books.get(0).getGenres();
        System.out.println(genres);

        int a = 10;
    }

}