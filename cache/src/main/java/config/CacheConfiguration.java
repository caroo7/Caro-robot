package config;

import access.CacheReader;
import cache.Cache;
import org.springframework.context.annotation.Bean;

public class CacheConfiguration {

    @Bean
    public Cache cache() {
        return new Cache();
    }

    @Bean
    public CacheReader cacheReader() {
        return new CacheReader();
    }

}
