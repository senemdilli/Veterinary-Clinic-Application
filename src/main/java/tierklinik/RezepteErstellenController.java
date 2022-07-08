package tierklinik;

import Classes.Rezepte;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RezepteErstellenController implements Initializable {

    @FXML
    private TextField addTierId;
    @FXML
    private TextField addTiername;
    @FXML
    private TextField addNachname;
    @FXML
    private TextField addMedizin;
    @FXML
    private TextField addDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void create() throws SQLException {
        if(addTierId.getText().isEmpty() || addNachname.getText().isEmpty() || addTiername.getText().isEmpty() || addMedizin.getText().isEmpty() || addDate.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Bitte füllen Sie alle Daten aus.");
            alert.showAndWait();
        } else {
            Rezepte rezept = new Rezepte(Integer.parseInt(addTierId.getText()), addMedizin.getText());
            rezept.setNachaname(addNachname.getText());
            rezept.setTierName(addTiername.getText());
            rezept.setMedizin(addMedizin.getText());

            clean();
        }
    }

    private void clean() {
        addTierId.setText(null);
        addNachname.setText(null);
        addTiername.setText(null);
        addMedizin.setText(null);
        addDate.setText(null);
    }

}
