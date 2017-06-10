package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import model.Packagee;
import model.Packagee_Delete;

public class packageController {

    @FXML
    private TextField name;

    @FXML
    private TextField lastname;

    @FXML
    private TextField address;

    @FXML
    private ToggleGroup brzina;

    @FXML
    private ToggleGroup flow;

    @FXML
    private ToggleGroup agreement;

    @FXML
    private TextField id;

    @FXML
    private Button saveBtn;

    @FXML
    private Button clearBtn;

    @FXML
    private Button deleteBtn;

    @FXML
    private TextArea lista;

    @FXML
    private Button listBtn;

    @FXML
    private URL location;

    @FXML
    private ResourceBundle resource;

    public packageController() {
    }

    Packagee pac;
    Packagee_Delete del;

    @FXML
    private void initialize() {

        pac = new Packagee();

        name.textProperty().bindBidirectional(pac.firstNameProperty());
        lastname.textProperty().bindBidirectional(pac.lastNameProperty());
        address.textProperty().bindBidirectional(pac.addressProperty());

        brzina.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue != null) {

                    RadioButton selected = (RadioButton) newValue;

                    switch (selected.getId()) {
                        case "2mbit":
                            pac.speedProperty().set("2 Mbit");
                            break;
                        case "5mbit":
                            pac.speedProperty().set("5 Mbit");
                            break;
                        case "10mbit":
                            pac.speedProperty().set("10 Mbit");
                            break;
                        case "20mbit":
                            pac.speedProperty().set("20 Mbit");
                            break;
                        case "50mbit":
                            pac.speedProperty().set("50 Mbit");
                            break;
                        case "100mbit":
                            pac.speedProperty().set("100 Mbit");
                            break;
                    }
                }
            }
        });

        flow.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue != null) {

                    RadioButton selected = (RadioButton) newValue;

                    switch (selected.getId()) {
                        case "1GB":
                            pac.flowProperty().set("1 GB");
                            break;
                        case "5GB":
                            pac.flowProperty().set("5 GB");
                            break;
                        case "10GB":
                            pac.flowProperty().set("10 GB");
                            break;
                        case "100GB":
                            pac.flowProperty().set("100 GB");
                            break;
                        case "1FLAT":
                            pac.flowProperty().set("1 FLAT");
                            break;
                    }
                }
            }
        });

        agreement.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

                if (newValue != null) {

                    RadioButton selected = (RadioButton) newValue;

                    switch (selected.getId()) {
                        case "1year":
                            pac.agreementProperty().set("1 Year");
                            break;
                        case "2year":
                            pac.agreementProperty().set("2 Year");
                            break;
                    }
                }
            }
        });

        del = new Packagee_Delete();
        id.textProperty().bindBidirectional(del.idProperty());
    }

    @FXML
    private void savePackagee() throws ClassNotFoundException {
        if (pac.isValid()) {
            pac.save();
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = pac.errorsProperty().get();

            for (String errList1 : errList) {
                errMsg.append(errList1);
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Package can not be saved");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
    }

    @FXML
    private void clearPackage() {
        pac.firstNameProperty().set("");
        pac.lastNameProperty().set("");
        pac.addressProperty().set("");

        if (brzina.getSelectedToggle() != null) {
            brzina.getSelectedToggle().setSelected(false);
        }
        if (flow.getSelectedToggle() != null) {
            flow.getSelectedToggle().setSelected(false);
        }
        if (agreement.getSelectedToggle() != null) {
            agreement.getSelectedToggle().setSelected(false);
        }
    }

    @FXML
    private void deletePackagee() throws ClassNotFoundException {
        if (del.isValid()) {
            del.delete();
        } else {
            StringBuilder errMsg = new StringBuilder();

            ArrayList<String> errList = del.errorsProperty().get();

            for (String errList1 : errList) {
                errMsg.append(errList1);
            }

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Package can not be removed");
            alert.setHeaderText(null);
            alert.setContentText(errMsg.toString());
            alert.showAndWait();
            errList.clear();
        }
    }

    @FXML
    private void listaPaketa() throws ClassNotFoundException {
        lista.setText(pac.listaPaketa());
    }

    @FXML
    private void clearLists() {
        lista.setText("");
    }
}
