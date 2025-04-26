package controller.sub;

import com.jfoenix.controls.JFXTextField;
import dto.custom.BookDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import service.custom.BookService;
import service.custom.impl.BookServiceIMPL;
import util.exception.ServiceException;
import util.exception.custom.BookException;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ManageBookFormController implements Initializable {

    @FXML
    private ComboBox<Integer> cmbSelectPublisher;

    @FXML
    private ComboBox<Integer> cmdMainCategory;

    @FXML
    private TableColumn<?, ?> colAuthorsName;

    @FXML
    private TableColumn<BookDTO, String> colBookISBN;

    @FXML
    private TableColumn<BookDTO, Integer> colBookId;

    @FXML
    private TableColumn<BookDTO, String> colBookName;

    @FXML
    private TableColumn<BookDTO, Double> colBookPrice;

    @FXML
    private TableColumn<?, ?> colCategoryName;

    @FXML
    private TableColumn<?, ?> colOption;

    @FXML
    private TableView<?> tblAuthors;

    @FXML
    private TableView<BookDTO> tblBook;

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

    private final BookService bookService = new BookServiceIMPL();
    private ObservableList<BookDTO> bookList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadAllBooks();
        setCellValueFactory();
        loadPublishersAndCategories();
    }

    private void loadPublishersAndCategories() {
        // Load publishers and categories - you would typically get these from a service
        ObservableList<Integer> publisherIds = FXCollections.observableArrayList(1, 2, 3);
        cmbSelectPublisher.setItems(publisherIds);

        ObservableList<Integer> categoryIds = FXCollections.observableArrayList(1, 2, 3);
        cmdMainCategory.setItems(categoryIds);
    }

    private void setCellValueFactory() {
        colBookId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colBookName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBookISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        colBookPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    }

    private void loadAllBooks() {
        try {
            List<BookDTO> all = bookService.getAll();
            bookList = FXCollections.observableArrayList(all);
            tblBook.setItems(bookList);
        } catch (ServiceException e) {
            // Show a user-friendly error message
            showErrorAlert("Failed to load books", e);
        }
    }

    @FXML
    void BookIdOnAction(ActionEvent event) {
        if (txtBookId.getText().isEmpty()) {
            showErrorAlert("Book ID cannot be empty", null);
            return;
        }

        try {
            int id = Integer.parseInt(txtBookId.getText());
            Optional<BookDTO> bookOptional = bookService.search(id);

            if (bookOptional.isPresent()) {
                BookDTO book = bookOptional.get();
                populateFields(book);
            } else {
                clearFields();
                showInfoAlert("Book not found", "No book found with ID: " + id);
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid ID format", null);
        } catch (ServiceException e) {
            showErrorAlert("Error searching for book", e);
        }
    }

    private void populateFields(BookDTO book) {
        txtBookName.setText(book.getName());
        txtBookISBN.setText(book.getIsbn());
        txtPrice.setText(String.valueOf(book.getPrice()));
        cmbSelectPublisher.setValue(book.getPublisherId());
        cmdMainCategory.setValue(book.getMainCategoryId());
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        if (txtBookId.getText().isEmpty()) {
            showErrorAlert("Book ID cannot be empty", null);
            return;
        }

        try {
            int id = Integer.parseInt(txtBookId.getText());

            // Confirm deletion
            if (confirmAction("Delete Book", "Are you sure you want to delete this book?")) {
                bookService.delete(id);
                showSuccessAlert("Book deleted successfully");
                loadAllBooks();
                clearFields();
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Invalid ID format", null);
        } catch (BookException e) {
            showErrorAlert("Error deleting book", e);
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        if (!validateInputs()) {
            return;
        }

        try {
            BookDTO bookDTO = createBookDTOFromFields();

            try {
                bookService.save(bookDTO);
                showSuccessAlert("Book saved successfully");
                loadAllBooks();
                clearFields();
            } catch (BookException e) {
                if (e.getMessage().contains("ID Already Exists")) {
                    showErrorAlert("Book ID already exists. Please use a different ID.", null);
                } else if (e.getMessage().contains("Data is To Large")) {
                    showErrorAlert("Data too large for field. Please shorten your input.", null);
                } else {
                    showErrorAlert("Error saving book", e);
                }
            }
        } catch (Exception e) {
            showErrorAlert("Error creating book", e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        if (!validateInputs()) {
            return;
        }

        try {
            int id = Integer.parseInt(txtBookId.getText());

            // Check if book exists
            Optional<BookDTO> existingBook = bookService.search(id);
            if (!existingBook.isPresent()) {
                showErrorAlert("Book not found", null);
                return;
            }

            BookDTO bookDTO = createBookDTOFromFields();
            // Preserve the author from existing book
            bookDTO.setAuthor(existingBook.get().getAuthor());

            try {
                bookService.update(bookDTO);
                showSuccessAlert("Book updated successfully");
                loadAllBooks();
                clearFields();
            } catch (BookException e) {
                if (e.getMessage().contains("Data is To Large")) {
                    showErrorAlert("Data too large for field. Please shorten your input.", null);
                } else {
                    showErrorAlert("Error updating book", e);
                }
            }
        } catch (BookException e) {
            showErrorAlert("Error searching for book", e);
        } catch (Exception e) {
            showErrorAlert("Error updating book", e);
        }
    }

    private BookDTO createBookDTOFromFields() {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(Integer.parseInt(txtBookId.getText()));
        bookDTO.setName(txtBookName.getText());
        bookDTO.setIsbn(txtBookISBN.getText());
        bookDTO.setPrice(Double.parseDouble(txtPrice.getText()));
        bookDTO.setPublisherId(cmbSelectPublisher.getValue());
        bookDTO.setMainCategoryId(cmdMainCategory.getValue());
        bookDTO.setAuthor("Unknown"); // Default value or add author field to UI
        return bookDTO;
    }

    private boolean validateInputs() {
        if (txtBookId.getText().isEmpty()) {
            showErrorAlert("Book ID cannot be empty", null);
            return false;
        }

        if (txtBookName.getText().isEmpty()) {
            showErrorAlert("Book name cannot be empty", null);
            return false;
        }

        if (txtBookISBN.getText().isEmpty()) {
            showErrorAlert("ISBN cannot be empty", null);
            return false;
        }

        if (txtPrice.getText().isEmpty()) {
            showErrorAlert("Price cannot be empty", null);
            return false;
        }

        if (cmbSelectPublisher.getValue() == null) {
            showErrorAlert("Please select a publisher", null);
            return false;
        }

        if (cmdMainCategory.getValue() == null) {
            showErrorAlert("Please select a main category", null);
            return false;
        }

        try {
            Integer.parseInt(txtBookId.getText());
        } catch (NumberFormatException e) {
            showErrorAlert("Book ID must be a number", null);
            return false;
        }

        try {
            Double.parseDouble(txtPrice.getText());
        } catch (NumberFormatException e) {
            showErrorAlert("Price must be a number", null);
            return false;
        }

        return true;
    }

    private void clearFields() {
        txtBookId.clear();
        txtBookName.clear();
        txtBookISBN.clear();
        txtPrice.clear();
        cmbSelectPublisher.setValue(null);
        cmdMainCategory.setValue(null);
    }

    // Helper methods for showing alerts
    private void showErrorAlert(String message, Exception e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);

        if (e != null) {
            // Show a more user-friendly message
            if (e.getMessage().contains("Error Occured Developer") ||
                    e.getMessage().contains("Contact Developer")) {
                alert.setContentText(message + ". Please try again or contact support.");
            } else {
                alert.setContentText(message + ": " + e.getMessage());
            }
        } else {
            alert.setContentText(message);
        }

        alert.showAndWait();
    }

    private void showInfoAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccessAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private boolean confirmAction(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        ButtonType yesButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
        ButtonType noButton = new ButtonType("No", ButtonBar.ButtonData.NO);

        alert.getButtonTypes().setAll(yesButton, noButton);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == yesButton;
    }
}