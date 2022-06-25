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

public class AddPersonalController implements Initializable {

    @FXML
    private TextField addId;
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
    String query2 = null;
    Connection connection = null;
    PreparedStatement preparedStatement;
    private boolean update;
    int id;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save() {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(addId.getText());
        String name = addName.getText();
        String nachname = addNachname.getText();
        int telefonnummer = Integer.parseInt(addTel.getText());
        String email = addEmail.getText();
        String adresse = addAdresse.getText();
        String arbeit = addArbeit.getText();
        int personalnummer = Integer.parseInt(addPnummer.getText());
        double gehalt = Double.parseDouble(addGehalt.getText());

        if(name.isEmpty() || nachname.isEmpty() || Integer.toString(telefonnummer).isEmpty() || email.isEmpty() || adresse.isEmpty() ||
        arbeit.isEmpty() || Integer.toString(personalnummer).isEmpty()|| Double.toString(gehalt).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            getQuery();
            insertPerson();
            insertPersonal();
            clean();
        }
    }

    private void getQuery() {

        if (!update) {
            query = "INSERT INTO person ('name', 'nachname', id , 'adresse', telefonnummer , 'email') VALUES(?,?,?,?,?,?)";
            query2 = "INSERT INTO personal ( id, personalnummer, gehalt, 'arbeit') VALUES(?,?,?,?)";
        } else {
            query = "UPDATE 'person' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'id' = ?,"
                    + "'adresse' =?,"
                    + "'telefonnummer' =?,"
                    + "'email' =? WHERE id ='" + id + "'";
            query2 = "UPDATE 'personal' SET"
                    + "'id' = ?,"
                    + "'personalnummer' =?,"
                    + "'gehalt' =?,"
                    + "'arbeit' =? WHERE id = '" + id + "'";
        }

    }

    private void insertPerson() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, String.valueOf(addId.getText()));
            preparedStatement.setString(1, addName.getText());
            preparedStatement.setString(2, addNachname.getText());
            preparedStatement.setString(5, String.valueOf(addTel.getText()));
            preparedStatement.setString(6, addEmail.getText());
            preparedStatement.setString(4, addAdresse.getText());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertPersonal() {

        try {
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(addId.getText()));
            preparedStatement.setString(4, addArbeit.getText());
            preparedStatement.setString(2, String.valueOf(addPnummer.getText()));
            preparedStatement.setString(3, String.valueOf(addGehalt.getText()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clean() {
        addId.setText(null);
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
        addId.setText(String.valueOf(id));
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
