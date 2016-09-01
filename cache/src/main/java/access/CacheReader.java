package access;

import entities.Book;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CacheReader {

    public List<Book> getBooksFromCache() {

        List<Book> books = new ArrayList<>();
        try (FileInputStream fileInputStream = new FileInputStream("cache.txt")) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            books = (List<Book>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            log.error("Can't access file: " +e);
        } catch (ClassNotFoundException e) {
            log.error("Class not found: " + e);
        }
        return books;
    }
}
