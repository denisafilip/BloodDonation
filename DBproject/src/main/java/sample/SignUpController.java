package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import user.County;
import user.Donor;
import user.Scraper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SignUpController extends ParentController implements Initializable {
    @FXML private Label lblFirstName;
    @FXML private Label lblLastName;
    @FXML private Label lblEmail;
    @FXML private Label lblPassword;
    @FXML private Label lblConfirmPassword;
    @FXML private Label lblPhoneNumber;
    @FXML private Label lblDOB;
    @FXML private Label lblGender;
    @FXML private Label lblBloodType;
    @FXML private Label lblRH;
    @FXML private Label lblCounty;
    @FXML private Label lblCNP;

    @FXML
    private ComboBox<String> comboGender;
    private final ObservableList<String> genderOptions = FXCollections.observableArrayList("F", "M");
    @FXML
    private ComboBox<String> comboCounty;
    private Scraper scraper = new Scraper();
    private ArrayList<County> counties = scraper.webScrapingCounties();
    @FXML
    private ComboBox<String> comboBloodType;
    private final ObservableList<String> bloodTypeOptions = FXCollections.observableArrayList("01", "A2", "B3", "AB4");
    @FXML
    private ComboBox<String> comboRH;
    private final ObservableList<String> RHOptions = FXCollections.observableArrayList("positive", "negative");
    @FXML
    private DatePicker dateDOB;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtCNP;
    @FXML
    private TextField txtPhoneNumber;
    @FXML
    private PasswordField passPassword;
    @FXML
    private PasswordField passConfirmPassword;
    @FXML
    private Button btnRegister;
    @FXML
    private Button btnSignIn;

    public void signUp(ActionEvent event) {
        clearErrorLabels();
        if (txtFirstName.getText().isEmpty()) {
            lblFirstName.setText("Please enter your first name.");
            return;
        }
        if (txtLastName.getText().isEmpty()) {
            lblLastName.setText("Please enter your last name.");
            return;
        }
        if (txtEmail.getText().isEmpty()) {
            lblEmail.setText("Please enter your email.");
            return;
        }
        if (passPassword.getText().isEmpty()) {
            lblPassword.setText("Please enter your password.");
            return;
        }
        if (passConfirmPassword.getText().isEmpty()) {
            lblConfirmPassword.setText("Please confirm your password.");
            return;
        }
        if (!passPassword.getText().equals(passConfirmPassword.getText())) {
            lblConfirmPassword.setText("Your passwords do not match.");
            return;
        }
        if (txtPhoneNumber.getText().isEmpty()) {
            lblPhoneNumber.setText("Please enter your phone number.");
            return;
        }
        if (dateDOB.getValue() == null) {
            lblDOB.setText("Please enter your date of birth.");
            return;
        }
        if (comboGender.getSelectionModel().getSelectedItem() == null) {
            lblGender.setText("Please choose your gender.");
            return;
        }
        if (comboBloodType.getSelectionModel().getSelectedItem() == null) {
            lblBloodType.setText("Please choose your blood type.");
            return;
        }
        if (comboRH.getSelectionModel().getSelectedItem() == null) {
            lblGender.setText("Please choose your RH.");
            return;
        }
        if (comboCounty.getSelectionModel().getSelectedItem() == null) {
            lblCounty.setText("Please choose your county.");
            return;
        }
        if (txtCNP.getText().isEmpty()) {
            lblPhoneNumber.setText("Please enter your CNP.");
            return;
        }
        Boolean RH = transformRHIntoBoolean();
        County userCounty = mapCountyNameToCounty(counties);
        Donor donor = new Donor(txtFirstName.getText(), txtLastName.getText(), txtPhoneNumber.getText(), dateDOB.getValue(), comboGender.getValue(), comboBloodType.getValue(), RH, userCounty, txtCNP.getText(), txtEmail.getText(), passPassword.getText());
        if (!donor.verifyFirstName()) {
            lblFirstName.setText("First name must contain only letters.");
            return;
        }
        if (!donor.verifyLastName()) {
            lblLastName.setText("Last name must contain only letters.");
            return;
        }
        if (!donor.verifyPhoneNumber()) {
            lblPhoneNumber.setText("Your phone number must have 10 digits.");
            return;
        }
        if (!donor.isEligibleToDonate()) {
            lblDOB.setText("You must be over 18 and under 65 to donate blood.");
            return;
        }
        donor.verifyCNP(lblCNP);

        //if everything's alright, go to next page - TO DO
    }

    public void goToSignIn(ActionEvent event) throws IOException {
        goForwardToScene(event, "/signIn.fxml", "Sign In");
    }

    /**
     * clearing the text of the error labels from the sign up form
     */
    public void clearErrorLabels() {
        lblFirstName.setText("");
        lblLastName.setText("");
        lblEmail.setText("");
        lblPassword.setText("");
        lblConfirmPassword.setText("");
        lblPhoneNumber.setText("");
        lblDOB.setText("");
        lblGender.setText("");
        lblBloodType.setText("");
        lblRH.setText("");
        lblCounty.setText("");
        lblCNP.setText("");
    }

    /**
     *
     * @return true if user's RH is positive, false if negative
     */
    public Boolean transformRHIntoBoolean() {
        return comboRH.getValue().equals("positive");
    }

    /**
     *
     * @return the ArrayList<String> containing strictly the county names
     */
    public ArrayList<String> createArrayWithCountyNames() {
        ArrayList<String> countyNames = new ArrayList<>();
        for (County c : counties) {
            countyNames.add(c.getName());
        }
        return countyNames;
    }

    /**
     *
     * @param counties - array containing all of Romania's counties
     * @return the County which the user chose when signing in - o sa incerc sa ma gandesc la o solutie mai ok pentru treaba asta, dar dunno acum
     */
    public County mapCountyNameToCounty(ArrayList<County> counties) {
        for (County c : counties) {
            if (comboCounty.getValue().equals(c.getName())) {
                return c;
            }
        }
        return null;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboGender.setItems(genderOptions);
        comboRH.setItems(RHOptions);
        comboBloodType.setItems(bloodTypeOptions);
        ArrayList<String> countyNames = createArrayWithCountyNames();
        ObservableList<String> countyOptions = FXCollections.observableArrayList(countyNames);
        comboCounty.setItems(countyOptions);
    }
}
