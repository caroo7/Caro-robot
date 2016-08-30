package mapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

public abstract class Mapper {

    Map<String, String> genresMap;

    Properties props = new Properties();

    void loadProperties(String pathToFile) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getClass()
                    .getResourceAsStream(pathToFile), "UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getGenresMap() {
        return genresMap;
    }
}