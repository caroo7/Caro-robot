package access;

import entities.Book;
import lombok.extern.log4j.Log4j2;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Set;
import java.util.stream.Collectors;

@Log4j2
public class CacheReader {

    public Set<Book> getBooksFromCache(String libName) {
        Set<Book> inputBooks;
        Set<Book> booksToSave = null;
        try (FileInputStream fileInputStream = new FileInputStream(libName)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            inputBooks = (Set<Book>) objectInputStream.readObject();
            booksToSave = inputBooks.stream().filter(book -> !(book.getTitle().isEmpty())).collect(Collectors.toSet());
            objectInputStream.close();
        } catch (IOException e) {
            log.error("Can't access file: " +e);
        } catch (ClassNotFoundException e) {
            log.error("Class not found: " + e);
        }
        return booksToSave;
    }
}
