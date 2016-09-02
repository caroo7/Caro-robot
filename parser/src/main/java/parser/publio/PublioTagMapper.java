package parser.publio;

import mapper.Mapper;

import java.util.HashMap;

public class PublioTagMapper extends Mapper {

    {
        loadProperties("/publio/publio_tags.properties");
        map = new HashMap<>();
        map.put(props.getProperty("recent"), "recent");
        map.put(props.getProperty("bestseller"), "bestseller");
        map.put(props.getProperty("promotion"), "promotion");
    }

}