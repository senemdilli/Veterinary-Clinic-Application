package tierklinik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddZahlungController implements Initializable {

    @FXML
    private ChoiceBox<String> addZahlungsart;
    @FXML
    private TextField addZahlungsbetrag;
    @FXML
    private TextField addHbname;
    @FXML
    private TextField addTiername;
    @FXML
    private TextField addNachname;

    private final String[] zahlungsarten = {"Bar", "Kreditkarte", "Anweisung"};

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement;
    private boolean update;
    int zahlungid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            zahlungid = TableControllerKontenstelle.getZahlungid();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addZahlungsart.getItems().addAll(zahlungsarten);
    }

    @FXML
    private void save() {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tiername = addTiername.getText();
        String nachname = addNachname.getText();
        String hbname = addHbname.getText();
        String zahlungsart = addZahlungsart.getValue();
        double zahlungsbetrag = Double.parseDouble(addZahlungsbetrag.getText());

        if(tiername.isEmpty() || nachname.isEmpty() || hbname.isEmpty() || zahlungsart.isEmpty() || Double.isNaN(zahlungsbetrag)) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            getQuery();
            insertZahlung();
            clean();
        }
    }

    private void getQuery() {

        if (!update) {
            query = "INSERT INTO zahlung ('zahlungsart', 'zahlungsbetrag', 'hbname', 'tiername', 'nachname', 'zustand', 'zahlungid') VALUES(?,?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'zahlung' SET"
                    + "'zahlungsart' =?,"
                    + "'zahlungsbetrag' =?,"
                    + "'hbname' = ?,"
                    + "'tiername' =?,"
                    + "'nachname' =?,"
                    + "'zustand' =?"
                    + "'zahlungid' =? WHERE zahlungid ='" + zahlungid + "'";
        }

    }

    private void insertZahlung() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(7, String.valueOf(zahlungid));
            preparedStatement.setString(1, addZahlungsart.getValue());
            preparedStatement.setString(2, addZahlungsbetrag.getText());
            preparedStatement.setString(3, addHbname.getText());
            preparedStatement.setString(4, addTiername.getText());
            preparedStatement.setString(5, addNachname.getText());
            preparedStatement.setString(6, "nicht");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clean() {
        addZahlungsart.setValue(null);
        addZahlungsbetrag.setText(null);
        addHbname.setText(null);
        addTiername.setText(null);
        addNachname.setText(null);
    }

    void setTextField(int zahlungid, String zahlungsart, Double zahlungsbetrag, String hbname, String tiername, String nachname) {
        this.zahlungid = zahlungid;
        addZahlungsart.setValue(zahlungsart);
        addZahlungsbetrag.setText(zahlungsbetrag.toString());
        addHbname.setText(hbname);
        addTiername.setText(tiername);
        addNachname.setText(nachname);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }
}
