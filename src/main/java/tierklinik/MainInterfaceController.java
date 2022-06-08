package tierklinik;

import Classes.Person;
import Classes.Personal;
import Classes.Termin;
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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainInterfaceController implements Initializable {

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;

    @FXML
    private TableView<Termin> terminTable;
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
    private TableColumn<Termin,String> col_startzeit;

    String query = null;
    ObservableList<Termin> oblist = FXCollections.observableArrayList();

    @FXML
    private void getPersonalListe() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/TableList_Personal.fxml"));
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
    private void getTierListe() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/TableList_Tier.fxml"));
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
    private void getTerminView() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/TableList_Appointment.fxml"));
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
    private void getZahlungView() {

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
                terminTable.setItems(oblist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
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

        col_tiername.setCellValueFactory(new PropertyValueFactory<>("tiername"));
        col_hbname.setCellValueFactory(new PropertyValueFactory<>("hbname"));
        col_tiernachname.setCellValueFactory(new PropertyValueFactory<>("tiernachname"));
        col_tierarzt.setCellValueFactory(new PropertyValueFactory<>("tierarztname"));
        col_angabe.setCellValueFactory(new PropertyValueFactory<>("angabe"));
        col_startzeit.setCellValueFactory(new PropertyValueFactory<>("startzeit"));

        terminTable.setItems(oblist);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
