package parser;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

public class PageLoader {

    private static final String USER_AGENT = "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:48.0) Gecko/20100101 Firefox/48.0";

    public Optional<Document> getPage(String requestUrl) {
        Document document;
        try {
            Connection.Response response = Jsoup.connect(requestUrl)
                    .userAgent(USER_AGENT)
                    .method(Connection.Method.GET)
                    .timeout(0)
                    .execute();
            document = response.parse();
        } catch (IOException e) {
            document = null;
        }
        return Optional.ofNullable(document);
    }

}

