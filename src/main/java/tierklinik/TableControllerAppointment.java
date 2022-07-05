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
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableControllerAppointment implements Initializable {
    @FXML
    private TableView<Termin> table;
    @FXML
    private TableColumn<Termin,Integer> col_zustand;
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
    Termin termin = null;
    ObservableList<Termin> oblist = FXCollections.observableArrayList();

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
    private void makeDone() {
        termin = table.getSelectionModel().getSelectedItem();
        FullDB.makeDone();
        refreshTable();
    }


    @FXML
    protected void refreshTable() {
        oblist = FullDB.getTerminDB();
        table.setItems(oblist);
    }

    @FXML
    private void removeTermin() {
        termin = table.getSelectionModel().getSelectedItem();
        FullDB.removeTermin(termin);
    }

    @FXML
    private void loadDate() {
        refreshTable();

        col_zustand.setCellValueFactory(new PropertyValueFactory<>("zustand"));
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
