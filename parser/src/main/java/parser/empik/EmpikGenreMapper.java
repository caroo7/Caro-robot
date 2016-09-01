package parser.empik;

import mapper.Mapper;

import java.util.HashMap;

public class EmpikGenreMapper extends Mapper {

    {
        loadProperties("/empik/empik_genres.properties");
        genresMap = new HashMap<>();
        genresMap.put(props.getProperty("biographical"), "biographical");
        genresMap.put(props.getProperty("economic"), "economic");
        genresMap.put(props.getProperty("kids"), "kids");
        genresMap.put(props.getProperty("youth"), "youth");
        genresMap.put(props.getProperty("house"), "house");
        genresMap.put(props.getProperty("encyclopedia"), "encyclopedia");
        genresMap.put(props.getProperty("fantasy"), "fantasy");
        genresMap.put(props.getProperty("IT"), "IT");
        genresMap.put(props.getProperty("technology"), "technology");
        genresMap.put(props.getProperty("languages"), "languages");
        genresMap.put(props.getProperty("classic"), "classic");
        genresMap.put(props.getProperty("comic"), "comic");
        genresMap.put(props.getProperty("criminal"), "criminal");
        genresMap.put(props.getProperty("academic_book"), "academic_book");
        genresMap.put(props.getProperty("kitchen"), "kitchen");
        genresMap.put(props.getProperty("lectures"), "lectures");
        genresMap.put(props.getProperty("literature"), "literature");
        genresMap.put(props.getProperty("medicine"), "medicine");
        genresMap.put(props.getProperty("science"), "science");
        genresMap.put(props.getProperty("student_book"), "student_book");
        genresMap.put(props.getProperty("guide"), "guide");
        genresMap.put(props.getProperty("law"), "law");
        genresMap.put(props.getProperty("romance"), "romance");
        genresMap.put(props.getProperty("prose"), "prose");
        genresMap.put(props.getProperty("sensation"), "sensation");
        genresMap.put(props.getProperty("art"), "art");
        genresMap.put(props.getProperty("vacation"), "vacation");
        genresMap.put(props.getProperty("thriller"), "thriller");
    }

}