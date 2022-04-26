package tierklinik;

import java.sql.*;
import java.util.ArrayList;

public class FullDB {
    //public  static Connection con = null;

    public static Connection connect() throws SQLException {
        // db parameters
        String url = "jdbc:sqlite:/Users/senemdilli/IdeaProjects/210503066-Senem-Dilli-V2/src/main/resources/tierklinik.db";
        // create a connection to the database
        Connection con = DriverManager.getConnection(url);
        /*try {
            // db parameters
            String url = "jdbc:sqlite:/Users/senemdilli/IdeaProjects/210503066-Senem-Dilli-V2/src/main/resources/tierklinik.db";
            // create a connection to the database
            Connection con = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            Statement stmt = con.createStatement();

            /*
            ResultSet res = stmt.executeQuery("SELECT * FROM person");
            while(res.next()){
                System.out.println("name: "+res.getString("name")+" nachname: "+res.getString("nachname"));

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }*/
        return con;
    }

}
