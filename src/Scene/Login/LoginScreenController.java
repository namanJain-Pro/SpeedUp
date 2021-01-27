package Scene.Login;

import DataModel.DataSource;
import Run.Main;
import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import static Run.Main.*;

public class LoginScreenController implements ControlledScreen {
    @FXML
    private StackPane stackPane;

    @FXML
    private AnchorPane signIn_anchorPane,signUp_anchorPane;

    @FXML
    private Button createNewAccount_button,signIn_button;

    //SignUp Variable
    @FXML
    private TextField signUpNameField,signUpEmailField;

    @FXML
    private PasswordField signUpNewPasswordField,signUpReEnterPasswordField;

    @FXML
    private DatePicker signUpDOBField;

    @FXML
    private Button signUp_button;

    @FXML
    private Label checkLabel;
    //*******************************

    //SignIn Variable
    @FXML
    private TextField signInUserNameField;

    @FXML
    private PasswordField signInPasswordField;

    @FXML
    private Label signInForgetPasswordField, uncorrectLabel;

    @FXML
    private Button login_button;
    //*******************************

    //Password Change Variable
    @FXML
    private AnchorPane password_anchorPane;

    @FXML
    private TextField forgetPassword_userNameField;

    @FXML
    private DatePicker forgetPassword_DOB;

    @FXML
    private Label forgetPassword_successLabel;

    @FXML
    private Button forgetPassword_nextButton;

    private int id;

    //*******************************

    //Password Next Step Variable
    @FXML
    private AnchorPane passwordNextStep_anchorPane;

    @FXML
    private TextField forgetPassword_newPassword;

    @FXML
    private PasswordField forgetPassword_confirmPassword;

    @FXML
    private Label forgetPassword_errorLabel;

    @FXML
    private Button forgetPassword_okButton;
    //*******************************

    private LoginDataHandle dataHandle = new LoginDataHandle();
    ScreenController myController;

    @Override
    public void setScreenParent(ScreenController screenController) {
        myController = screenController;
    }

    @FXML
    public void initialize(){
        login_button.setDisable(true);
        signUp_button.setDisable(true);
        forgetPassword_nextButton.setDisable(true);
        forgetPassword_nextButton.setDisable(true);
    }

    @FXML
    public void handleScreenSwap(ActionEvent e){
        if(e.getSource().equals(createNewAccount_button)){
            signUp_anchorPane.toFront();
        }else if (e.getSource().equals(signIn_button)){
            signIn_anchorPane.toFront();
        }
    }

    @FXML
    public void handleKeyType(){
        login_button.setDisable(signInUserNameField.getText().trim().isEmpty() || signInPasswordField.getText().isEmpty());
        signUp_button.setDisable(signUpNameField.getText().trim().isEmpty() || signUpEmailField.getText().trim().isEmpty());
    }

    @FXML
    public void forgotPasswordHandle() {
        password_anchorPane.toFront();
    }

    @FXML
    public void handleCancelButton() {
        signIn_anchorPane.toFront();
    }

    @FXML
    public void checkUserInfo() {
        String name = forgetPassword_userNameField.getText();
        String dob = forgetPassword_DOB.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        id = DataSource.getInstance().checkUserAvailability(name, dob);

        if(id != 0) {
            forgetPassword_successLabel.setText("");
            passwordNextStep_anchorPane.toFront();
        } else {
          forgetPassword_successLabel.setTextFill(Color.RED);
          forgetPassword_successLabel.setText("Didn't find any match");
        }
    }

    @FXML
    public void updatePassword() {
        String newPassword = forgetPassword_newPassword.getText();
        String confirmPassword = forgetPassword_confirmPassword.getText();

        if(newPassword.equals(confirmPassword)) {
            forgetPassword_errorLabel.setText("");
            DataSource.getInstance().updatePassword(newPassword, id);
            signIn_anchorPane.toFront();
        } else {
            forgetPassword_errorLabel.setTextFill(Color.RED);
            forgetPassword_errorLabel.setText("Password didn't match");
        }
    }

    @FXML
    public void handleKeyTyped() {
        if(forgetPassword_userNameField.getText().trim().isEmpty()) {
            forgetPassword_nextButton.setDisable(true);
        } else {
            forgetPassword_nextButton.setDisable(false);
        }

        if(forgetPassword_newPassword.getText().trim().isEmpty() && forgetPassword_confirmPassword.getText().trim().isEmpty()) {
            forgetPassword_okButton.setDisable(true);
        }else {
            forgetPassword_okButton.setDisable(false);
        }
    }

    @FXML
    public void loginHandle(){
        String input = signInUserNameField.getText();
        String password = signInPasswordField.getText();

        boolean check = dataHandle.loginHandle(input, password);

        if(check) {
            uncorrectLabel.setText("");
            clearSignInField();
            load();
            myController.setScreen(Main.mainScreenId);
        } else {
            uncorrectLabel.setText("Username/Password might be wrong!!");
        }
    }

    @FXML
    public void newAccountHandle(){
        String name = signUpNameField.getText();
        String email = signUpEmailField.getText();
        String password;
        String dob = signUpDOBField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        String tempPassword = signUpNewPasswordField.getText();
        if(tempPassword.equals(signUpReEnterPasswordField.getText())){
            checkLabel.setText("");
            password = tempPassword;
            if(dataHandle.createNewAccount(name, email, password, dob)){
                checkLabel.setText("");
                clearSignUpField();
                load();
                myController.setScreen(Main.mainScreenId);
            } else{
              checkLabel.setText("Username already exists, try with different name!!");
            }
        } else{
            checkLabel.setText("Password didn't match try again!!");
        }
    }

    private void clearSignUpField() {
        signUpNameField.clear();
        signUpEmailField.clear();
        signUpDOBField.getEditor().clear();
        signUpNewPasswordField.clear();
        signUpReEnterPasswordField.clear();
    }

    private void clearSignInField() {
        signInUserNameField.clear();
        signInPasswordField.clear();
    }

    private void load() {
        myController.loadScreen(mainScreenId, mainScreen);
    }


}
