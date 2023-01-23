package lab_1.client.zad2;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class SiteContentDisplayer {

    public String content(String urlAsString) throws IOException {
        URLConnection conn = ConnectionUtil.aConnection(urlAsString);
        return new String(conn.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}
