package Scene.PracticeScreen;

import Run.Main;
import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PracticeScreenController implements ControlledScreen {

    ScreenController myController;

    @FXML
    private Button backButton;

    @Override
    public void setScreenParent(ScreenController screenController) {
     myController = screenController;
    }

    @FXML
    public void goToHomeScreen(){
        myController.setScreen(Main.mainScreenId);
    }
}
