package tierklinik;

import Classes.Tier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTierController implements Initializable {

    @FXML
    private TextField addTierId;
    @FXML
    private TextField addTierName;
    @FXML
    private TextField addHBName;
    @FXML
    private TextField addNachname;
    @FXML
    private TextField addHBId;
    @FXML
    private TextField addTel;
    @FXML
    private TextField addEmail;
    @FXML
    private TextField addAdresse;
    @FXML
    private TextField addKontostand;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void save() {
        if(addTierName.getText().isEmpty() || addNachname.getText().isEmpty() || addTel.getText().isEmpty() || addEmail.getText().isEmpty() || addAdresse.getText().isEmpty() ||
                addHBName.getText().isEmpty() || addHBId.getText().isEmpty()|| addKontostand.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            Tier tier = new Tier(Integer.parseInt(addTierId.getText()),addTierName.getText(), addNachname.getText());
            tier.setTelefonnummer(Integer.parseInt(addTel.getText()));
            tier.setEmail(addEmail.getText());
            tier.setAdresse(addAdresse.getText());
            tier.setHbname(addHBName.getText());
            tier.setHBid(Integer.parseInt(addHBId.getText()));
            tier.setKontostand(Double.parseDouble(addKontostand.getText()));

            FullDB.getTierQuery(tier);
            FullDB.insertTier(tier);
            clean();
        }
    }

    @FXML
    private void clean() {
        addTierId.setText(null);
        addTierName.setText(null);
        addNachname.setText(null);
        addAdresse.setText(null);
        addTel.setText(null);
        addHBName.setText(null);
        addHBId.setText(null);
        addEmail.setText(null);
        addKontostand.setText(null);
    }

}
