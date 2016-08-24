package repositories;

import config.DatabaseConfiguration;
import entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@ContextConfiguration(classes = {DatabaseConfiguration.class})
public class BookRepositoryTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private BookRepository repo;

    @Test
    @Transactional
    public void retrievingBookTest() {
        Book book = new Book("title", "author", "description");

        repo.save(book);

        assertEquals(repo.findAll().size(), 1);
    }

}
