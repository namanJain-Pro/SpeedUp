package Run;

import DataModel.DataSource;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Scene.*;

public class Main extends Application {

    public static String loginScreen = "/Scene/Login/loginScreen.fxml";
    public static String loginScreenId = "LoginScreen";
    public static String mainScreen = "/Scene/MainScreen/mainScreen.fxml";
    public static String mainScreenId = "MainScreen";
    public static String practiceScreenId = "PracticeScreen";
    public static String practiceScreen = "/Scene/PracticeScreen/practiceScreen.fxml";
    public static String testScreenId = "TestScreen";
    public static String testScreen = "/Scene/TestScreen/testScreen.fxml";


    private static final ScreenController mainController = new ScreenController();


    @Override
    public void start(Stage primaryStage) {
        mainController.loadScreen(loginScreenId,loginScreen);
        mainController.loadScreen(mainScreenId,mainScreen);
        mainController.loadScreen(practiceScreenId,practiceScreen);
        mainController.loadScreen(testScreenId,testScreen);

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

    @Override
    public void init() throws Exception {
        super.init();
        if(!DataSource.getInstance().open()){
            Platform.exit();
        }
        DataSource.getInstance().create();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        DataSource.getInstance().close();
    }
}
