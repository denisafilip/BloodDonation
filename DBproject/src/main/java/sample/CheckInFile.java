package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;

public class CheckInFile extends ParentController {

    ObservableList<String> choices = FXCollections.observableArrayList("Yes", "No");

    @FXML
    private ChoiceBox<String> alcoholChoiceBox;
    @FXML
    private ChoiceBox<String> tattooChoiceBox;
    @FXML
    private ChoiceBox<String> treatmentChoiceBox;
    @FXML
    private ChoiceBox<String> chronicChoiceBox;
    @FXML
    private ChoiceBox<String> covid19ChoiceBox;
    @FXML
    private ChoiceBox<String> rejectedChoiceBox;
    @FXML
    private Label warningLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button nextButton;

    @FXML
    private void initialize() {
        alcoholChoiceBox.setItems(choices);
        tattooChoiceBox.setItems(choices);
        treatmentChoiceBox.setItems(choices);
        chronicChoiceBox.setItems(choices);
        covid19ChoiceBox.setItems(choices);
        rejectedChoiceBox.setItems(choices);
    }

    @FXML
    private void check() {
        if (alcoholChoiceBox.getValue() == null ||
                tattooChoiceBox.getValue() == null ||
                treatmentChoiceBox.getValue() == null ||
                chronicChoiceBox.getValue() == null ||
                covid19ChoiceBox.getValue() == null ||
                rejectedChoiceBox.getValue() == null) {
            warningLabel.setText("Please fill in all the categories first.");
        } else if (alcoholChoiceBox.getValue().equals("No") &&
                tattooChoiceBox.getValue().equals("No") &&
                chronicChoiceBox.getValue().equals("No") &&
                covid19ChoiceBox.getValue().equals("No") &&
                treatmentChoiceBox.getValue().equals("No")) {
            warningLabel.setStyle(
                    "-fx-text-fill: black;"
            );
            warningLabel.setText("You are eligible to donate. Press next.");
            submitButton.setDisable(true);
            nextButton.setText("Next");
            nextButton.setVisible(true);
            nextButton.setOnAction(e -> {
                try {
                    changeScene(e, "/Appointments.fxml");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        } else {
            warningLabel.setStyle(
                    "-fx-text-fill: red;"
            );
            warningLabel.setText("You are not eligible to donate. Press exit.");
            submitButton.setDisable(true);
            nextButton.setText("Exit");
            nextButton.setVisible(true);
            nextButton.setOnAction(e -> {
                try {
                    changeScene(e, "/signIn.fxml");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            });
        }
    }

}
