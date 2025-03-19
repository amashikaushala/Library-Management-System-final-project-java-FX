package controller.sub;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ManageBookFormController {

    @FXML
    private ComboBox<?> cmbSelectPublisher;

    @FXML
    private ComboBox<?> cmdMainCategory;

    @FXML
    private TableColumn<?, ?> colAuthorsName;

    @FXML
    private TableColumn<?, ?> colBookISBN;

    @FXML
    private TableColumn<?, ?> colBookId;

    @FXML
    private TableColumn<?, ?> colBookName;

    @FXML
    private TableColumn<?, ?> colBookPrice;

    @FXML
    private TableColumn<?, ?> colCategoryName;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TableView<?> tblAuthors;

    @FXML
    private TableView<?> tblBook;

    @FXML
    private TableView<?> tblSubCategories;

    @FXML
    private JFXTextField txtBookISBN;

    @FXML
    private JFXTextField txtBookId;

    @FXML
    private JFXTextField txtBookName;

    @FXML
    private JFXTextField txtPrice;

    @FXML
    void BookIdOnAction(ActionEvent event) {

    }

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
