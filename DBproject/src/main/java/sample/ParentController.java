package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * ParentController - a class that acts like a parent for every controller, implementing some general methods
 */

public class ParentController {

    public void changeScene(ActionEvent event, String fxmlFilePath) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource(fxmlFilePath));
        Scene  scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
