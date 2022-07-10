package tierklinik;

import Classes.Personal;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
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
    private ChoiceBox<String> addArbeit;
    @FXML
    private TextField addPnummer;
    @FXML
    private TextField addGehalt;

    private final String[] arbeiten = {"Sekretär", "Tierarzt", "Anderen"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addArbeit.getItems().addAll(arbeiten);
    }

    @FXML
    private void save() {
        if(addId.getText().isEmpty() || addNachname.getText().isEmpty() || addTel.getText().isEmpty() || addEmail.getText().isEmpty() || addAdresse.getText().isEmpty() ||
        addArbeit.getValue().isEmpty() || addPnummer.getText().isEmpty()|| addGehalt.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            Personal personal = new Personal(Integer.parseInt(addId.getText()),addName.getText(), addNachname.getText());
            personal.setTelefonnummer(Integer.parseInt(addTel.getText()));
            personal.setEmail(addEmail.getText());
            personal.setAdresse(addAdresse.getText());
            personal.setArbeit(addArbeit.getValue());
            personal.setPersonalnummer(Integer.parseInt(addPnummer.getText()));
            personal.setGehalt(Double.parseDouble(addGehalt.getText()));

            FullDB.getPersonalQuery(personal);
            FullDB.insertPerson(personal);
            FullDB.insertPersonal(personal);
            clean();
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
        addArbeit.setValue(null);
        addEmail.setText(null);
        addGehalt.setText(null);
    }
}
