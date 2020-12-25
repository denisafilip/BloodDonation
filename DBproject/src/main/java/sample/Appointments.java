package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

public class Appointments {
    ObservableList<String> observableList = FXCollections.observableArrayList("00", "15", "30", "45");
    @FXML private ChoiceBox<String> choice;
    @FXML private TextField textHour;
    @FXML private Button eightAM;

    @FXML
    private void set8am()
    {
        textHour.setText("8AM");
    }

    @FXML
    private void set9am()
    {
        textHour.setText("9AM");
    }

    @FXML
    private void set10am()
    {
        textHour.setText("10AM");
    }

    @FXML
    private void set11am()
    {
        textHour.setText("11AM");
    }

    @FXML
    private void set12pm()
    {
        textHour.setText("12PM");
    }

    @FXML
    private void set1pm()
    {
        textHour.setText("1PM");
    }



    @FXML
    private void initialize(){
        choice.setItems(observableList);
    }

}
