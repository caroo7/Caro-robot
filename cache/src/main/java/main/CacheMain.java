package main;

import access.DBAccessor;
import entities.Book;
import lombok.extern.log4j.Log4j2;

import java.util.List;

/**
 * Created by bartlomiej on 31.08.16.
 */
@Log4j2
public class CacheMain {


    public static void main(String[] args) {
        log.info("Cache creation initialized");
        DBAccessor dbAccessor = new DBAccessor();
        List<Book> bookList = dbAccessor.getBooks();
        dbAccessor.createCache(bookList);
        log.info("Cache created");

    }


}

