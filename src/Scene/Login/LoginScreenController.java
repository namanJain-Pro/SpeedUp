package Scene.Login;

import Run.Main;
import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginScreenController implements ControlledScreen, Initializable {

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

    //Data
    LoginDataHandle loginDataHandle = new LoginDataHandle();
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
    public void loginHandle(){

        String input = signInUserNameField.getText().trim();
        String password = signInPasswordField.getText().trim();

        if(!input.isEmpty()){
            if(emailCheck(input)){
                boolean verification = loginDataHandle.verificationWithEmail(input,password);
                if(verification){
                    loginDataHandle.userTrackWithEmail(input);
                    myController.setScreen(Main.mainScreenId);
                }else{
                    uncorrectLabel.setText("Username/Password dosen't match, try with correct one");
                }
            }else{
                boolean verification = loginDataHandle.verificationWithUsername(input,password);
                if(verification){
                    loginDataHandle.userTrackWithUsername(input);
                    myController.setScreen(Main.mainScreenId);
                }else{
                    uncorrectLabel.setText("Username/Password dosen't match, try with correct one");
                }
            }
        }
    }

    @FXML
    public void newAccountHandle(){
        String name = signUpNameField.getText().trim();
        String email = signUpEmailField.getText().trim();
        String dob = signUpDOBField.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String password;

        if(signUpNewPasswordField.getText().trim().equals(signUpReEnterPasswordField.getText().trim())){
            password = signUpReEnterPasswordField.getText().trim();


            int check = loginDataHandle.createNewAccount(name, email, password, dob);

            if(check == 1){
                checkLabel.setText("Username already exist");
            }else if(check == 2){
                checkLabel.setText("Email is already used");
            }else{
                System.out.println("Successful");
            }

        }else{
            checkLabel.setText("Password didn't match");
        }

    }

    @FXML
    public void handleKeyType(){
        login_button.setDisable(signInUserNameField.getText().trim().isEmpty() || signInPasswordField.getText().isEmpty());
        signUp_button.setDisable(signUpNameField.getText().trim().isEmpty() || signUpEmailField.getText().trim().isEmpty());
    }

    private boolean emailCheck(String str){
        char[] ch = str.toCharArray();
        int counter = 0;

        for(char c : ch){
            if(c == '@'){
                counter++;
            }else if(c == '.'){
                counter++;
            }
        }

        if(counter >= 2 && counter <=3){
            return true;
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
