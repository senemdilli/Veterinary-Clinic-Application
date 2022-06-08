package tierklinik;

import Classes.Person;
import Classes.Termin;
import Classes.Personal;
import Classes.Tier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
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

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Termin termin = null;
    int id = 0;
    ObservableList<Termin> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/addAppointment.fxml"));
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
    private void refreshTable() {
        try {
            oblist.clear();
            query = "SELECT * FROM termin";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                oblist.add(new Termin(resultSet.getInt("terminid"), resultSet.getString("angabe"),
                        resultSet.getString("date"), resultSet.getString("startzeit"),
                        resultSet.getString("endezeit"), resultSet.getString("tiername"),
                        resultSet.getString("tiernachname"), resultSet.getString("hbname"),
                        resultSet.getString("tierarztname")));
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
