package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String URL;

    @OneToMany
    @JoinColumn(name = "library_id")
    List<Book> books;

    private Library() {
    }

    public Library(String name, String URL, List<Book> books) {
        this.name = name;
        this.URL = URL;
        this.books = books;
    }


}