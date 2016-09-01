package repositories;

import entities.Library;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LibraryRepository extends CrudRepository<Library, Long> {

    List<Library> findAll();

    Library findByName(String name);
}
