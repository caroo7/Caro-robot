package mapper;

import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

@Log4j2
public abstract class Mapper {

    protected Map<String, String> map;

    protected Properties props = new Properties();

    protected void loadProperties(String pathToFile) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getClass()
                    .getResourceAsStream(pathToFile), "UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            log.error(e);
        }
    }

    public Map<String, String> getMap() {
        return map;
    }

}