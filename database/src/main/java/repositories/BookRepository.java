package repositories;

import entities.Book;
import entities.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Component
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    Set<Book> findByLibrary(Library library);

    List<Book> findByLibraryAndToDate(Library library, LocalDate toDate);

}