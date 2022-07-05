package tierklinik;

import Classes.Person;
import Classes.Tier;
import Classes.Zahlung;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddZahlungController implements Initializable {

    @FXML
    private TableView<Tier> zahlungTable;
    @FXML
    private TableColumn<Tier, Integer> col_tierid;
    @FXML
    private TableColumn<Person,String> col_tiername;
    @FXML
    private TableColumn<Tier,String> col_hbname;
    @FXML
    private TableColumn<Person,String> col_nachname;
    @FXML
    private ChoiceBox<String> addZahlungsart;
    @FXML
    private TextField addZahlungsbetrag;
    @FXML
    private ChoiceBox<Integer> addTierid;
    @FXML
    private TextField addHbname;
    @FXML
    private TextField addTiername;
    @FXML
    private TextField addNachname;

    private final String[] zahlungsarten = {"Bar", "Kreditkarte", "Anweisung"};
    ObservableList<Tier> oblist = FXCollections.observableArrayList();
    Connection connection = null;
    private boolean update;
    int zahlungid;

    String tiername;
    String nachname;
    String hbname;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //connection = FullDB.connect();
            zahlungid = FullDB.getZahlungid();
            loadData();
            getTierid();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addZahlungsart.getItems().addAll(zahlungsarten);
    }

    private void getTier() throws SQLException {
        ResultSet resultSet = FullDB.getTier();
        while (resultSet.next()) {
            tiername = resultSet.getString("name");
            hbname = resultSet.getString("hbname");
            nachname = resultSet.getString("nachname");
        }
    }

    @FXML
    private void refreshTable() {
        oblist = FullDB.getTierDB();
        zahlungTable.setItems(oblist);
    }

    @FXML
    public void loadData() {
        refreshTable();

        col_tierid.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_tiername.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_nachname.setCellValueFactory(new PropertyValueFactory<>("nachname"));
        col_hbname.setCellValueFactory(new PropertyValueFactory<>("hbname"));

        zahlungTable.setItems(oblist);
    }

    @FXML
    public void getTierid() throws SQLException {
        ResultSet resultSet = FullDB.getTierid();
        while (resultSet.next()) {
            addTierid.getItems().add(resultSet.getInt("tierid"));
        }
    }
    @FXML
    private void save() throws SQLException {

        if(addZahlungsbetrag.getText().isEmpty() || addZahlungsart.getValue().isEmpty() || addTierid.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            Zahlung zahlung = new Zahlung(addZahlungsart.getValue(), Double.parseDouble(addZahlungsbetrag.getText()), addNachname.getText(), addHbname.getText(), addTiername.getText());
            FullDB.getZahlungQuery(zahlungid);
            FullDB.insertZahlung(zahlung);
            clean();
        }
    }

    @FXML
    private void clean() {
        addZahlungsart.setValue(null);
        addZahlungsbetrag.setText(null);
        /* addHbname.setValue(null);
        addTiername.setValue(null);
        addNachname.setText(null); */
    }

    void setTextField(int zahlungid, String zahlungsart, Double zahlungsbetrag, String hbname, String tiername, String nachname) {
        this.zahlungid = zahlungid;
        addZahlungsart.setValue(zahlungsart);
        addZahlungsbetrag.setText(String.valueOf(zahlungsbetrag));
        /* addHbname.setValue(hbname);
        addTiername.setValue(tiername);
        addNachname.setText(nachname); */
    }

    void setUpdate(boolean b) {
        this.update = b;
    }
}
