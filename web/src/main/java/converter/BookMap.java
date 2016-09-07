package converter;

import dto.BookDTO;
import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Tag;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.PropertyMap;

import java.util.Set;

public class BookMap extends PropertyMap<Book, BookDTO>{


    Converter<Set<Author>, String> authorsConverter = new AbstractConverter<Set<Author>, String>() {
        @Override
        protected String convert(Set<Author> authors) {
            return authors.stream().map(Author::getName).reduce((s, s2) -> s+", "+s2).orElse("");
        }
    };

    Converter<Set<Tag>, String> tagsConverter = new AbstractConverter<Set<Tag>, String>() {
        @Override
        protected String convert(Set<Tag> tags) {
            return tags.stream().map(Tag::getName).reduce((s, s2) -> s+", "+s2).orElse("");
        }
    };

    Converter<Set<Genre>, String> genresConverter = new AbstractConverter<Set<Genre>, String>() {
        @Override
        protected String convert(Set<Genre> genres) {
            return genres.stream().map(Genre::getName).reduce((s, s2) -> s+", "+s2).orElse("");
        }
    };

    private String concatenateBy(String s1,String s2,String separator){
        return s1+", "+s2;
    }

    @Override
    protected void configure(){
        using(authorsConverter).map(source.getAuthors()).setAuthors("");
        using(genresConverter).map(source.getGenres()).setGenres("");
        using(tagsConverter).map(source.getTags()).setTags("");
    }


};