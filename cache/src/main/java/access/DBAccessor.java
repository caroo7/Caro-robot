package access;

import entities.Book;
import entities.Library;
import lombok.extern.log4j.Log4j2;
import repositories.BookRepository;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

@Log4j2
public class DBAccessor {

    public List<Book> getBooks(BookRepository bookRepo, Library library) {
        return bookRepo.findByLibrary(library);
    }

    private static final String CACHE_FILE_SAVE_LOCATION = "../../web/";


    public void createCache(List<Book> list, String libraryName) {
        try {
            FileOutputStream outputStream = new FileOutputStream(CACHE_FILE_SAVE_LOCATION + libraryName);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(list);
        } catch (FileNotFoundException fNFE) {
            log.error("Exception while accessing the file: " + fNFE);
        } catch (IOException iOE) {
            log.error("Exception while streaming the objects: " + iOE);
        }
    }

}