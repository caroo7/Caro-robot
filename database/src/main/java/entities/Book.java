package entities;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

// not sure about model classes serialization, maybe later we create some kind of DTO classes to passing over HTTP
@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String authors;

    private String description;

    private String discount;

    private String price;  // string because of different currencies

    private Timestamp timestamp;

    @OneToMany
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<Tag> tags;

    @OneToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    Set<Genre> genres;

    private Book() {
    }

    // what about many parameters constructors in entities (maybe builder)?
    public Book(String title, String authors, String description, String discount, String price, Timestamp timestamp, Set<Tag> tags, Set<Genre> genres) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.discount = discount;
        this.price = price;
        this.timestamp = timestamp;
        this.tags = tags;
        this.genres = genres;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public Set<Genre> getGenres() {
        return genres;
    }
}