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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableControllerTier implements Initializable {
    @FXML
    private TableView<Tier> table;
    @FXML
    private TableColumn<Tier,Integer> col_id;
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
    Tier tier = null;
    ObservableList<Tier> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addTier.fxml")));
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
        oblist = FullDB.getTierDB();
        table.setItems(oblist);
    }

    @FXML
    private void deleteTier() {
        tier = table.getSelectionModel().getSelectedItem();
        FullDB.deleteTier(tier);
    }

    @FXML
    private void loadDate() {
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
