package parser.publio;

import mapper.Mapper;

import java.util.HashMap;

public class PublioGenreMapper extends Mapper {

    {
        loadProperties("/publio/publio_genres.properties");
        map = new HashMap<>();
        map.put(props.getProperty("album"), "album");
        map.put(props.getProperty("biographical"), "biographical");
        map.put(props.getProperty("economic"), "economic");
        map.put(props.getProperty("kids"), "kids");
        map.put(props.getProperty("youth"), "youth");
        map.put(props.getProperty("encyclopedia"), "encyclopedia");
        map.put(props.getProperty("science"), "science");
        map.put(props.getProperty("kitchen"), "kitchen");
        map.put(props.getProperty("guide"), "guide");
        map.put(props.getProperty("criminal"), "criminal");
        map.put(props.getProperty("languages"), "languages");
        map.put(props.getProperty("art"), "art");
        map.put(props.getProperty("vacation"), "vacation");
        map.put(props.getProperty("academic_book"), "academic_book");
        map.put(props.getProperty("sport"), "sport");
        map.put(props.getProperty("poetry"), "poetry");
        map.put(props.getProperty("romance"), "romance");
        map.put(props.getProperty("erotic"), "erotic");
        map.put(props.getProperty("fact"), "fact");
        map.put(props.getProperty("professional"), "professional");
        map.put(props.getProperty("history"), "history");
        map.put(props.getProperty("political"), "political");
        map.put(props.getProperty("novel"), "novel");
        map.put(props.getProperty("psychology"), "psychology");
    }

}
