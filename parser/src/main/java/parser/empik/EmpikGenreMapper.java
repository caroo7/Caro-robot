package parser.empik;

import mapper.Mapper;

import java.util.HashMap;

public class EmpikGenreMapper extends Mapper {

    {
        loadProperties("/empik/empik_genres.properties");
        map = new HashMap<>();
        map.put(props.getProperty("biographical"), "biographical");
        map.put(props.getProperty("economic"), "economic");
        map.put(props.getProperty("kids"), "kids");
        map.put(props.getProperty("youth"), "youth");
        map.put(props.getProperty("house"), "house");
        map.put(props.getProperty("encyclopedia"), "encyclopedia");
        map.put(props.getProperty("fantasy"), "fantasy");
        map.put(props.getProperty("IT"), "IT");
        map.put(props.getProperty("technology"), "technology");
        map.put(props.getProperty("languages"), "languages");
        map.put(props.getProperty("classic"), "classic");
        map.put(props.getProperty("comic"), "comic");
        map.put(props.getProperty("criminal"), "criminal");
        map.put(props.getProperty("academic_book"), "academic_book");
        map.put(props.getProperty("kitchen"), "kitchen");
        map.put(props.getProperty("lectures"), "lectures");
        map.put(props.getProperty("literature"), "literature");
        map.put(props.getProperty("medicine"), "medicine");
        map.put(props.getProperty("science"), "science");
        map.put(props.getProperty("student_book"), "student_book");
        map.put(props.getProperty("guide"), "guide");
        map.put(props.getProperty("law"), "law");
        map.put(props.getProperty("romance"), "romance");
        map.put(props.getProperty("prose"), "prose");
        map.put(props.getProperty("sensation"), "sensation");
        map.put(props.getProperty("art"), "art");
        map.put(props.getProperty("vacation"), "vacation");
        map.put(props.getProperty("thriller"), "thriller");
    }

}