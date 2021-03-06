package entities;

import converter.LocalDateConverter;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Set;

// not sure about model classes serialization, maybe later we create some kind of dto classes to passing over HTTP
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Getter
    private String title;

    @Getter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    @Getter
    @Column(length = 10000)
    private String description;
    @Getter
    private String discount;
    @Getter
    private String price;

    @Setter
    @Getter
    @Convert(converter = LocalDateConverter.class)
    private LocalDate fromDate;

    @Setter
    @Getter
    @Convert(converter = LocalDateConverter.class)
    private LocalDate toDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_tag",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    @Getter
    private Set<Tag> tags;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    @Getter
    private Set<Genre> genres;


    @Getter
    private String url;

    @Getter
    @Column(name = "cover_url")
    private String coverUrl;

    @ManyToOne
    @JoinColumn(name = "library_id")
    @Getter
    private Library library;


    /**
     * Books are distinguished based on title and library.
     * If in other library there is book with same title it is treated as different book
     * (it will be saved as another record in database).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (price != null ? !price.equals(book.price) : book.price != null) return false;
        return library != null ? library.equals(book.library) : book.library == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (fromDate != null ? fromDate.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }


}