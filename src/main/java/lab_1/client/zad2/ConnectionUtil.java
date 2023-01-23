package lab_1.client.zad2;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class ConnectionUtil {

    public static URLConnection aConnection(String urlAsString) throws IOException {
        URL url = new URL(urlAsString);
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:47.0) Gecko/20100101 Firefox/47.0");
        conn.setRequestProperty("Content-Type", "text/html");
        return conn;
    }
}
