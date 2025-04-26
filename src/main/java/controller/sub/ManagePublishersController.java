package controller.sub;

import com.jfoenix.controls.JFXTextField;
import dto.custom.PublisherDTO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.custom.PublisherService;
import service.custom.impl.PublisherServiceIMPL;
import util.exception.ServiceException;

import java.util.Optional;

public class ManagePublishersController {

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

    private PublisherService publisherService=new PublisherServiceIMPL();

    public void btnSaveAuthorOnAction(ActionEvent actionEvent) throws ServiceException{


    }

    public void btnUpdateAuthorOnAction(ActionEvent actionEvent) throws ServiceException {
    }

    public void btnDeleteAuthorOnAction(ActionEvent actionEvent) throws ServiceException {


    }

    public void btnClearAuthorOnAction(ActionEvent actionEvent) throws ServiceException {
    }

    public void btnUpdatePublisherOnAction(ActionEvent actionEvent) throws ServiceException {
    PublisherDTO publisherDTO=collectData();
try{
    boolean update = publisherService.update(publisherDTO);
    if (update){
        new Alert(Alert.AlertType.INFORMATION,"Updated Success").show();

    }else{
        new Alert(Alert.AlertType.ERROR,"Not Updated").show();
    }
}catch (ServiceException e){
    new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
}

}

    public void btnSavePublisherOnAction(ActionEvent actionEvent) throws ServiceException {
        PublisherDTO publisherDTO = collectData();
        if (publisherDTO == null) {
            new Alert(Alert.AlertType.ERROR,"please fill all fields").show();
        }

        boolean isSaved = publisherService.save(publisherDTO);
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Saved Successfully").show();
            } else {
                new Alert(Alert.AlertType.ERROR,"something went wrong-may be duplicate id or db error").show();
            }

    }

    public void btnDeletePublisherOnAction(ActionEvent actionEvent) throws ServiceException {
    }

    public void btnClearPublisherOnAction(ActionEvent actionEvent) throws ServiceException {
    }

    private PublisherDTO collectData(){
        String id = txtPublisherId.getText();
        String name = txtPublisherName.getText();
        String contact = tctPublisherContactNumber.getText();
        String location = txtPublisherLocation.getText();

        int idNum;
        try {
            idNum = Integer.parseInt(id); // FIXED: Now assigns parsed ID
        } catch (NumberFormatException e) {
            new Alert(Alert.AlertType.ERROR, "Invalid Publisher ID").show();
            return null;
        }

        PublisherDTO publisherDTO = new PublisherDTO();
        publisherDTO.setId(idNum);
        publisherDTO.setName(name);
        publisherDTO.setLocation(location);
        publisherDTO.setContact(contact);
        return publisherDTO;
    }


    public void txtidpublisheronaction(ActionEvent actionEvent) {
   PublisherDTO publisherDTO=collectData();
        try {
            Optional<PublisherDTO> search =publisherService.search(publisherDTO.getId());
        if (search.isPresent()){
            setDataToFields(search.get());
        }else {
            new Alert(Alert.AlertType.ERROR,"publisher not found or invalid id").show();
        }

        } catch (ServiceException e) {
            e.printStackTrace();
           new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void setDataToFields(PublisherDTO publisherDTO){
        txtPublisherId.setText(String.valueOf(publisherDTO.getId()));
        txtAuthorName.setText(publisherDTO.getName());
        txtAuthorContact.setText(publisherDTO.getContact());
        txtPublisherLocation.setText(publisherDTO.getLocation());
    }

    public void txtcontactpublisheronaction(ActionEvent actionEvent) {
    }

    public void txtlocationpublisheronaction(ActionEvent actionEvent) {
    }

    public void txtnamepublisheronaction(ActionEvent actionEvent) {
    }
}
