package config;

import converter.BookDetailsToBookAssembler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParserConfiguration {

    @Bean
    public BookDetailsToBookAssembler bookDetailsToBookAssembler() {
        return new BookDetailsToBookAssembler();
    }

}
