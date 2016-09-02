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

    @RequestMapping(value = "/", method = RequestMethod.GET)

    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        List<Book> books = cacheReader.getBooksFromCache();
        model.addObject("books", books);
        return model;
    }


}
