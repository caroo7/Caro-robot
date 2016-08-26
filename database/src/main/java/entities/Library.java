package entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String URL;

    @OneToMany
    @JoinColumn(name = "library_id")
    List<Book> books;

    public Library(String name, String URL) {
        this.name = name;
        this.URL = URL;
    }

}