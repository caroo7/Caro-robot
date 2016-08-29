package mapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class EmpikGenreMapper {

    private final Map<String, String> genresMap;

    private Properties props = new Properties();

    {
        loadProperties();
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

    private void loadProperties() {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getClass()
                    .getResourceAsStream("/empik/empik_genres.properties"), "UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getGenresMap() {
        return genresMap;
    }
}
