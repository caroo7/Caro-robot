package repositories;

import entities.Tag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TagRepository extends CrudRepository<Tag, Long> {

    List<Tag> findAll();

}