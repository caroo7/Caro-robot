package mapper;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

public abstract class Mapper {

    protected Map<String, String> map;

    protected Properties props = new Properties();

    protected void loadProperties(String pathToFile) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(getClass()
                    .getResourceAsStream(pathToFile), "UTF-8");
            props.load(inputStreamReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getMap() {
        return map;
    }

}