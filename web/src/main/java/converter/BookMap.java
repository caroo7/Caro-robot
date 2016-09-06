package converter;

import DTO.BookDTO;
import entities.Author;
import entities.Book;
import entities.Genre;
import entities.Tag;
import org.modelmapper.PropertyMap;

import java.util.Set;

public class BookMap extends PropertyMap<Book, BookDTO>{

    private String concatenateBy(String s1,String s2,String separator){
        return s1+", "+s2;
    }

//
//    private String retrieveTags() {
//        Set<Tag> tags=source.getTags();
//        System.out.println("tags: "+tags);
//        if(tags==null||tags.isEmpty()) {
//            //FIXME: why would I log that like it's noteworthy event? :-)
//            return "";
//        }
//        return source.getTags().stream().map(tag -> tag.getName()).reduce((s, s2) -> concatenateBy(s, s2, ",")).get();
//    }


    private String getAuthors(Set<Author>authorSet){
        String authors = "";
        for (Author author:authorSet){
            authors=concatenateBy(authors,author.getName(),",");
        }
        System.out.println(authors);
        return authors;
    }


    private String getGenres(Set<Genre>genreSet){
        String genres = "";
        for (Genre genre:genreSet){
            genres=concatenateBy(genres,genre.getName(),",");
        }
        System.out.println(genres);
        return genres;
    }

    private String getTags(Set<Tag>tagSet){
        String tags = "";
        for (Tag tag:tagSet){
            tags=concatenateBy(tags,tag.getName(),",");
        }
        System.out.println(tags);
        return tags;
    }

    @Override
    protected void configure(){

//        System.out.println(getAuthors(source.getAuthors()));
//        String genres=source.getGenres().stream().map(genre -> genre.getName()).reduce((s, s2) -> concatenateBy(s,s2,",")).orElse("");
        map().setAuthors(getAuthors(source.getAuthors()));
        map().setGenres(getGenres(source.getGenres()));
        map().setTags(getTags(source.getTags()));
    }


};