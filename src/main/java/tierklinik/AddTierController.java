package tierklinik;

import Classes.Personal;
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
    private void save(MouseEvent event) {
        try {
            connection = FullDB.connect();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int id = Integer.parseInt(addTierId.getText());
        String name = addTierName.getText();
        String nachname = addNachname.getText();
        int telefonnummer = Integer.parseInt(addTel.getText());
        String email = addEmail.getText();
        String adresse = addAdresse.getText();
        String hbname = addHBName.getText();
        int hbid = Integer.parseInt(addHBId.getText());
        double kontostand = Double.parseDouble(addKontostand.getText());

        if(name.isEmpty() || nachname.isEmpty() || Integer.toString(telefonnummer).isEmpty() || email.isEmpty() || adresse.isEmpty() ||
                hbname.isEmpty() || Integer.toString(hbid).isEmpty()|| Double.toString(kontostand).isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            getQuery();
            insertPerson();
            insertTier();
            clean();
        }
    }

    private void getQuery() {

        if (!update) {
            query = "INSERT INTO person ('name', 'nachname', id , 'adresse', telefonnummer , 'email') VALUES(?,?,?,?,?,?)";
            query2 = "INSERT INTO tier ( tierid, 'hbname', hbid, kontostand) VALUES(?,?,?,?)";
        } else {
            query = "UPDATE 'person' SET"
                    + "'name' =?,"
                    + "'nachname' =?,"
                    + "'id' = ?,"
                    + "'adresse' =?,"
                    + "'telefonnummer' =?,"
                    + "'email' =? WHERE id ='" + id + "'";
            query2 = "UPDATE 'tier' SET"
                    + "'tierid' = ?,"
                    + "'hbname' =?,"
                    + "'hbid' =?,"
                    + "'kontostand' =? WHERE id = '" + tierid + "'";
        }

    }

    private void insertPerson() {
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(3, String.valueOf(addTierId.getText()));
            preparedStatement.setString(1, addTierName.getText().toString());
            preparedStatement.setString(2, addNachname.getText().toString());
            preparedStatement.setString(5, String.valueOf(addTel.getText()));
            preparedStatement.setString(6, addEmail.getText().toString());
            preparedStatement.setString(4, addAdresse.getText().toString());
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void insertTier() {

        try {
            preparedStatement = connection.prepareStatement(query2);
            preparedStatement.setString(1, String.valueOf(addTierId.getText()));
            preparedStatement.setString(2, addHBName.getText().toString());
            preparedStatement.setString(3, String.valueOf(addHBId.getText()));
            preparedStatement.setString(4, String.valueOf(addKontostand.getText()));
            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
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
