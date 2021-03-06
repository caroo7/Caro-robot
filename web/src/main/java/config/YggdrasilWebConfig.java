package config;

import converter.BookMap;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;



@EnableWebMvc
@Configuration
@ComponentScan(value = {"web.controller"})
public class YggdrasilWebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setContentType("text/html; charset=utf-8");
        viewResolver.setPrefix("/WEB-INF/views/jsp/");
        viewResolver.setSuffix("index.jsp");
        return viewResolver;
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.addMappings(new BookMap());
        return modelMapper;
    }
}
