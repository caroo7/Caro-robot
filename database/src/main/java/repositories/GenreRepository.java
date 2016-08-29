package repositories;

import entities.Genre;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    List<Genre> findAll();

    default Genre findByName(String name) {
        Optional<Genre> result = findAll().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst();

        return result.isPresent() ? result.get() : null;
    }

}