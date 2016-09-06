package cache;

import access.DBAccessor;
import entities.Book;
import lombok.extern.log4j.Log4j2;
import repositories.BookRepository;

import java.util.List;

@Log4j2
public class Cache {

    public void initializeCache(BookRepository bookRepo, String libName) {
        log.info("Cache creation initialized");
        DBAccessor dbAccessor = new DBAccessor();
        List<Book> bookList = dbAccessor.getBooks(bookRepo);
        dbAccessor.createCache(bookList, libName);
        log.info("Cache created");
    }

}