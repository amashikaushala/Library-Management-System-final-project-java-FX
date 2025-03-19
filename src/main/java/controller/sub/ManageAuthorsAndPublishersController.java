package controller.sub;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageAuthorsAndPublishersController {

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colContactNumberAuthor;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colIdAuthor;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colNameAuthor;

    @FXML
    private TableView<?> tblPublishers;

    @FXML
    private TableView<?> tblPublishers1;

    @FXML
    private JFXTextField tctPublisherContactNumber;

    @FXML
    private JFXTextField txtAuthorContact;

    @FXML
    private JFXTextField txtAuthorId;

    @FXML
    private JFXTextField txtAuthorName;

    @FXML
    private JFXTextField txtPublisherId;

    @FXML
    private JFXTextField txtPublisherLocation;

    @FXML
    private JFXTextField txtPublisherName;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

}
