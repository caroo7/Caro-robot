package repositories;

import entities.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends CrudRepository<Author, Long> {

    List<Author> findAll();

    default Author findAuthor(String name, String surname) {
        Optional<Author> result = findAll().stream()
                .filter(p -> p.getName().equals(name))
                .filter(p -> p.getSurname().equals(surname))
                .findAny();

        return result.isPresent() ? result.get() : null;
    }

}