package Run;

import Scene.Login.LoginDataHandle;
import Scene.Login.User;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Scene.*;


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

        mainController.setScreen(loginScreenId);
        Group root = new Group();
        root.getChildren().addAll(mainController);
        primaryStage.setTitle("Speed Up");
        primaryStage.setScene(new Scene(root,1500,950));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
