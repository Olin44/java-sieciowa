package server.zad5;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class myServer {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        server.createContext("/", new CustomHttpHandler());
        server.start();
    }

    static class CustomHttpHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Headers responseHeaders = httpExchange.getResponseHeaders();

            String line;
            StringBuilder response = new StringBuilder();

            try {
                File newFile = createFile(httpExchange);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(newFile)));

                while ((line = bufferedReader.readLine()) != null) {
                    response.append(line);
                }
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            responseHeaders.add("Content-Type", "text/html");
            httpExchange.sendResponseHeaders(200, response.length());
            OutputStream os = httpExchange.getResponseBody();
            os.write(response.toString().getBytes());
            os.close();
        }

        @NotNull
        private File createFile(HttpExchange httpExchange) {
            return new File("src/main/java/server/zad5/www" + httpExchange.getRequestURI());
        }
    }
}
