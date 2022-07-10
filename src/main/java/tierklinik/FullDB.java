package tierklinik;

import Classes.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class FullDB {
    public  static Connection con = null;
    static String query = null;
    static String query2 = null;
    static Connection connection = null;
    static PreparedStatement preparedStatement;
    static ResultSet resultSet = null;
    private static boolean update;
    static Integer id;

    public static Connection connect() throws SQLException {
        String url = "jdbc:sqlite:" + System.getProperty("user.dir") + "/src/main/resources/tierklinik.db";
        con = DriverManager.getConnection(url);

        return con;
    }

    public static void setConnection() {
        try {
            connection = connect();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Personal SQL Operationen
    // ADD Personal
    protected static void getPersonalQuery(Personal personal) {
        setConnection();
        id = personal.getId();
        System.out.println(id);
        if (!update) {
            query = "INSERT INTO person ('name', 'nachname', id , 'adresse', telefonnummer , 'email') VALUES(?,?,?,?,?,?)";
            query2 = "INSERT INTO personal ( id, personalnummer, gehalt, 'arbeit') VALUES(?,?,?,?)";
        } else {
            query = "UPDATE 'person' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'id' = ?,"
                    + "'adresse' =?,"
                    + "'telefonnummer' =?,"
                    + "'email' =? WHERE id ='" + id + "'";
            query2 = "UPDATE 'personal' SET"
                    + "'id' = ?,"
                    + "'personalnummer' =?,"
                    + "'gehalt' =?,"
                    + "'arbeit' =? WHERE id = '" + id + "'";
        }

    }
    protected static void insertPerson(Person person) {
        getPersonalQuery((Personal) person);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, String.valueOf(person.getId()));
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getNachname());
            preparedStatement.setString(5, String.valueOf(person.getTelefonnummer()));
            preparedStatement.setString(6, person.getEmail());
            preparedStatement.setString(4, person.getAdresse());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected static void insertPersonal(Personal personal) {
        getPersonalQuery(personal);
        try {
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(personal.getId()));
            preparedStatement.setString(4, personal.getArbeit());
            preparedStatement.setString(2, String.valueOf(personal.getPersonalnummer()));
            preparedStatement.setString(3, String.valueOf(personal.getGehalt()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Personal Table
    protected static ObservableList<Personal> getPersonalDB() {
        setConnection();
        ObservableList<Personal> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM person INNER JOIN personal WHERE person.id = personal.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                oblist.add(new Personal(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("nachname"), resultSet.getInt("telefonnummer"),
                        resultSet.getString("email"), resultSet.getString("adresse"),
                        resultSet.getString("arbeit"), resultSet.getInt("personalnummer"),
                        resultSet.getDouble("gehalt")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
    protected static void deletePersonal(Personal personal) {
        id = personal.getId();
        try {
            query = "DELETE FROM 'personal' WHERE id = " + id;
            String query2 = "DELETE FROM 'person' WHERE id = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Tier SQL Operationen
    // ADD Tier
    protected static void getTierQuery(Tier tier) {
        setConnection();
        id = tier.getTierid();
        if (!update) {
            query = "INSERT INTO person ('name', 'nachname', id , 'adresse', telefonnummer , 'email') VALUES(?,?,?,?,?,?)";
            query2 = "INSERT INTO tier (tierid, 'hbname', hbid, kontostand) VALUES(?,?,?,?)";
        } else {
            query = "UPDATE 'person' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'id' = ?,"
                    + "'adresse' =?,"
                    + "'telefonnummer' =?,"
                    + "'email' =? WHERE id ='" + id + "'";
            query2 = "UPDATE 'tier' SET"
                    + "'tierid' = ?,"
                    + "'hbname' = ?,"
                    + "'hbid' = ?,"
                    + "'kontostand' = ? WHERE tierid = '" + id + "'";
        }

    }
    protected static void insertTier(Tier tier) {
        getTierQuery(tier);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, tier.getName());
            preparedStatement.setString(2, tier.getNachname());
            preparedStatement.setString(3, String.valueOf(tier.getId()));
            preparedStatement.setString(4, tier.getAdresse());
            preparedStatement.setString(5, String.valueOf(tier.getTelefonnummer()));
            preparedStatement.setString(6, tier.getEmail());
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(tier.getId()));
            preparedStatement.setString(2, tier.getHbname());
            preparedStatement.setString(3, String.valueOf(tier.getHbid()));
            preparedStatement.setString(4, String.valueOf(tier.getKontostand()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    // Tier Table
    protected static ObservableList<Tier> getTierDB() {
        setConnection();
        ObservableList<Tier> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM tier INNER JOIN person ON person.id = tier.tierid";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Tier tier = new Tier(resultSet.getInt("tierid"), resultSet.getString("name"),
                        resultSet.getString("nachname"), resultSet.getInt("telefonnummer"),
                        resultSet.getString("email"), resultSet.getString("adresse"),
                        resultSet.getString("hbname"), resultSet.getInt("hbid"),
                        resultSet.getDouble("kontostand"));
                oblist.add(tier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
    protected static void deleteTier(Tier tier) {
        id = tier.getId();
        try {
            query = "DELETE FROM 'tier' WHERE tierid = " + id;
            query2 = "DELETE FROM 'person' WHERE id = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Appointment SQL Operationen
    // ADD Appointment
    public static ResultSet getTierarztname() throws SQLException {
        query = "SELECT name FROM 'person' INNER JOIN 'personal' ON 'person'.id = 'personal'.id WHERE arbeit = 'Tierarzt'";
        connection = FullDB.connect();
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }
    protected static void insertTermin(Termin termin) {
        getTerminQuery(termin);
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(termin.getDate()));
            preparedStatement.setString(2, String.valueOf(termin.getStartzeit()));
            preparedStatement.setString(3, String.valueOf(termin.getEndezeit()));
            preparedStatement.setString(4, termin.getAngabe());
            preparedStatement.setString(5, String.valueOf(Termin.getTerminid()));
            preparedStatement.setString(6, termin.getTiername());
            preparedStatement.setString(7, termin.getTiernachname());
            preparedStatement.setString(8, termin.getTierarztname());
            preparedStatement.setString(9, termin.getHbname());
            preparedStatement.setString(10, "nicht");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected static void getTerminQuery(Termin termin) {
        id = Termin.getTerminid();

        if (!update) {
            query = "INSERT INTO termin ('date', 'startzeit', 'endezeit', 'angabe', terminid, 'tiername', 'tiernachname', 'tierarztname', 'hbname', 'zustand') VALUES(?,?,?,?,?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'termin' SET"
                    + "'date' =?,"
                    + "'startzeit' =?,"
                    + "'endezeit' = ?,"
                    + "'angabe' =?,"
                    + "'terminid' =?,"
                    + "'tiername' =?,"
                    + "'tiernachname' =?,"
                    + "'tierarztname' =?,"
                    + "'hbname' =?,"
                    + "'zustand' = 'nicht' WHERE terminid ='" + id + "'";
        }

    }

    // Appointment Table
    public static int getTerminId() throws SQLException {
        query = "SELECT * FROM termin";
        int terminid = 0;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            terminid = resultSet.getInt("terminid");
        }
        return terminid+1;
    }
    public static Termin getTermin(int id) throws SQLException {
        Termin termin = null;
        query = "SELECT * FROM termin WHERE terminid =" + id;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultSet.next()) {
            termin = new Termin(resultSet.getInt("terminid"), resultSet.getString("tiername"),
                    resultSet.getString("hbname"), resultSet.getString("tiernachname"),
                    resultSet.getString("tierarzt"), resultSet.getString("angabe"),
                    resultSet.getString("date"), resultSet.getString("startzeit"),
                    resultSet.getString("endezeit"));
        }
        return termin;
    }
    protected static void makeDone() {
        try {
            id = Termin.getTerminid();
            query = "UPDATE termin SET zustand = 'erledigt' WHERE terminid = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static ObservableList<Termin> getTerminDB() {
        setConnection();
        ObservableList<Termin> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM termin";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Termin termin = new Termin(resultSet.getString("angabe"), resultSet.getString("tiername"),
                        resultSet.getString("tiernachname"), resultSet.getString("hbname"),
                        resultSet.getString("tierarztname"), resultSet.getInt("terminid"), resultSet.getString("zustand"),
                        resultSet.getString("date"), resultSet.getString("startzeit"), resultSet.getString("endezeit"));
                oblist.add(termin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }
    protected static void removeTermin(Termin termin) {
        try {
            id = termin.getTerminid();
            query = "DELETE FROM 'termin' WHERE terminid = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static ResultSet getTierinfo() throws SQLException {
        query = "SELECT tierid,name,nachname,hbname FROM 'tier' INNER JOIN person p on tier.tierid = p.id";
        preparedStatement = connection.prepareStatement(query);
        return preparedStatement.executeQuery();
    }

    // Zahlung SQL Operationen
    // ADD Zahlung
    protected static void getZahlungQuery(Zahlung zahlung) {
        id = Zahlung.getZahlungid();

        if (!update) {
            query = "INSERT INTO zahlung ('zahlungsart', 'zahlungsbetrag', 'zustand', 'zahlungid', 'tierid') VALUES(?,?,?,?,?)";
        } else {
            query = "UPDATE 'zahlung' SET"
                    + "'zahlungsart' =?,"
                    + "'zahlungsbetrag' =?,"
                    + "'zustand' =?,"
                    + "'zahlungid' =?,"
                    + "'tierid' =? WHERE zahlungid ='" + id + "'";
        }

    }
    protected static void insertZahlung(Zahlung zahlung){
        id = zahlung.getZahlungid();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, zahlung.getZahlungsart());
            preparedStatement.setString(2, String.valueOf(zahlung.getZahlungsbetrag()));
            preparedStatement.setString(3, "nicht");
            preparedStatement.setString(4, String.valueOf(Zahlung.getZahlungid()));
            preparedStatement.setString(5, String.valueOf(zahlung.getTierid()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    protected static ResultSet getTier() {
        try {
            query = "SELECT * FROM person INNER JOIN tier WHERE person.id = tier.tierid";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    // Zahlung Table
    protected static Double getTotalAmount() {
        setConnection();
        try {
            query = "SELECT SUM(zahlungsbetrag) FROM zahlung";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            return resultSet.getDouble("SUM(zahlungsbetrag)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected static void deleteZahlung(int id) {
        try {
            query = "DELETE FROM 'zahlung' WHERE zahlungid = " + id;
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int getZahlungid() throws SQLException {
        query = "SELECT * FROM zahlung";
        int zahlungid = 0;
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            zahlungid = resultSet.getInt("zahlungid");
        }
        return zahlungid+1;
    }
    protected static void makePaid(Zahlung zahlung) {
        try {
            query = "UPDATE zahlung SET zustand = 'gezahlt', zahlungsbetrag =  ABS(zahlungsbetrag) WHERE zahlungid = " + Zahlung.getZahlungid();
            String query2 = "UPDATE tier SET kontostand = kontostand + " + zahlung.getZahlungsbetrag() + " WHERE tierid = '" + zahlung.getTierid() + "'";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    protected static ObservableList<Zahlung> getZahlungDB() {
        setConnection();
        ObservableList<Zahlung> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM zahlung INNER JOIN tier t ON zahlung.tierid = t.tierid INNER JOIN person p on t.tierid = p.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Zahlung zahlung = new Zahlung(resultSet.getString("zahlungsart"), resultSet.getDouble("zahlungsbetrag"),
                        resultSet.getInt("zahlungid"), resultSet.getString("zustand"), resultSet.getInt("tierid"),
                        resultSet.getString("name"), resultSet.getString("nachname"), resultSet.getString("hbname"));
                oblist.add(zahlung);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }

    // Rezepte SQL Operationen
    // ADD Rezepte
    public static int getRezeptId() throws SQLException {
        query = "SELECT * FROM rezepte";
        int rezeptid = 0;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet.next()) {
            rezeptid = resultSet.getInt("rezeptid");
        }
        return rezeptid+1;
    }
    protected static void getRezepteQuery(Rezepte rezept) {
        id = rezept.getRezeptId();

        if (!update) {
            query = "INSERT INTO rezepte ('rezeptid', 'tierid', 'tiername', 'nachname', 'medizin', 'date') VALUES(?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'rezepte' SET"
                    + "'tierid' =?,"
                    + "'tiername' =?,"
                    + "'nachname' =?,"
                    + "'medizin' =?,"
                    + "'date' =?,"
                    + "'rezeptid' =? WHERE rezeptid ='" + id + "'";
        }

    }
    protected static void insertRezepte(Rezepte rezept){
        id = rezept.getRezeptId();

        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, String.valueOf(rezept.getRezeptId()));
            preparedStatement.setString(2, String.valueOf(rezept.getTierId()));
            preparedStatement.setString(3, rezept.getTierName());
            preparedStatement.setString(4, rezept.getNachaname());
            preparedStatement.setString(5, rezept.getMedizin());
            preparedStatement.setString(6, String.valueOf(rezept.getDate()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    // Rezepte Table
    protected static ObservableList<Rezepte> getRezepteDB() {
        setConnection();
        ObservableList<Rezepte> oblist = FXCollections.observableArrayList();
        try {
            oblist.clear();
            query = "SELECT * FROM rezepte";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Rezepte rezept = new Rezepte(resultSet.getInt("rezeptid"), resultSet.getInt("tierid"),
                        resultSet.getString("tiername"), resultSet.getString("nachname"),
                        resultSet.getString("medizin"), resultSet.getDate("date"));
                oblist.add(rezept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return oblist;
    }

}
