package tierklinik;

import Classes.Person;
import Classes.Personal;
import Classes.Termin;
import Classes.Tier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
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
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class MainInterfaceController implements Initializable {
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
    ObservableList<Termin> oblist = FXCollections.observableArrayList();

    @FXML
    private void getPersonalListe() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Personal.fxml")));
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
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Tier.fxml")));
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
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Appointment.fxml")));
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
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Kontenstelle.fxml")));
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
    private void getRezepteView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/TableList_Rezepte.fxml")));
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
    private void refreshTermin() {
        oblist = FullDB.getTerminDB();
        terminTable.setItems(oblist);
    }
    @FXML
    private void loadDate() {
        refreshTermin();

        col_tiername.setCellValueFactory(new PropertyValueFactory<>("tiername"));
        col_hbname.setCellValueFactory(new PropertyValueFactory<>("hbname"));
        col_tiernachname.setCellValueFactory(new PropertyValueFactory<>("tiernachname"));
        col_tierarzt.setCellValueFactory(new PropertyValueFactory<>("tierarztname"));
        col_angabe.setCellValueFactory(new PropertyValueFactory<>("angabe"));
        col_startzeit.setCellValueFactory(new PropertyValueFactory<>("startzeit"));


        LocalDate today = LocalDate.now();
        Date date = java.sql.Date.valueOf(today);
        FilteredList<Termin> filteredData = new FilteredList<>(oblist, p -> p.getDate().equals(date));
        terminTable.setItems(filteredData.sorted());

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
