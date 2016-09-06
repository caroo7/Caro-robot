package web.controller;

import access.CacheReader;
import entities.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class YggdrasilController {

    @Autowired
    private CacheReader cacheReader;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        List<Book> empik = cacheReader.getBooksFromCache("EMPIK");
        List<Book> publio = cacheReader.getBooksFromCache("PUBLIO");
        model.addObject("empik", empik);
        model.addObject("publio", publio);
        return model;
    }

    @RequestMapping(value= "/logging")
    public ModelAndView logging(){
        ModelAndView modelAndView = new ModelAndView();
        return modelAndView;
    }


}
