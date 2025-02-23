package controller.sub;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class ManageReturnBookFormController {

    @FXML
    private Label LblLateDateCount;

    @FXML
    private Label LblLateDateCount1;

    @FXML
    private RadioButton RBBookID;

    @FXML
    private RadioButton RBMemberId;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colMemberId;

    @FXML
    private TableColumn<?, ?> colMemberName;

    @FXML
    private TableColumn<?, ?> colReturnDate;

    @FXML
    private TableColumn<?, ?> colrecordId;

    @FXML
    private Label lblbalance;

    @FXML
    private TableView<?> tblnotreturnbookrecord;

    @FXML
    private ToggleGroup textgroup;

    @FXML
    private JFXTextField txtEnterkeywordtosearch;

    @FXML
    private JFXTextField txtFineForoneday;

    @FXML
    private Label txtLatedatecount;

    @FXML
    void btnMarkAsReturnOnAction(ActionEvent event) {

    }

}
