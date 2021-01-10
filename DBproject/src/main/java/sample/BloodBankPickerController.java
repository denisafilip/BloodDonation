package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import user.BloodBank;
import user.County;

import java.io.IOException;

public class BloodBankPickerController extends ParentController {

    @FXML
    private ComboBox<String> countyChoice;

    @FXML
    private ComboBox<String> bloodBankChoice;

    @FXML
    private void initialize() {
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
}
