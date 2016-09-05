package cache;

import access.DBAccessor;
import entities.Book;
import entities.Library;
import lombok.extern.log4j.Log4j2;
import repositories.BookRepository;

import java.util.List;

@Log4j2
public class Cache {

    public void initializeCache(BookRepository bookRepo, Library library) {
        log.info("Cache creation initialized");
        DBAccessor dbAccessor = new DBAccessor();
        List<Book> bookList = dbAccessor.getBooks(bookRepo, library);
        dbAccessor.createCache(bookList, library.getName());
        log.info("Cache created");
    }

}