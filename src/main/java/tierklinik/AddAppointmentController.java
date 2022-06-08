package tierklinik;

import Classes.Termin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Termin termin = null;
    private boolean update;
    int terminid = Termin.getTerminid();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save(MouseEvent event) {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println(terminid);
        int terminid = Termin.getTerminid();
        String tieNname = addTiername.getText();
        String tierNachname = addTiernachname.getText();
        String HBname = addHBname.getText();
        String tierarztName = addTierarztname.getText();
        String angabe = addAngabe.getText();
        String date = addDate.getText();
        String startzeit = addStartzeit.getText();
        String endezeit = addEndezeit.getText();

        if(Integer.toString(terminid).isEmpty() || tieNname.isEmpty() || tierNachname.isEmpty() || HBname.isEmpty() || tierarztName.isEmpty() || angabe.isEmpty() ||
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
            query = "INSERT INTO termin ('date', 'startzeit', 'endezeit', 'angabe', terminid, 'tiername', 'tiernachname', 'tierarztname', 'hbname') VALUES(?,?,?,?,?,?,?,?,?)";
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
                    + "'hbname' =? WHERE terminid ='" + terminid + "'";
        }

    }

    private void insertTermin() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(5, String.valueOf(terminid));
            preparedStatement.setString(1, addDate.getText().toString());
            preparedStatement.setString(2, addStartzeit.getText().toString());
            preparedStatement.setString(3, addEndezeit.getText().toString());
            preparedStatement.setString(4, addAngabe.getText().toString());
            preparedStatement.setString(6, addTiername.getText().toString());
            preparedStatement.setString(7, addTiernachname.getText().toString());
            preparedStatement.setString(8, addTierarztname.getText().toString());
            preparedStatement.setString(9, addHBname.getText().toString());
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
