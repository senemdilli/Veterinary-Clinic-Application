package tierklinik;

import Classes.Personal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
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
    private boolean update;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save() {
        if(addId.getText().isEmpty() || addNachname.getText().isEmpty() || addTel.getText().isEmpty() || addEmail.getText().isEmpty() || addAdresse.getText().isEmpty() ||
        addArbeit.getText().isEmpty() || addPnummer.getText().isEmpty()|| addGehalt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            Personal personal = new Personal(Integer.parseInt(addId.getText()),addName.getText(), addNachname.getText());
            personal.setTelefonnummer(Integer.parseInt(addTel.getText()));
            personal.setEmail(addEmail.getText());
            personal.setAdresse(addAdresse.getText());
            personal.setArbeit(addArbeit.getText());
            personal.setPersonalnummer(Integer.parseInt(addPnummer.getText()));
            personal.setGehalt(Double.parseDouble(addGehalt.getText()));

            FullDB.getPersonalQuery(personal);
            FullDB.insertPerson(personal);
            FullDB.insertPersonal(personal);
            clean();
        }
    }
 /*
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

    } */
/*

    private void insertPerson() {
        FullDB.insertPerson(personal);
    }

    private void insertPersonal() {
        FullDB.insertPersonal(personal);
    } */

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
