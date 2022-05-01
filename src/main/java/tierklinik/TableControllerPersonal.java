package tierklinik;

import Classes.Person;
import Classes.Personal;
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

public class TableControllerPersonal implements Initializable {
    @FXML
    private TableView<Personal> table;
    @FXML
    private TableColumn<Person,Integer> col_id;
    @FXML
    private TableColumn<Person,String> col_name;
    @FXML
    private TableColumn<Person,String> col_nachname;
    @FXML
    private TableColumn<Person,Integer> col_telnum;
    @FXML
    private TableColumn<Person,String> col_mail;
    @FXML
    private TableColumn<Person,String> col_adresse;
    @FXML
    private TableColumn<Personal,String> col_arbeit;
    @FXML
    private TableColumn<Personal,Double> col_gehalt;
    @FXML
    private TableColumn<Personal,Integer> col_personalnummer;

    String query = null;
    String query2 = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Personal personal = null;
    Person person = null;
    ObservableList<Personal> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/addPersonal.fxml"));
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
            query = "SELECT * FROM person INNER JOIN personal WHERE person.id = personal.id";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                oblist.add(new Personal(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("nachname"), resultSet.getInt("telefonnummer"),
                        resultSet.getString("email"), resultSet.getString("adresse"),
                        resultSet.getString("arbeit"), resultSet.getInt("personalnummer"),
                        resultSet.getDouble("gehalt")));
                table.setItems(oblist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deletePersonal(MouseEvent event) {
        try {
            personal = table.getSelectionModel().getSelectedItem();
            person = table.getSelectionModel().getSelectedItem();
            query = "DELETE FROM 'personal' WHERE id = " + personal.getId();
            query2 = "DELETE FROM 'person' WHERE id = " + person.getId();
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement = connection.prepareStatement(query2);
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

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_telnum.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_personalnummer.setCellValueFactory(new PropertyValueFactory<>("personalnummer"));
        col_arbeit.setCellValueFactory(new PropertyValueFactory<>("arbeit"));
        col_gehalt.setCellValueFactory(new PropertyValueFactory<>("gehalt"));

        table.setItems(oblist);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
