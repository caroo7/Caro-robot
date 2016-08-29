package parser.empik;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Grzesiek on 2016-08-27.
 */
public class Utils {

    private static Path getPathInResources(String relativePath) throws URISyntaxException {
        return Paths.get(Utils.class.getClass().getResource(relativePath).toURI());
    }

    public static Optional<Document> loadHtmlDocument(String relativePath, String pageUrl) throws URISyntaxException, IOException {
        String htmlPage = new String(Files.readAllBytes(getPathInResources(relativePath)));
        Document document = Jsoup.parse(htmlPage);
        document.setBaseUri(pageUrl);
        return Optional.of(document);
    }

    public static List<String> loadFileByLineToList(String relativePath)  {
        List<String> textLineByLine = new ArrayList<>();

        try (Stream<String> stream = Files.lines(getPathInResources(relativePath))) {
            textLineByLine = stream.collect(Collectors.toList());
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        return textLineByLine;
    }

}
