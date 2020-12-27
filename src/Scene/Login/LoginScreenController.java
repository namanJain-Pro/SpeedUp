package Scene.Login;

import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

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

    }

    @FXML
    public void newAccountHandle(){

    }
}
