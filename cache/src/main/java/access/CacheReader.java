package access;

import entities.Book;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.Set;

@Log4j2
public class CacheReader {

    public Set<Book> getBooksFromCache(String libName) {

        Set<Book> books = new HashSet<>();
        try (FileInputStream fileInputStream = new FileInputStream(libName)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            books = (Set<Book>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException e) {
            log.error("Can't access file: " +e);
        } catch (ClassNotFoundException e) {
            log.error("Class not found: " + e);
        }
        return books;
    }
}
