package controller.sub;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageMemberFormController {

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableView<?> tblMember;

    @FXML
    private JFXTextField txtMemberAddress;

    @FXML
    private JFXTextField txtMemberContactNumber;

    @FXML
    private JFXTextField txtMemberEmail;

    @FXML
    private JFXTextField txtMemberId;

    @FXML
    private JFXTextField txtMemberName;

    @FXML
    void btnAddOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }



    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtMemberContactOnAction(ActionEvent event) {

    }

    @FXML
    void txtMemberEmailOnAction(ActionEvent event) {

    }

    @FXML
    void txtMemberIdOnAction(ActionEvent event) {

    }

    public void btnclearOnAction(ActionEvent actionEvent) {
    }
}
