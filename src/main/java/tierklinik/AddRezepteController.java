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
    private ChoiceBox<String> addTier;
    @FXML
    private TextField addMedizin;
    @FXML
    //private TextField addDate;
    int terminid;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            terminid = FullDB.getRezeptId();
            getTierinfo();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getTierinfo() throws SQLException {
        ResultSet resultSet = FullDB.getTierinfo();
        while (resultSet.next()) {
            addTier.getItems().add(resultSet.getString("tierid") + "| " + resultSet.getString("name") + " | " + resultSet.getString("nachname"));
        }
    }
    @FXML
    private void save() throws SQLException {
        if(addTier.getValue().isEmpty() || addMedizin.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Fehler");
            alert.setHeaderText("Bitte alle Felder ausfüllen!");
            alert.showAndWait();
        } else {
            String[] tierinfo = addTier.getValue().split("\\|");
            Rezepte rezept = new Rezepte(Integer.parseInt(tierinfo[0]),addMedizin.getText());
            rezept.setNachaname(tierinfo[2]);
            rezept.setTiername(tierinfo[1]);

            FullDB.getRezepteQuery(rezept);
            FullDB.insertRezepte(rezept);
            clean();
        }
    }

    @FXML
    private void clean() {
        //addDate.setText(null);
        addMedizin.setText(null);
        addTier.setValue(null);
    }

}
