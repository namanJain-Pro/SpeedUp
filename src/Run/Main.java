package Run;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("/Scene/Login/loginScreen.fxml"));
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
