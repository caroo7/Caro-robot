package cache;

import access.DBAccessor;
import entities.Book;
import entities.Library;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import repositories.BookRepository;

import java.util.Set;

@Log4j2
public class Cache {

    @Autowired
    private DBAccessor dbAccessor;

    public void initializeCache(Library library, BookRepository bookRepo) {
        log.info("Cache creation initialized");
        Set<Book> bookList = dbAccessor.getBooks( bookRepo,library);
        dbAccessor.createCache(bookList, library.getName());
        log.info("Cache created");
    }

}