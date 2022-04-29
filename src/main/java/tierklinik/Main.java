package tierklinik;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/TableList_Personal.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Image icon = new Image("png/pet.png");
            stage.getIcons().add(icon);
            stage.setTitle("Tierklinik System");
            stage.setWidth(1366);
            stage.setHeight(768);

            stage.setScene(scene);
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        launch(args);
    }
}