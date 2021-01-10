package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;


public class AppointmentsController extends ParentController {
    @FXML
    private TextField textHour;
    @FXML
    private DatePicker appointmentDate;
    @FXML
    private Button eightAM;
    @FXML
    private Button nineAM;
    @FXML
    private Button tenAM;
    @FXML
    private Button elevenAM;
    @FXML
    private Button twelvePM;
    @FXML
    private Button onePM;

    @FXML
    private void set8am() {
        textHour.setText("8");
    }

    @FXML
    private void set9am() {
        textHour.setText("9");
    }

    @FXML
    private void set10am() {
        textHour.setText("10");
    }

    @FXML
    private void set11am() {
        textHour.setText("11");
    }

    @FXML
    private void set12pm() {
        textHour.setText("12");
    }

    @FXML
    private void set1pm() {
        textHour.setText("1");
    }


    @FXML
    private void initialize() {
        appointmentDate.setOnAction(e -> {
            String date = appointmentDate.getValue().toString();

            eightAM.setDisable(false);
            eightAM.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 5em;" +
                            "-fx-border-color: red"
            );
            nineAM.setDisable(false);
            nineAM.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 5em;" +
                            "-fx-border-color: red"
            );
            tenAM.setDisable(false);
            tenAM.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 5em;" +
                            "-fx-border-color: red"
            );
            elevenAM.setDisable(false);
            elevenAM.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 5em;" +
                            "-fx-border-color: red"
            );
            twelvePM.setDisable(false);
            twelvePM.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 5em;" +
                            "-fx-border-color: red"
            );
            onePM.setDisable(false);
            onePM.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-border-radius: 5em;" +
                            "-fx-border-color: red"
            );

            ArrayList<Timestamp> datesOccupiedOnDate = database.getOccupiedOnDate(date, bloodBanks.indexOf(currentBloodBank) + 1);
            for (Timestamp s : datesOccupiedOnDate) {
                String[] parts = s.toString().split(" ");
                if (parts[1].contains("08")) {
                    eightAM.setDisable(true);
                    eightAM.setStyle(
                            "-fx-background-color: #fc3903;" +
                                    "-fx-border-color: white;" +
                                    "-fx-border-radius: 5em;"
                    );
                }

                if (parts[1].contains("09")) {
                    nineAM.setDisable(true);
                    nineAM.setStyle(
                            "-fx-background-color: #fc3903;" +
                                    "-fx-border-color: white;" +
                                    "-fx-border-radius: 5em;"
                    );
                }

                if (parts[1].contains("10")) {
                    tenAM.setDisable(true);
                    tenAM.setStyle(
                            "-fx-background-color: #fc3903;" +
                                    "-fx-border-color: white;" +
                                    "-fx-border-radius: 5em;"
                    );
                }

                if (parts[1].contains("11")) {
                    elevenAM.setDisable(true);
                    elevenAM.setStyle(
                            "-fx-background-color: #fc3903;" +
                                    "-fx-border-color: white;" +
                                    "-fx-border-radius: 5em;"
                    );
                }

                if (parts[1].contains("12")) {
                    twelvePM.setDisable(true);
                    twelvePM.setStyle(
                            "-fx-background-color: #fc3903;" +
                                    "-fx-border-color: white;" +
                                    "-fx-border-radius: 5em;"
                    );
                }

                if (parts[1].contains("13")) {
                    onePM.setDisable(true);
                    onePM.setStyle(
                            "-fx-background-color: #fc3903;" +
                                    "-fx-border-color: white;" +
                                    "-fx-border-radius: 5em;"
                    );
                }
            }
        });
    }

    @FXML
    private void goBack(ActionEvent event) {
        try {
            changeScene(event, "/bloodBankPicker.fxml");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @FXML
    private void setAppointment(ActionEvent event) {
        if (!textHour.getText().isEmpty()) {
            String[] parts = appointmentDate.getValue().toString().split("-");
            Timestamp timestamp = Timestamp.valueOf(String.format("%04d-%02d-%02d %02d:00:00", Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(textHour.getText())));
            currentTimeStamp = timestamp;
            try {
                changeScene(event, "/checkInFile.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            System.out.println("Empty hour!");
        }
    }

}
