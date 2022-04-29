package tierklinik;

import Classes.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TableController implements Initializable {
    @FXML
    private TableView<Person> table;
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

    ObservableList<Person> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            Connection con = FullDB.connect();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM person");

            while (rs.next()) {
                oblist.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getString("nachname"), rs.getInt("telefonnummer"), rs.getString("email"), rs.getString("adresse")));
            }
        } catch (SQLException e) {
            Logger.getLogger(TableController.class.getName()).log(Level.SEVERE, null, e);
        }

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_telnum.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
        col_mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));

        table.setItems(oblist);
    }
}
