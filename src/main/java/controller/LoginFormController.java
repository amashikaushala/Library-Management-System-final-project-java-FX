package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class LoginFormController {

    @FXML
    private JFXPasswordField pwpassword;

    @FXML
    private JFXTextField txtusername;

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage window=(Stage) pwpassword.getScene().getWindow();
        window.close();
        Stage stage=new Stage();

        try {

            Parent load= FXMLLoader.load(getClass().getResource("/view/main_dashboardForm.fxml"));
            Scene scene=new Scene(load);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
           new Alert(Alert.AlertType.ERROR,"Failed to load Form-Content Developer").show();
           e.printStackTrace();
        }
    }

}
