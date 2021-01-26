package Scene.MainScreen;

import DataModel.DataSource;
import DataModel.Statistics;
import Run.Main;
import Scene.ControlledScreen;
import DataModel.User;
import Scene.PracticeScreen.PracticeScreenController;
import Scene.ScreenController;
import Scene.TestScreen.TestScreenController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static Run.Main.*;

public class MainController implements ControlledScreen {

    @FXML
    private AnchorPane practice_anchorPane,test_anchorPane,statistic_anchorPane,profile_anchorPane;

    @FXML
    private Button practice_button,test_button,statistic_button,profile_button;

    ScreenController myController;

    //Profile Tab Variables
    @FXML
    private Label profile_ErrorLabel;

    @FXML
    private CheckBox checkBox_profile;

    @FXML
    private TextField oldPasswordField_profile,emailField_profile,nameField_profile;

    @FXML
    private DatePicker dobField_profile;

    @FXML
    private PasswordField newPasswordField_profile,confirmPasswordField_profile;

    @FXML
    private Button saveButton_profile;

    //*********************************************************************************

    //Practice Tab Variables
    @FXML
    private Button startButton_practice;

    @FXML
    private ChoiceBox<String> practice_DifficultyChoiceBox;

    @FXML
    private Slider practice_ParaLengthSlider;

    //***********************************************************************************

    //Test Tab Variables
    @FXML
    private ChoiceBox<String> test_difficultyChoiceBox;

    @FXML
    private ChoiceBox<Integer> test_TimePeriodChoiceBox;

    @FXML
    private Slider test_ParaLengthSlider;
    //***********************************************************************************

    // Statistics Tab Variables
    @FXML
    private TableView<Statistics> tableView;

    @FXML
    private ChoiceBox<String> statistic_RecordChoiceBox;

    @FXML
    private Label statistic_BestScoreLabel;

    @FXML
    private ProgressBar statistic_progressBar;

    private String value = null;

    private double bestScore = 200.00;
    //***********************************************************************************

    @FXML
    public void initialize(){
        populateTable();
        handleChanges();
        populatingProfileTabFields();
    }

    @FXML
    public void populateTable() {
        value = statistic_RecordChoiceBox.getValue();
        setBestScore();
        tableView.setItems(DataSource.getInstance().getRecords(value));
    }

    @Override
    public void setScreenParent(ScreenController screenController) {
        myController = screenController;
    }

    @FXML
    public void handleUserInfoChanges() {
        String name = nameField_profile.getText();
        String email = emailField_profile.getText();
        String dob = dobField_profile.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String oldPassword = oldPasswordField_profile.getText();
        String password = DataSource.getInstance().getCurrentUser().getPassword();

        if(!oldPassword.trim().isEmpty()){
            if(oldPassword.equals(DataSource.getInstance().getCurrentUser().getPassword())) {
                profile_ErrorLabel.setText("");
                String newPassword = newPasswordField_profile.getText();
                String retypedPassword = confirmPasswordField_profile.getText();
                if(newPassword.equals(retypedPassword)) {
                    profile_ErrorLabel.setText("");
                    password = newPassword;
                }else {
                    profile_ErrorLabel.setText("New entered passwords didn't match");
                }
            } else {
                profile_ErrorLabel.setText("Old Password didn't match");
            }
        }

        checkBox_profile.setSelected(false);
        handleChanges();
        DataSource.getInstance().updateUserInfo(name, email, dob, password);
    }

    @FXML
    public void handleScreenSwap(ActionEvent e){
        if(e.getSource().equals(practice_button)){
            practice_anchorPane.toFront();
        }else if(e.getSource().equals(test_button)){
            test_anchorPane.toFront();
        }else if(e.getSource().equals(statistic_button)){
            statistic_anchorPane.toFront();
        }else if(e.getSource().equals(profile_button)){
            profile_anchorPane.toFront();
        }
    }

    @FXML
    public void handleChanges(){
        boolean isSelected = checkBox_profile.isSelected();

        if(!isSelected){
            nameField_profile.setDisable(true);
            dobField_profile.setDisable(true);
            emailField_profile.setDisable(true);
            oldPasswordField_profile.setDisable(true);
            newPasswordField_profile.setDisable(true);
            confirmPasswordField_profile.setDisable(true);
            saveButton_profile.setDisable(true);
        }else{
            nameField_profile.setDisable(false);
            dobField_profile.setDisable(false);
            emailField_profile.setDisable(false);
            oldPasswordField_profile.setDisable(false);
            newPasswordField_profile.setDisable(false);
            confirmPasswordField_profile.setDisable(false);
            saveButton_profile.setDisable(false);
        }
    }

    private void populatingProfileTabFields(){
        User user = getUser();
        if(user != null) {
            nameField_profile.setText(user.getName());
            emailField_profile.setText(user.getEmail());

            LocalDate localDate = LocalDate.parse(user.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            dobField_profile.setValue(localDate);
        }
    }

    @FXML
    public void handleTestMode(){
        String difficulty = test_difficultyChoiceBox.getValue();
        int length =(int) test_ParaLengthSlider.getValue();
        int time = test_TimePeriodChoiceBox.getValue()*60;
        TestScreenController.getValues(time, length, difficulty);

        myController.loadScreen(Main.testScreenId, Main.testScreen);
        myController.setScreen(Main.testScreenId);
    }

    @FXML
    public void handlePracticeMode(){
        String difficulty = practice_DifficultyChoiceBox.getValue();
        int length =(int) practice_ParaLengthSlider.getValue();

        PracticeScreenController.gatherInfo(length, difficulty);

        myController.loadScreen(practiceScreenId,practiceScreen);
        myController.setScreen(practiceScreenId);
    }

    @FXML
    public void handleLogout(){
        DataSource.getInstance().changeLoginStatusTo0();
        myController.unloadScreen(loginScreenId);
        myController.loadScreen(Main.loginScreenId, loginScreen);
        myController.setScreen(Main.loginScreenId);
    }

    private User getUser(){
        return DataSource.getInstance().getCurrentUser();
    }

    private void setBestScore() {
        ObservableList<Statistics> list = DataSource.getInstance().getRecords(value);
        int max = 0;

        for(Statistics obj : list) {
            int temp = obj.getWpm();
            if(temp >= max)
                max = temp;
        }

        setStatistic_progressBar(max);
        statistic_BestScoreLabel.setText(max+"");
    }

    private void setStatistic_progressBar(int userBestScore) {
        double percentScore = userBestScore/bestScore;
        statistic_progressBar.setProgress(percentScore);
    }
}
