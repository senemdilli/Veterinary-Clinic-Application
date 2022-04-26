package tierklinik;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private ListView<String> myListView;

    @FXML
    private Label myLabel;

    String[] personal = {"senem", "leyla", "gupse"};
    String currentPersonal;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        myListView.getItems().addAll(personal);
        myListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentPersonal =myListView.getSelectionModel().getSelectedItem();
                myLabel.setText(currentPersonal);
            }
        });
    }
}