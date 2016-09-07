package repositories;

import config.DatabaseConfiguration;
import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertEquals;

// integration tests
@ContextConfiguration(classes = {DatabaseConfiguration.class})
@Transactional
public class RepositoriesTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private GenreRepository genreRepo;

    @Autowired
    private AuthorRepository authorRepo;

    @Autowired
    private TagRepository tagRepo;

    private Book book;

    @BeforeMethod
    public void setUp() {
        Tag tag = new Tag("nowosc");
        Tag tag2 = new Tag("bestseller");
        tagRepo.save(tag);
        tagRepo.save(tag2);

        Set<Tag> tags = new HashSet<>();
        tags.add(tag);
        tags.add(tag2);

        Genre genre = new Genre("prawnicza");
        Genre genre2 = new Genre("sensacja");
        genreRepo.save(genre);
        genreRepo.save(genre2);

        Set<Genre> genres = new HashSet<>();
        genres.add(genre);
        genres.add(genre2);

        Author author = new Author("Grisham John");
        authorRepo.save(author);
        Set<Author> authors = new HashSet<>(Collections.singletonList(author));

        book = Book.builder().title("Firma").authors(authors).description("Swietna ksiazka").discount("30%").
                price("29zl").timestamp(new Timestamp(System.currentTimeMillis())).tags(tags).genres(genres).build();
    }

    @AfterMethod
    public void clear() {
        bookRepo.deleteAll();
    }

    @Test
    public void retrievingEmptyBooksListTest() {
        assertEquals(bookRepo.findAll().size(), 0);
    }

    @Test
    public void retrievingNotEmptyBooksListTest() {
        bookRepo.save(book);
        assertEquals(bookRepo.findAll().size(), 1);
    }

    @Test
    public void saveAndDeleteBookTest() {
        bookRepo.save(book);
        assertEquals(bookRepo.findAll().size(), 1);
        bookRepo.delete(book);
        assertEquals(bookRepo.findAll().size(), 0);
    }

}