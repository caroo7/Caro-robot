package web.controller;

import DTO.BookDTO;
import access.CacheReader;
import entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class YggdrasilController {

    @Autowired
    private CacheReader cacheReader;

    @Autowired
    ModelMapper modelMapper;

    private Set<BookDTO> convertToDTO(Set<Book> books) {
        return books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toSet());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        Set<BookDTO> empik = convertToDTO(cacheReader.getBooksFromCache("EMPIK"));
        Set<BookDTO> publio = convertToDTO(cacheReader.getBooksFromCache("PUBLIO"));
        model.addObject("empik", empik);
        model.addObject("publio", publio);
        return model;
    }


    @RequestMapping(value = "/register")
    public ModelAndView register() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/account")
    public ModelAndView account() {
        return new ModelAndView();
    }


}
