package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
