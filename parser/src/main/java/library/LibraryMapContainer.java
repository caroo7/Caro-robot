package library;

import entities.Library;
import parser.PageLoader;
import parser.PromotionLibrary;
import parser.empik.EmpikGenreMapper;
import parser.empik.EmpikPromotionLibrary;
import parser.empik.EmpikTagMapper;
import parser.publio.PublioGenreMapper;
import parser.publio.PublioPromotionLibrary;
import parser.publio.PublioTagMapper;
import repositories.LibraryRepository;

import java.util.HashMap;
import java.util.Map;

public class LibraryMapContainer {

    private Map<Library, PromotionLibrary> libraryMap = new HashMap<>();

    // improve this method
    public void initialize(LibraryRepository libraryRepo) {
        for (Library library : libraryRepo.findAll()) {
            if (library.getName().equalsIgnoreCase(Libraries.EMPIK.toString())) {
                PromotionLibrary empikPromotionLibrary = new EmpikPromotionLibrary(new PageLoader(), new EmpikGenreMapper(), new EmpikTagMapper());
                libraryMap.put(library, empikPromotionLibrary);
            } else if (library.getName().equalsIgnoreCase(Libraries.PUBLIO.toString())) {
                PromotionLibrary publioPromotionLibrary = new PublioPromotionLibrary(new PageLoader(), new PublioGenreMapper(), new PublioTagMapper());
                libraryMap.put(library, publioPromotionLibrary);
            }
        }
    }

    public PromotionLibrary getLibrary(Library key) {
        return libraryMap.get(key);
    }

}