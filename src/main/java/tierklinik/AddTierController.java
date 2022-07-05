package tierklinik;

import Classes.Personal;
import Classes.Tier;
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

    String query = null;
    String query2 = null;
    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Personal personal = null;
    private boolean update;
    int id, tierid;

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
            tier.setHbName(addHBName.getText());
            tier.setHBId(Integer.parseInt(addHBId.getText()));
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

    void setTextField(Integer tierid, String tiername, String nachname, Integer telefonnummer, String email, String adresse, String hbname, Integer hbid, Double kontostand) {
        addTierId.setText(String.valueOf(tierid));
        addTierName.setText(tiername);
        addNachname.setText(nachname);
        addAdresse.setText(adresse);
        addTel.setText(String.valueOf(telefonnummer));
        addHBName.setText(String.valueOf(hbname));
        addHBId.setText(String.valueOf(hbid));
        addEmail.setText(email);
        addKontostand.setText(String.valueOf(kontostand));
    }

    void setUpdate(boolean b) {
        this.update = b;
    }

}
