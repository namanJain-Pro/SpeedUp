package Run;

import Scene.Login.LoginDataHandle;
import Scene.Login.User;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Scene.*;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;


public class Main extends Application {

    public static String loginScreen = "/Scene/Login/loginScreen.fxml";
    public static String loginScreenId = "LoginScreen";
    public static String mainScreen = "/Scene/MainScreen/mainScreen.fxml";
    public static String mainScreenId = "MainScreen";

    @Override
    public void start(Stage primaryStage) throws Exception{
        ScreenController mainController = new ScreenController();
        mainController.loadScreen(loginScreenId,loginScreen);
        mainController.loadScreen(mainScreenId,mainScreen);


        Path filePath = FileSystems.getDefault().getPath("src/DataFiles/currentUserDetails.dat");
        try(ObjectInputStream inputStream = new ObjectInputStream(new BufferedInputStream(Files.newInputStream(filePath)))){
            try {
                inputStream.readObject();
                User user = (User) inputStream.readObject();
                mainController.setScreen(mainScreenId);
            }catch (EOFException e){
                mainController.setScreen(loginScreenId);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        Group root = new Group();
        root.getChildren().addAll(mainController);
        primaryStage.setTitle("Speed Up");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMinHeight(844);
        primaryStage.setMinWidth(1189);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
