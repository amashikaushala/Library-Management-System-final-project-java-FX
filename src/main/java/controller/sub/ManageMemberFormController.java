package controller.sub;

import com.jfoenix.controls.JFXTextField;
import dto.custom.MemberDTO;
import entity.custom.Member;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import service.custom.impl.MemberServiceIMPL;
import util.exception.custom.MemberException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageMemberFormController {

    @FXML
    private TableView<MemberDTO> tblMember;

    @FXML
    private TableColumn<MemberDTO, String> colId;

    @FXML
    private TableColumn<MemberDTO, String> colName;

    @FXML
    private TableColumn<MemberDTO, String> colAddress;

    @FXML
    private TableColumn<MemberDTO, String> colEmail;

    @FXML
    private TableColumn<MemberDTO, String> colContactNumber;


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

    private final MemberServiceIMPL service = new MemberServiceIMPL();

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String memberId = txtMemberId.getText();
        boolean delete = false;
        String errorMessage = "User Cancelled-Not Deleted";
        Alert areYouSure = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure", ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> buttonType = areYouSure.showAndWait();
        if (buttonType.isPresent()) {
            ButtonType pressedButton = buttonType.get();
            if (pressedButton.equals(ButtonType.YES)) {
                try {
                    delete = service.delete(memberId);
                    if (!delete) {
                        errorMessage = "user not found-check id";
                    }
                } catch (MemberException e) {
                    errorMessage = e.getMessage();
                }
            }
        }
        if (delete) {
            new Alert(Alert.AlertType.INFORMATION, "Member Deleted Successfully").show();

        } else {
            new Alert(Alert.AlertType.ERROR, errorMessage).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        MemberDTO memberDTO = collectData();

        if (memberDTO == null) {
            new Alert(Alert.AlertType.ERROR, "Please fill all fields").show();
            return;
        }

        boolean isMemberSaved = service.save(memberDTO);
        if (isMemberSaved) {
            new Alert(Alert.AlertType.INFORMATION, "Member Saved Successfully").show();

        } else {
            new Alert(Alert.AlertType.ERROR, "Something went wrong - maybe duplicate ID or DB error").show();
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        MemberDTO memberDTO = collectData();
        boolean isUpdated = false;
        String errorMessage = "Data is already Dame-Not Deleted";
        try {
            isUpdated = service.update(memberDTO);
        } catch (MemberException e) {
            errorMessage = e.getMessage();
        }
        if (isUpdated) {
            new Alert(Alert.AlertType.INFORMATION, "Member updated Success").show();

        } else {
            new Alert(Alert.AlertType.ERROR, errorMessage);
        }
    }

    @FXML
    void btnclearOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void txtMemberContactOnAction(ActionEvent event) {
    }

    @FXML
    void txtMemberEmailOnAction(ActionEvent event) {
    }

    @FXML
    void txtMemberIdOnAction(ActionEvent event) {
        Optional<MemberDTO> search = null;
        try {
            search = service.search(txtMemberId.getText());
        } catch (MemberException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            return;
        }
        if (search.isPresent()) {
            setDataTOFields(search.get());
        } else {
            new Alert(Alert.AlertType.ERROR, "Member Not Found").show();

        }
    }

    private MemberDTO collectData() {
        String id = txtMemberId.getText().trim();
        String name = txtMemberName.getText().trim();
        String address = txtMemberAddress.getText().trim();
        String email = txtMemberEmail.getText().trim();
        String contact = txtMemberContactNumber.getText().trim();


        if (id.isEmpty() || name.isEmpty() || address.isEmpty() || email.isEmpty() || contact.isEmpty()) {
            return null;
        }

        return new MemberDTO(id, name, address, email, contact);
    }





    private void clearFields() {
        txtMemberId.clear();
        txtMemberName.clear();
        txtMemberAddress.clear();
        txtMemberEmail.clear();
        txtMemberContactNumber.clear();
    }

    public void setDataTOFields(MemberDTO member) {
        txtMemberId.setText(member.getId());
        txtMemberName.setText(member.getName());
        txtMemberEmail.setText(member.getEmail());
        txtMemberAddress.setText(member.getAddress());
        txtMemberContactNumber.setText(member.getContact());
    }
}



