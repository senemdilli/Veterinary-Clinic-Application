package tierklinik;

import Classes.Rezepte;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddRezepteController implements Initializable {

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
    private boolean update;
    int terminid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            terminid = FullDB.getRezeptId();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void save() throws SQLException {
        if(addTierId.getText().isEmpty() || addTiername.getText().isEmpty() || addNachname.getText().isEmpty() ||
                addMedizin.getText().isEmpty() || addDate.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            Rezepte rezept = new Rezepte(Integer.parseInt(addTierId.getText()),addMedizin.getText());
            rezept.setNachaname(addNachname.getText());
            rezept.setTierName(addTiername.getText());
            rezept.setDate(addDate.getText());

            FullDB.getRezepteQuery(rezept);
            FullDB.insertRezepte(rezept);
            clean();
        }
    }

    @FXML
    private void clean() {
        addDate.setText(null);
        addMedizin.setText(null);
        addNachname.setText(null);
        addTiername.setText(null);
        addTierId.setText(null);
    }
    void setUpdate(boolean b) {
        this.update = b;
    }

}
