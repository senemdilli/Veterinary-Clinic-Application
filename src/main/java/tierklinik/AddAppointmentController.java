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
import java.text.ParseException;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    @FXML
    private ChoiceBox<String> addTier;
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
    int tierid;
    String tiername, nachname, hbname;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            terminid = FullDB.getTerminId();
            getTierarztname();
            getTierinfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getTierinfo() throws SQLException {
        ResultSet resultSet = FullDB.getTierinfo();
        while (resultSet.next()) {
            addTier.getItems().add(resultSet.getString("hbname") + " | " + resultSet.getString("name") + " | " + resultSet.getString("nachname"));
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
    private void save() throws SQLException{
        if(addTierarztname.getValue().isEmpty() || addAngabe.getText().isEmpty() || addDate.getText().isEmpty() ||
                addStartzeit.getText().isEmpty()|| addEndezeit.getText().isEmpty() || addTier.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            String[] tierinfo = addTier.getValue().split("\\|");
            Termin termin = new Termin(tierinfo[1],tierinfo[2],addTierarztname.getValue());
            termin.setAngabe(addAngabe.getText());
            termin.setDate(addDate.getText());
            termin.setStartzeit(addStartzeit.getText());
            termin.setEndezeit(addEndezeit.getText());
            termin.setHbname(tierinfo[0]);

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
        addAngabe.setText(null);
        addTierarztname.setValue(null);
        addTier.setValue(null);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}
