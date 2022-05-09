package tierklinik;

import Classes.Person;
import Classes.Personal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class MainInterfaceController {

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement;
    Personal personal = null;
    Person person = null;

    @FXML
    private void getPersonalListe() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/TableList_Personal.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void getTierListe() {
        try {
            Parent parent = FXMLLoader.load(Main.class.getResource("/TableList_Tier.fxml"));
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.initStyle(StageStyle.UTILITY);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void getKalendarView() {

    }

    @FXML
    private void getZahlungView() {

    }
}
