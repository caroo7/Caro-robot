package parser.publio;

import mapper.Mapper;

public class PublioTagMapper extends Mapper {
    static {


        map.put(props.getProperty("recent"), "recent");
        map.put(props.getProperty("bestseller"), "bestseller");
        map.put(props.getProperty("promotion"), "promotion");
    }

    public PublioTagMapper() {
        loadProperties("/publio/publio_tags.properties");
    }

}