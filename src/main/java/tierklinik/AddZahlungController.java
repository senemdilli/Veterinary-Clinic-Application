package tierklinik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddZahlungController implements Initializable {

    @FXML
    private ChoiceBox<String> addZahlungsart;
    @FXML
    private TextField addZahlungsbetrag;
    @FXML
    private ChoiceBox<String> addHbname;
    @FXML
    private ChoiceBox<String> addTiername;
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
            getHbname();
            getTiername();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        addZahlungsart.getItems().addAll(zahlungsarten);
    }

    @FXML
    public void getHbname() throws SQLException {
        query = "SELECT hbname FROM 'tier'";
        connection = FullDB.connect();
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            addHbname.getItems().add(resultSet.getString("hbname"));
        }
    }

    @FXML
    public void getTiername() throws SQLException {
        query = "SELECT name FROM 'person' WHERE id = (SELECT tierid FROM 'tier' WHERE hbname =" + addHbname.getValue() + ")";
        connection = FullDB.connect();
        preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            addTiername.getItems().add(resultSet.getString("name"));
        }
    }

    @FXML
    private void save() {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tiername = addTiername.getValue();
        String nachname = addNachname.getText();
        String hbname = addHbname.getValue();
        String zahlungsart = addZahlungsart.getValue();
        String zahlungsbetrag = addZahlungsbetrag.getText();

        if(tiername.isEmpty() || nachname.isEmpty() || hbname.isEmpty() || zahlungsart.isEmpty() || zahlungsbetrag.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
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
            preparedStatement.setString(3, addHbname.getValue());
            preparedStatement.setString(4, addTiername.getValue());
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
        addHbname.setValue(null);
        addTiername.setValue(null);
        addNachname.setText(null);
    }

    void setTextField(int zahlungid, String zahlungsart, Double zahlungsbetrag, String hbname, String tiername, String nachname) {
        this.zahlungid = zahlungid;
        addZahlungsart.setValue(zahlungsart);
        addZahlungsbetrag.setText(String.valueOf(zahlungsbetrag));
        addHbname.setValue(hbname);
        addTiername.setValue(tiername);
        addNachname.setText(nachname);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }
}
