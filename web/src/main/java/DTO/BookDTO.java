package DTO;

import entities.Library;
import lombok.*;

import java.io.Serializable;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookDTO implements Serializable {

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String authors;

    @Getter
    @Setter
    private String description;
    @Getter
    @Setter
    private String discount;
    @Getter
    @Setter
    private String price;

    @Setter
    @Getter
    private Timestamp timestamp;

    @Getter
    @Setter
    private String url;

    @Getter
    @Setter
    private String coverUrl;

    @Getter
    @Setter
    private String tags;


    @Getter
    @Setter
    private String genres;


    @Getter
    @Setter
    private Library library;


    /**
     * Books are distinguished based on title and library.
     * If in other library there is book with same title it is treated as different book
     * (it will be saved as another record in database).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookDTO book = (BookDTO) o;

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
        result = 31 * result + (timestamp != null ? timestamp.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (genres != null ? genres.hashCode() : 0);
        return result;
    }

}
