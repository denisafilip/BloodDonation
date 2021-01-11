package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import user.BloodBank;
import user.County;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BloodBankPickerController extends ParentController implements Initializable {

    @FXML
    private ComboBox<String> countyChoice;
    @FXML
    private Button btnBack;

    @FXML
    private ComboBox<String> bloodBankChoice;

    private static final String HOVERED_BUTTON_STYLE_BACK = "-fx-background-color: #ffffff; -fx-border-color: #000000; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.8), 10, 0, 0, 5);";
    private static final String IDLE_BUTTON_STYLE_BACK = "-fx-background-color: #ffffff;-fx-border-color: #000000; -fx-background-radius: 10";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> countiesOBS = FXCollections.observableArrayList();
        for (County county : counties) {
            countiesOBS.add(county.getName());
        }

        countyChoice.setItems(countiesOBS);
        countyChoice.setOnAction(e -> {
            if (countyChoice.getValue() != null) {
                int index = countiesOBS.indexOf(countyChoice.getValue());
                County county = counties.get(index);

                ObservableList<String> bloodBanksOBS = FXCollections.observableArrayList();
                for (BloodBank bloodBank : bloodBanks) {
                    if (bloodBank.getCounty().equals(county))
                        bloodBanksOBS.add(bloodBank.getName());
                }
                bloodBankChoice.setItems(bloodBanksOBS);
            }
        });

        btnBack.setOnMouseEntered(e -> btnBack.setStyle(HOVERED_BUTTON_STYLE_BACK));
        btnBack.setOnMouseExited(e -> btnBack.setStyle(IDLE_BUTTON_STYLE_BACK));
    }

    @FXML
    private void goToAppointment(ActionEvent event) {
        for (BloodBank bloodBank : bloodBanks) {
            if (bloodBank.getName().equals(bloodBankChoice.getValue())) {
                currentBloodBank = bloodBank;
                break;
            }
        }
        try {
            changeScene(event, "/appointments.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void goBackToMainMenu(ActionEvent event) {
        try {
            changeScene(event, "/mainMenu.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


}
