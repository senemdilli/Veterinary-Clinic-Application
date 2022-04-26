package tierklinik;

import java.sql.*;

public class FullDB {
    public  static Connection con = null;

    public static Connection connect() throws SQLException {
        // db parameters
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/tierklinik.db";
        // create a connection to the database
        Connection con = DriverManager.getConnection(url);

        return con;
    }

}
