package Scene.MainScreen;

import Run.Main;
import Scene.ControlledScreen;
import Scene.Login.User;
import Scene.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainController implements ControlledScreen {

    @FXML
    private AnchorPane practice_anchorPane,test_anchorPane,statistic_anchorPane,profile_anchorPane;

    @FXML
    private Button practice_button,test_button,statistic_button,profile_button;

    ScreenController myController;

    //Profile Tab Variables
    @FXML
    private CheckBox checkBox_profile;

    @FXML
    private TextField oldPasswordField_profile,emailField_profile,nameField_profile;

    @FXML
    private DatePicker dobField_profile;

    @FXML
    private PasswordField newPasswordField_profile,confirmPasswordField_profile;

    @FXML
    private Button saveButton_profile,resetButton_profile,logoutButton_profile;
    //*********************************************************************************

    //Practice Tab Variables
    @FXML
    private Button startButton_practice;


    @FXML
    public void initialize(){
        handleChanges();
        populatingProfileTabFields();
    }

    @Override
    public void setScreenParent(ScreenController screenController) {
        myController = screenController;
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
        }else{
            nameField_profile.setDisable(false);
            dobField_profile.setDisable(false);
            emailField_profile.setDisable(false);
            oldPasswordField_profile.setDisable(false);
            newPasswordField_profile.setDisable(false);
            confirmPasswordField_profile.setDisable(false);
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
    public void handlePracticeMode(){
        myController.setScreen(Main.practiceScreenId);
    }

    @FXML
    public void handleTestMode(){
        myController.setScreen(Main.testScreenId);
    }

    private User getUser(){
        User user = null;

        Path filePath = FileSystems.getDefault().getPath("src/DataFiles/currentUser.dat");
        try(ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(filePath)))){
            try{
                user = (User) inputStream.readObject();
            }catch(EOFException ignored){
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return user;
    }
}
