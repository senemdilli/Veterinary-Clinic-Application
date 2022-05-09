package tierklinik;

import Classes.Person;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableControllerTier implements Initializable {
    @FXML
    private TableView<Tier> table;
    @FXML
    private TableColumn<Person,Integer> col_id;
    @FXML
    private TableColumn<Person,String> col_tiername;
    @FXML
    private TableColumn<Person,String> col_nachname;
    @FXML
    private TableColumn<Person,Integer> col_telnum;
    @FXML
    private TableColumn<Person,String> col_mail;
    @FXML
    private TableColumn<Person,String> col_adresse;
    @FXML
    private TableColumn<Tier,String> col_hbname;
    @FXML
    private TableColumn<Tier,Integer> col_hbid;
    @FXML
    private TableColumn<Tier,Double> col_kontostand;

    String query = null;
    String query2 = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Tier tier = null;
    Person person = null;
    int id = 0;
    ObservableList<Tier> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/addTier.fxml"));
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
            query = "SELECT * FROM person INNER JOIN tier WHERE person.id = tier.tierid";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                oblist.add(new Tier(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getString("nachname"), resultSet.getInt("telefonnummer"),
                        resultSet.getString("email"), resultSet.getString("adresse"),
                        resultSet.getString("hbname"), resultSet.getInt("hbid"),
                        resultSet.getDouble("kontostand")));
                table.setItems(oblist);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void deleteTier(MouseEvent event) {
        try {
            tier = table.getSelectionModel().getSelectedItem();
            person = table.getSelectionModel().getSelectedItem();
            id = tier.getId();
            query = "DELETE FROM 'tier' WHERE tierid = " + id;
            query2 = "DELETE FROM 'person' WHERE id = " + id;
            connection = FullDB.connect();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
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
        col_tiername.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_telnum.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
        col_hbname.setCellValueFactory(new PropertyValueFactory<>("hbname"));
        col_hbid.setCellValueFactory(new PropertyValueFactory<>("hbid"));
        col_kontostand.setCellValueFactory(new PropertyValueFactory<>("kontostand"));

        table.setItems(oblist);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
