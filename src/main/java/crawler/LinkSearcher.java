package crawler;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class LinkSearcher {

    public List<String> getLinks(String url) throws IOException {
        try {
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("a[href]");
            return links.stream()
                    .map(link -> link.attr("abs:href"))
                    .distinct()
                    .toList();
        } catch (UnsupportedMimeTypeException unsupportedMimeTypeException) {
            unsupportedMimeTypeException.printStackTrace();
            return List.of();
        }
    }
}
