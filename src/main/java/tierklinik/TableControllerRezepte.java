package tierklinik;

import Classes.Rezepte;
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
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class TableControllerRezepte implements Initializable {
    @FXML
    private TableView<Rezepte> table;
    @FXML
    private TableColumn<Rezepte,Integer> col_id;
    @FXML
    private TableColumn<Rezepte,String> col_name;
    @FXML
    private TableColumn<Rezepte,String> col_nachname;
    @FXML
    private TableColumn<Rezepte,Integer> col_medizin;
    @FXML
    private TableColumn<Rezepte, Date> col_date;
    ObservableList<Rezepte> oblist = FXCollections.observableArrayList();

    @FXML
    private void getAddView() {
        try {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("/addRezepte.fxml")));
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
        oblist = FullDB.getRezepteDB();
        table.setItems(oblist);
    }

    @FXML
    private void loadDate() {
        refreshTable();

        col_id.setCellValueFactory(new PropertyValueFactory<>("tierid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("tiername"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_medizin.setCellValueFactory(new PropertyValueFactory<>("medizin"));
        col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        table.setItems(oblist);

    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadDate();
    }
}
