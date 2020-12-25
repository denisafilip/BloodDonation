package sample;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;

import java.time.LocalDate;

public class Appointments {
    @FXML private DatePicker appointmentDate;

    @FXML
    public void buttonas(){
        LocalDate localDate = appointmentDate.getValue();
        System.out.println(localDate.getDayOfMonth());
    }
}
