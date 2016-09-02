package parser.publio;

import mapper.Mapper;

import java.util.HashMap;

public class PublioGenreMapper extends Mapper {

    {
        loadProperties("/publio/publio_genres.properties");
        genresMap = new HashMap<>();
        genresMap.put(props.getProperty("album"), "album");
        genresMap.put(props.getProperty("biographical"), "biographical");
        genresMap.put(props.getProperty("economic"), "economic");
        genresMap.put(props.getProperty("kids"), "kids");
        genresMap.put(props.getProperty("youth"), "youth");
        genresMap.put(props.getProperty("encyclopedia"), "encyclopedia");
        genresMap.put(props.getProperty("science"), "science");
        genresMap.put(props.getProperty("kitchen"), "kitchen");
        genresMap.put(props.getProperty("guide"), "guide");
        genresMap.put(props.getProperty("criminal"), "criminal");
        genresMap.put(props.getProperty("languages"), "languages");
        genresMap.put(props.getProperty("art"), "art");
        genresMap.put(props.getProperty("vacation"), "vacation");
        genresMap.put(props.getProperty("academic_book"), "academic_book");
        genresMap.put(props.getProperty("sport"), "sport");
        genresMap.put(props.getProperty("poetry"), "poetry");
        genresMap.put(props.getProperty("romance"), "romance");
        genresMap.put(props.getProperty("erotic"), "erotic");
        genresMap.put(props.getProperty("fact"), "fact");
        genresMap.put(props.getProperty("professional"), "professional");
        genresMap.put(props.getProperty("history"), "history");
        genresMap.put(props.getProperty("political"), "political");
        genresMap.put(props.getProperty("novel"), "novel");
        genresMap.put(props.getProperty("psychology"), "psychology");
    }

}
