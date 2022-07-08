package tierklinik;

import Classes.Zahlung;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddZahlungController implements Initializable {

    @FXML
    private ChoiceBox<String> addZahlungsart;
    @FXML
    private TextField addZahlungsbetrag;
    @FXML
    private ChoiceBox<String> addTier;

    private final String[] zahlungsarten = {"Bar", "Kreditkarte", "Anweisung"};
    private boolean update;
    int zahlungid;
    int tierid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            zahlungid = FullDB.getZahlungid();
            getTierinfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addZahlungsart.getItems().addAll(zahlungsarten);
    }

    @FXML
    public void getTierinfo() throws SQLException {
        ResultSet resultSet = FullDB.getTierinfo();
        while (resultSet.next()) {
            addTier.getItems().add(resultSet.getInt("tierid") + " | " + resultSet.getString("name") + " | " + resultSet.getString("nachname"));
            tierid =  resultSet.getInt("tierid");
        }
    }

    @FXML
    private void save() throws SQLException {

        if(addZahlungsbetrag.getText().isEmpty() || addZahlungsart.getValue().isEmpty() || addTier.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            Zahlung zahlung;
            zahlung = new Zahlung(addZahlungsart.getValue(), Double.parseDouble(addZahlungsbetrag.getText()), tierid);
            FullDB.getZahlungQuery(zahlung);
            FullDB.insertZahlung(zahlung);
            clean();
        }
    }

    @FXML
    private void clean() {
        addZahlungsart.setValue(null);
        addZahlungsbetrag.setText(null);
        addTier.setValue(null);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }
}
