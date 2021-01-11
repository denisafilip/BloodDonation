package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import user.Donor;
import user.User;

import java.io.IOException;

public class SignInController extends ParentController {
    @FXML private Label lblEmailSignIn;
    @FXML private Label lblPasswordSignIn;
    @FXML
    private TextField txtEmailSignIn;
    @FXML
    private PasswordField passPasswordSignIn;
    @FXML private AnchorPane anchorAlert;
    @FXML private Button btnAlert;
    @FXML
    private Button btnRegister2;
    @FXML
    private Button btnSignIn2;

    public void signIn(ActionEvent event) {
        clearSignInLabels();
        if (txtEmailSignIn.getText().isEmpty()) {
            lblEmailSignIn.setText("Please enter your email.");
            return;
        }
        if (passPasswordSignIn.getText().isEmpty()) {
            lblPasswordSignIn.setText("Please enter your password.");
            return;
        }
        Donor donor = new Donor(txtEmailSignIn.getText(), passPasswordSignIn.getText());
        if (database.isDonorInDatabaseSignIn(donor)) {
            currentDonor = database.getDonorData(donor.getEmail());
           //change scene
            try {
                changeScene(event, "/mainMenu.fxml");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        } else {
            anchorAlert.setVisible(true);
        }
    }

    public void goToSignUp(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/signUp.fxml");
    }

    public void closeAlert() {
        anchorAlert.setVisible(false);
    }

    public void clearSignInLabels() {
        lblEmailSignIn.setText("");
        lblPasswordSignIn.setText("");
    }

}
