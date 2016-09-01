package access;

import config.DatabaseConfiguration;
import entities.Book;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.BookRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;


@Log4j2
public class DBAccessor {


    public List<Book> getBooks() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(DatabaseConfiguration.class);
        BookRepository repo = ctx.getBean(BookRepository.class);
        List<Book> books = repo.findAll();
        return books;
    }

    public void createCache(List<Book> list) {
        try {
            FileOutputStream outputStream = new FileOutputStream("cache.txt");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(list);
        } catch (FileNotFoundException fNFE) {
            log.error("Exception while accessing the file: " + fNFE);
        } catch (IOException iOE) {
            log.error("Exception while streaming the objects: " + iOE);
        }
    }
}


