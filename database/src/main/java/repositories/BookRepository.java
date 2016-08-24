package repositories;

import entities.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAll();

}