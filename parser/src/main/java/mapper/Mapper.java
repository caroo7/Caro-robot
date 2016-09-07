package mapper;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Log4j2
public abstract class Mapper {

    protected static final Map<String, String> map = new HashMap<>();

    protected static final Properties props = new Properties();

    protected void loadProperties(String pathToFile) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getClass()
                    .getResourceAsStream(pathToFile), "UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public Map<String, String> getMap() {
        return map;
    }

}