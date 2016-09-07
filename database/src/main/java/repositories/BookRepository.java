package repositories;

import entities.Book;
import entities.Library;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

    List<Book>  findByLibrary(Library library);

    List<Book> findByLibraryAndToDate(Library library, LocalDate toDate);

}