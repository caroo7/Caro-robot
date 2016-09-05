package parser.helion;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.IPageUrlParser;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class HelionUrlParser implements IPageUrlParser{

    @Override
    public String getLinkToNextPage(Optional<Document> document){
        Elements elements=document.get().select("div.stronicowanie > a");
        if (elements != null) {
            Optional<Element> element=elements.stream().filter(e -> e.text().equals(">")).findFirst();
            if(element.isPresent()){
                return element.get().absUrl("href");
            }
        }

        return null;
    }
    @Override
    public List<String> getLinksToBooksDetails(Optional<Document> document) {
        if(document.isPresent()) {
            List<Element> allBooksSection=document.get().select("ul.list > li.classPresale");
            List<Element> onlyBooksOnPromotion= allBooksSection.stream().filter(element -> element.select("p.tags").text().contains("Promocja")).collect(Collectors.toList());

           return onlyBooksOnPromotion.stream().map(element -> element.select("li.classPresale > a").first().absUrl("href")).collect(Collectors.toList());
        }
        return null;

    }
}
