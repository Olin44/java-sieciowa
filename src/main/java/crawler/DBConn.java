package crawler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConn {
    private Connection connection;

    DBConn() {

    }

    public void connect() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:sqlite:books.db");
    }

    public void disconnect() throws SQLException {
        connection.close();
    }

    public void createTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("create table links (link string, seen int, locked boolean);");
    }

    public void insertRow(String link, int seen, boolean locked) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("insert into links values('%s', '%s', '%s');".formatted(link, seen, locked));
    }

    public void setLocked(String link, boolean locked) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE links SET locked = %s WHERE link = '%s';".formatted(locked, link));
    }


    public boolean exist(String link) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT EXISTS(SELECT * FROM links WHERE link = '%s');".formatted(link));
        return resultSet.getBoolean(1);
    }

    public void setSeen(String link) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("UPDATE links SET seen = seen + 1 WHERE link = '%s';".formatted(link));
        getAllRows().forEach(System.out::println);
    }

    public void dropTables() throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate("drop table links;");
    }

    public List<Link> getAllRows() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select * from links");
        List<Link> links = new ArrayList<>();
        while (resultSet.next()) {
            links.add(new Link(resultSet.getString("link"), resultSet.getInt("seen"), resultSet.getBoolean("locked")));
        }
        return links;
    }

    public String selectRandomLink() throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery("SELECT link FROM links where locked = 'false' ORDER BY RANDOM() LIMIT 1;")
                .getString("link");
    }
}
