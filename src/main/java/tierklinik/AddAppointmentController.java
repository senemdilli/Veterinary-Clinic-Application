package tierklinik;

import Classes.Termin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private TextField addTiername;
    @FXML
    private TextField addHBname;
    @FXML
    private TextField addTiernachname;
    @FXML
    private ChoiceBox<String> addTierarztname;
    @FXML
    private TextField addAngabe;
    @FXML
    private TextField addDate;
    @FXML
    private TextField addStartzeit;
    @FXML
    private TextField addEndezeit;
    private boolean update;
    int terminid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            terminid = FullDB.getTerminId();
            getTierarztname();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void getTierarztname() throws SQLException {
        ResultSet resultSet = FullDB.getTierarztname();
        while (resultSet.next()) {
            addTierarztname.getItems().add(resultSet.getString("name"));
        }
    }
    @FXML
    private void save() throws SQLException {
        if(addTiername.getText().isEmpty() || addTiernachname.getText().isEmpty() || addHBname.getText().isEmpty() || addTierarztname.getValue().isEmpty() || addAngabe.getText().isEmpty() ||
                addDate.getText().isEmpty() || addStartzeit.getText().isEmpty()|| addEndezeit.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            Termin termin = new Termin(addTiername.getText(),addTiernachname.getText(),addTierarztname.getValue());
            termin.setAngabe(addAngabe.getText());
            termin.setDate(addDate.getText());
            termin.setStartzeit(addStartzeit.getText());
            termin.setEndezeit(addEndezeit.getText());
            termin.setHbname(addHBname.getText());

            FullDB.getTerminQuery(termin);
            FullDB.insertTermin(termin);
            clean();
        }
    }

    @FXML
    private void clean() {
        addDate.setText(null);
        addStartzeit.setText(null);
        addEndezeit.setText(null);
        addTiername.setText(null);
        addAngabe.setText(null);
        addTiernachname.setText(null);
        addTierarztname.setValue(null);
        addHBname.setText(null);
    }

    void setTextField(int terminid, String date, String startzeit, String endezeit, String angabe, String tiername, String tiernachname, String tierarztname, String hbname) {
        this.terminid = terminid;
        addDate.setText(date);
        addStartzeit.setText(startzeit);
        addEndezeit.setText(endezeit);
        addAngabe.setText(angabe);
        addTiername.setText(tiername);
        addTiernachname.setText(tiernachname);
        addTierarztname.setValue(tierarztname);
        addHBname.setText(hbname);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}
