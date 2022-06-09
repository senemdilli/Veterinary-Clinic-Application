package tierklinik;

import Classes.Person;
import Classes.Termin;
import Classes.Personal;
import Classes.Tier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableControllerAppointment implements Initializable {
    @FXML
    private TableView<Termin> table;
    @FXML
    private TableColumn<Termin,Integer> col_terminid;
    @FXML
    private TableColumn<Person,String> col_tiername;
    @FXML
    private TableColumn<Tier,String> col_hbname;
    @FXML
    private TableColumn<Person,String> col_tiernachname;
    @FXML
    private TableColumn<Personal,String> col_tierarzt;
    @FXML
    private TableColumn<Termin,String> col_angabe;
    @FXML
    private TableColumn<Termin,String> col_date;
    @FXML
    private TableColumn<Termin,String> col_startzeit;
    @FXML
    private TableColumn<Termin,String> col_endezeit;

    static String query = null;
    static Connection connection = null;
    ResultSet resultSet = null;
    static ResultSet resultSet2 = null;
    static ResultSet resultSet3 = null;
    static PreparedStatement preparedStatement;
    Termin termin = null;
    int id = 0;
    ObservableList<Termin> oblist = FXCollections.observableArrayList();
    /*private static ArrayList<Termin> terminList = new ArrayList<>();

    public static int getTerminId() {
        return terminList.size();
    }

    public static Termin getTermin(int id) {
        return terminList.get(id);
    }

    public static void addTermin(Termin termin) {
        terminList.add(termin);
    } */

    public static int getTerminId() throws SQLException {
        query = "SELECT * FROM termin";
        int terminid = 0;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet3 = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        while (resultSet3.next()) {
            terminid = resultSet3.getInt("terminid");
        }
        return terminid+1;
    }

    public static Termin getTermin(int id) throws SQLException {
        Termin termin = null;
        query = "SELECT * FROM termin WHERE terminid =" + id;
        try {
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            resultSet2 = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(resultSet2.next()) {
            termin = new Termin(resultSet2.getInt("terminid"), resultSet2.getString("tiername"), resultSet2.getString("hbname"), resultSet2.getString("tiernachname"), resultSet2.getString("tierarzt"), resultSet2.getString("angabe"), resultSet2.getString("date"), resultSet2.getString("startzeit"), resultSet2.getString("endezeit"));
        }
        return termin;
    }

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addAppointment.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void refreshTable() {
        try {
            oblist.clear();
            query = "SELECT * FROM termin";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Termin termin = new Termin(resultSet.getString("angabe"), resultSet.getString("tiername"),
                        resultSet.getString("tiernachname"), resultSet.getString("hbname"),
                        resultSet.getString("tierarztname"));
                termin.setTerminId(resultSet.getInt("terminid"));
                termin.setDate(resultSet.getString("date"));
                termin.setStartzeit(resultSet.getString("startzeit"));
                termin.setEndezeit(resultSet.getString("endezeit"));
                oblist.add(termin);
            }
            table.setItems(oblist);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void removeTermin(MouseEvent event) {
        try {
            termin = table.getSelectionModel().getSelectedItem();
            id = termin.getTerminid();
            query = "DELETE FROM 'termin' WHERE terminid = " + id;
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
            refreshTable();
        } catch (SQLException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void loadDate() {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        refreshTable();

        col_terminid.setCellValueFactory(new PropertyValueFactory<>("terminid"));
        col_tiername.setCellValueFactory(new PropertyValueFactory<>("tiername"));
        col_hbname.setCellValueFactory(new PropertyValueFactory<>("hbname"));
        col_tiernachname.setCellValueFactory(new PropertyValueFactory<>("tiernachname"));
        col_tierarzt.setCellValueFactory(new PropertyValueFactory<>("tierarztname"));
        col_angabe.setCellValueFactory(new PropertyValueFactory<>("angabe"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        col_startzeit.setCellValueFactory(new PropertyValueFactory<>("startzeit"));
        col_endezeit.setCellValueFactory(new PropertyValueFactory<>("endezeit"));

        table.setItems(oblist);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
