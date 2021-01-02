package Scene.Login;

import DataModel.DataSource;
import Run.Main;
import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.format.DateTimeFormatter;

import static Run.Main.*;

public class LoginScreenController implements ControlledScreen {

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
        myController.loadScreen(practiceScreenId,practiceScreen);
        myController.loadScreen(testScreenId,testScreen);
    }


}
