package entities;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;


@Entity
@NoArgsConstructor

public class Library implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter
    private String name;
    @Getter
    private String url;

    /**
     *
     * @param name the name of the library
     * @param url the url address of the library
     */
    public Library(String name, String url) {
        this.name = name;
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Library library = (Library) o;

        return name.equals(library.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}