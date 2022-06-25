package tierklinik;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
    private TextField addTierarztname;
    @FXML
    private TextField addAngabe;
    @FXML
    private TextField addDate;
    @FXML
    private TextField addStartzeit;
    @FXML
    private TextField addEndezeit;

    String query = null;
    Connection connection = null;
    PreparedStatement preparedStatement;
    private boolean update;
    int terminid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            terminid = TableControllerAppointment.getTerminId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void save() {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        String tierName = addTiername.getText();
        String tierNachname = addTiernachname.getText();
        String HBname = addHBname.getText();
        String tierarztName = addTierarztname.getText();
        String angabe = addAngabe.getText();
        String date = addDate.getText();
        String startzeit = addStartzeit.getText();
        String endezeit = addEndezeit.getText();

        if(tierName.isEmpty() || tierNachname.isEmpty() || HBname.isEmpty() || tierarztName.isEmpty() || angabe.isEmpty() ||
                date.isEmpty() || startzeit.isEmpty()|| endezeit.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            getQuery();
            insertTermin();
            clean();
        }
    }

    private void getQuery() {

        if (!update) {
            query = "INSERT INTO termin ('date', 'startzeit', 'endezeit', 'angabe', terminid, 'tiername', 'tiernachname', 'tierarztname', 'hbname', 'zustand') VALUES(?,?,?,?,?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'termin' SET"
                    + "'date' =?,"
                    + "'startzeit' =?,"
                    + "'endezeit' = ?,"
                    + "'angabe' =?,"
                    + "'terminid' =?,"
                    + "'tiername' =?,"
                    + "'tiernachname' =?,"
                    + "'tierarztname' =?,"
                    + "'hbname' =?,"
                    + "'zustand' = 'nicht' WHERE terminid ='" + terminid + "'";
        }

    }

    private void insertTermin() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(5, String.valueOf(terminid));
            preparedStatement.setString(1, addDate.getText());
            preparedStatement.setString(2, addStartzeit.getText());
            preparedStatement.setString(3, addEndezeit.getText());
            preparedStatement.setString(4, addAngabe.getText());
            preparedStatement.setString(6, addTiername.getText());
            preparedStatement.setString(7, addTiernachname.getText());
            preparedStatement.setString(8, addTierarztname.getText());
            preparedStatement.setString(9, addHBname.getText());
            preparedStatement.setString(10, "nicht");
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
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
        addTierarztname.setText(null);
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
        addTierarztname.setText(tierarztname);
        addHBname.setText(hbname);
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}
