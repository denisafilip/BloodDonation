package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import user.Scraper;

import java.io.IOException;

public class MainMenuController extends ParentController {

    public MainMenuController() {
        if (counties == null) {
            Scraper scraper = new Scraper();
            counties = scraper.webScrapingCounties();
        }
        if (bloodBanks == null) {
            bloodBanks = database.getBloodBanks(counties);
        }

        currentDonor.setAppointments(database.getUsersAppointments(currentDonor, bloodBanks));
    }


    @FXML
    private void goToAppointmentsHistory(ActionEvent event) {
        try {
            changeScene(event, "/appointmentHistory.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void goToNewAppointment(ActionEvent event) {
        try {
            changeScene(event, "/bloodBankPicker.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
