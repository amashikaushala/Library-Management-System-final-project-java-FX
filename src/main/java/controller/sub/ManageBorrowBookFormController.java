package controller.sub;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageBorrowBookFormController {

    @FXML
    private DatePicker bpBorrowDate;

    @FXML
    private DatePicker bpReturnDate;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBorrowdate;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableView<?> tblCart;

    @FXML
    private JFXTextField txtBookISBN;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtMainCategory;

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
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnConfirmOnAction(ActionEvent event) {

    }

    @FXML
    void txtBookIdOnAction(ActionEvent event) {

    }

    @FXML
    void txtBookIsbnOnAction(ActionEvent event) {

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

}
