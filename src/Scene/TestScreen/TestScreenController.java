package Scene.TestScreen;

import DataModel.DataSource;
import DataModel.ParagraphGen;
import Run.Main;
import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.util.Duration;

import java.util.concurrent.TimeUnit;

public class TestScreenController implements ControlledScreen {

    ScreenController myController;

    @FXML
    private TextArea test_textArea, test_inputText;
    @FXML
    private Label timerText, test_errorLabel, test_wpmLabel;

    private static int count = 0;

    private static int timePeriod;
    private static int lengthOfPara;
    private static String difficulty;

    // Clock Variables
    private Timeline timeline;
    private Integer timeSeconds;
    private boolean checker = true;
    // ..........................

    // Variable for the logic
    private char[] letters;
    private int wrongCharCounter = 0;
    private int charCounter = 0;
    private long start;
    private long end;
    private int intialCheck = 0;
    private boolean check = false;
    // .......................

    @FXML
    public void initialize(){
        populateTextArea(lengthOfPara, difficulty);
        timerText.setText(timePeriod+"");
        letters = test_textArea.getText().toCharArray();
    }

    @Override
    public void setScreenParent(ScreenController screenController) {
        myController = screenController;
    }

    @FXML
    public void goToHomeScreen(){
        myController.unloadScreen(Main.mainScreenId);
        myController.loadScreen(Main.mainScreenId, Main.mainScreen);
        myController.setScreen(Main.mainScreenId);
    }

    @FXML
    public void handleKeyTyped(){
        char[] inputLetters = test_inputText.getText().toCharArray();

        for(int i=charCounter; i< inputLetters.length; i++){
            if (inputLetters[i] == letters[i]){
                charCounter++;
                test_textArea.selectRange(0, charCounter);
                end = System.nanoTime();
                calculation();
                check = false;
            }else {
                if(check == false){
                    wrongCharCounter++;
                    check = true;
                }
                test_errorLabel.setText(wrongCharCounter+"");
            }
        }

        if(inputLetters.length >= letters.length){
            timeline.stop();
            int time = timePeriod - timeSeconds;
            test_inputText.setDisable(true);
            end = System.nanoTime();
            int speed = calculation();
            DataSource.getInstance().updateTestRecord(difficulty, lengthOfPara, speed, wrongCharCounter, time);
        }
    }

    @FXML
    public void handleMouseClick(){
        if(intialCheck <= 1){
            intialCheck++;
            start = System.nanoTime();
            startClock();
        }
    }

    private int calculation(){
        long elapsedTime = end - start;
        long convert = TimeUnit.SECONDS.convert( elapsedTime, TimeUnit.NANOSECONDS);
        double timeMin = (double)convert / 60;

        int grossWPM = (int) ((double) charCounter/5 /timeMin);
        test_wpmLabel.setText(grossWPM+"");
        return grossWPM;
    }

    private void populateTextArea(int len, String diff){
        String para = ParagraphGen.getInstance().generateParagraph(len, diff).trim();
        test_textArea.setText(para);
    }

    private void startClock() {
        if (checker) {
            checker = false;
            if (timeline != null) {
                timeline.stop();
            }
            timeSeconds = timePeriod;

            timerText.setText(timeSeconds.toString());
            timeline = new Timeline();
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.getKeyFrames().add(
                    new KeyFrame(Duration.seconds(1),
                            new EventHandler() {
                                @Override
                                public void handle(Event event) {
                                    timeSeconds--;
                                    timerText.setText(
                                            timeSeconds.toString());
                                    if (timeSeconds <= 0) {
                                        timeline.stop();
                                        test_inputText.setDisable(true);
                                        end = System.nanoTime();
                                        int speed = calculation();
                                        DataSource.getInstance().updateTestRecord(difficulty, lengthOfPara, speed, wrongCharCounter, timePeriod);
                                        test_textArea.setText("Times Up !!!");
                                    }
                                }
                            }));
            timeline.playFromStart();
        }
    }


    public static void getValues(int time, int len, String diff) {
        timePeriod = time;
        lengthOfPara = len;
        difficulty = diff;
    }
}
