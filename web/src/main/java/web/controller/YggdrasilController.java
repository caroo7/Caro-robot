package web.controller;


import access.CacheReader;
import dto.BookDTO;
import entities.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class YggdrasilController {

    @Autowired
    private CacheReader cacheReader;

    @Autowired
    ModelMapper modelMapper;

    private Map<String, List<BookDTO>> cachesMap;

    @PostConstruct
    public void initialize() {
        cachesMap = new HashMap<>();
        cachesMap.put("EMPIK", convertToDTO(cacheReader.getBooksFromCache("EMPIK")));
        cachesMap.put("PUBLIO", convertToDTO(cacheReader.getBooksFromCache("PUBLIO")));
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.addObject("empik", cachesMap.get("EMPIK"));
        model.addObject("publio", cachesMap.get("PUBLIO"));
        return model;
    }

    @RequestMapping(value = "/onDemand", params = {"libName"})
    public String onDemand(@RequestParam(value = "libName") String libName) {
        System.out.println(libName); // instead of this populate appropriate value in map with actual data
        return "redirect:/";
    }


    @RequestMapping(value = "/register")
    public ModelAndView register() {
        return new ModelAndView();
    }

    @RequestMapping(value = "/account")
    public ModelAndView account() {
        return new ModelAndView();
    }

    private List<BookDTO> convertToDTO(List<Book> books) {
        return books.stream().map(book -> modelMapper.map(book, BookDTO.class)).collect(Collectors.toList());
    }


}
