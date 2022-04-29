package tierklinik;

import Classes.Person;
import Classes.Personal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.w3c.dom.events.MouseEvent;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddPersonalController implements Initializable {

    @FXML
    private TextField addName;
    @FXML
    private TextField addNachname;
    @FXML
    private TextField addTel;
    @FXML
    private TextField addEmail;
    @FXML
    private TextField addAdresse;
    @FXML
    private TextField addArbeit;
    @FXML
    private TextField addPnummer;
    @FXML
    private TextField addGehalt;

    String query = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Personal personal = null;
    private boolean update;
    int personalnummer;

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

        //int id = addId.
        String name = addName.getText();
        String nachname = addNachname.getText();
        Integer telefonnummer = Integer.valueOf(addTel.getText());
        String email = addEmail.getText();
        String adress = addAdresse.getText();
        String arbeit = addArbeit.getText();
        Integer personalnummer = Integer.valueOf(addPnummer.getText());
        Double gehalt = Double.valueOf(addGehalt.getText());

        if(name.isEmpty() || nachname.isEmpty() || telefonnummer.toString().isEmpty() || email.isEmpty() || adress.isEmpty() ||
        arbeit.isEmpty() || personalnummer.toString().isEmpty()|| gehalt.toString().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            getQuery();
            insert();
            clean();
        }
    }

    private void getQuery() {

        if (!update) {
            query = "INSERT INTO 'personal' ('name', 'nachname', 'telefonnummer', 'email', 'adress', 'arbeit', 'personalnummer', 'gehalt') " +
                    "VALUES(?,?,?,?,?,?,?,?)";
        } else {
            query = "UPDATE 'personal' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'telefonnummer' =?,"
                    + "'email' =?,"
                    + "'adress' =?,"
                    + "'arbeit' =?,"
                    + "'personalnummer' =?,"
                    + "'gehalt' =? WHERE personalnummer = '" + personalnummer + "'";
        }

    }

    private void insert() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, addName.getText());
            preparedStatement.setString(2, addNachname.getText());
            preparedStatement.setString(3, addTel.getText());
            preparedStatement.setString(4, addEmail.getText());
            preparedStatement.setString(5, addAdresse.getText());
            preparedStatement.setString(6, addArbeit.getText());
            preparedStatement.setString(7, addPnummer.getText());
            preparedStatement.setString(8, addGehalt.getText());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clean() {
        addName.setText(null);
        addNachname.setText(null);
        addAdresse.setText(null);
        addTel.setText(null);
        addPnummer.setText(null);
        addArbeit.setText(null);
        addEmail.setText(null);
        addGehalt.setText(null);
    }

    void setTextField(int id, String name, String nachname, Integer telefonnummer, String email, String adresse, String arbeit, Integer personalnummer, Double gehalt) {
        //addId.
        addName.setText(name);
        addNachname.setText(nachname);
        addAdresse.setText(adresse);
        addTel.setText(String.valueOf(telefonnummer));
        addPnummer.setText(String.valueOf(personalnummer));
        addArbeit.setText(arbeit);
        addEmail.setText(email);
        addGehalt.setText(String.valueOf(gehalt));
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}
