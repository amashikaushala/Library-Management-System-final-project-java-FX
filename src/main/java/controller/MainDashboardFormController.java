package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.io.IOException;

import static javafx.fxml.FXMLLoader.load;

public class MainDashboardFormController {


    public AnchorPane mainpain;
    @FXML
    private Text txtaddabooktoyourcollection;

    @FXML
    private Text txtmanageallyourbooks;

    @FXML
    void btnBorrowBookRecordOnAction(ActionEvent event) {
loadUI("manage_borrow_book_Form.fxml");
    }

    @FXML
    void btnManageAuthorsandPublishersOnAction(ActionEvent event)throws IOException {
loadUI("manage_authors and publishers_Form.fxml");
    }

    @FXML
    void btnManageBookOnAction(ActionEvent event) {
        loadUI("manage_book_Form.fxml");
    }

    @FXML
    void btnManageMembersOnAction(ActionEvent event) throws IOException {
loadUI("manage_member_Form.fxml");
    }



    @FXML
    void btnReturnBookRecordsOnAction(ActionEvent event) {
loadUI("manage_return_book_Form.fxml");
    }



    public void loadUI(String uiName){
        mainpain.getChildren().clear();
        try {
            Parent load = FXMLLoader.load(getClass().getResource("/view/sub/"+uiName));
            mainpain.getChildren().add(load);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR,"UI - Load Error Contact Developer").show();
            e.printStackTrace();
        }
    }

}
