import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {

             Parent load = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));
            Scene scene=new Scene(load);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"Failed to load the Form - contact Developer").show();
                    e.printStackTrace();
        }
    }
}
