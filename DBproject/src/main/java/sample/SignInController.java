package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
        }/* else {
            //display an error message somehow
            //showAlert(Alert.AlertType.ERROR, owner, "Conectare nereușită", "Acest cont nu a fost înregistrat. Verificați credențialele de conectare sau apăsați pe butonul ”Înregistrare” pentru a vă crea un cont.");
        }*/
        //go to next page
    }

    public void goToSignUp(ActionEvent actionEvent) throws IOException {
        changeScene(actionEvent, "/signUp.fxml");
    }

    public void clearSignInLabels() {
        lblEmailSignIn.setText("");
        lblPasswordSignIn.setText("");
    }

}
