package Scene.MainScreen;

import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class MainController implements ControlledScreen {

    @FXML
    private AnchorPane practice_anchorPane,test_anchorPane,statistic_anchorPane,profile_anchorPane;

    @FXML
    private Button practice_button,test_button,statistic_button,profile_button;

    ScreenController myController;

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
}
