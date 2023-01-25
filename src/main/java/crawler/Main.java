package crawler;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        LinkSearcher linkSearcher = new LinkSearcher();
        DBConn dbConn = new DBConn();
        dbConn.connect();
        dbConn.dropTables();
        dbConn.createTables();
        dbConn.insertRow("https://inwestomat.eu/jak-kupic-obligacje-skarbowe/", 0, false);
        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(task(linkSearcher, dbConn));
        executor.submit(task(linkSearcher, dbConn));
        executor.submit(task(linkSearcher, dbConn));
        executor.submit(task(linkSearcher, dbConn));
        executor.submit(task(linkSearcher, dbConn));
    }

    @NotNull
    private static Callable<Object> task(LinkSearcher linkSearcher, DBConn dbConn) {
        return () -> {
            while (true) {
                String randomLink = dbConn.selectRandomLink();
                dbConn.setLocked(randomLink, true);
                List<String> links = linkSearcher.getLinks(randomLink);
                dbConn.setLocked(randomLink, false);
                links.parallelStream()
                        .forEach(link -> saveLink(dbConn, link));
            }
        };
    }

    private static void saveLink(DBConn dbConn, String link) {
        try {
            if (dbConn.exist(link)) {
                dbConn.setSeen(link);
            } else {
                dbConn.insertRow(link, 0, false);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
