package repositories;

import config.DatabaseConfiguration;
import entities.Book;
import entities.Genre;
import entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.*;

@ContextConfiguration(classes = {DatabaseConfiguration.class})
public class BookRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BookRepository repo;

    @Test
    @Transactional
    public void retrievingBookTest() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("nowosc"));
        tags.add(new Tag("bestseller"));

        Set<Genre> genres = new HashSet<>();
        genres.add(new Genre("prawnicza"));
        genres.add(new Genre("sensacja"));

        Book book = new Book("Firma", "John Grisham", "Swietna ksiazka", "30%", "29zl", new Timestamp(System.currentTimeMillis()), tags, genres);
        repo.save(book);

        assertEquals(repo.findAll().size(), 1);
    }

}
