package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import user.Appointment;
import user.Scraper;

import java.io.IOException;

public class AppointmentHistoryController extends ParentController {

    public AppointmentHistoryController() {
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
    private ListView<Pane> listView;


    @FXML
    private void initialize() {
        ObservableList<Pane> observableList = FXCollections.observableArrayList();
        for (Appointment appointment : currentDonor.getAppointments()) {
            Pane pane = new Pane();

            Text donorsName = new Text(appointment.getDonor().getFirstName() + " " + appointment.getDonor().getLastName());
            donorsName.setLayoutX(100);
            donorsName.setLayoutY(20);

            Text date = new Text(appointment.getAppointmentDate().toString());
            date.setLayoutX(400);
            date.setLayoutY(20);

            Text bloodBankName = new Text(appointment.getBloodBank().getName() + " " + appointment.getBloodBank().getCounty().getName());
            bloodBankName.setLayoutX(700);
            bloodBankName.setLayoutY(20);


            pane.getChildren().add(donorsName);
            pane.getChildren().add(date);
            pane.getChildren().add(bloodBankName);

            observableList.add(pane);
        }

        listView.setItems(observableList);
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            changeScene(event, "/mainMenu.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

}
