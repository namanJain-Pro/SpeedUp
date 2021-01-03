package Scene.PracticeScreen;

import DataModel.DataSource;
import DataModel.ParagraphGen;
import Run.Main;
import Scene.ControlledScreen;
import Scene.ScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.util.concurrent.TimeUnit;

public class PracticeScreenController implements ControlledScreen {

    ScreenController myController;

    @FXML
    private Button backButton;

    @FXML
    private TextArea textArea_Practice;

    @FXML
    private TextArea practice_inputText;

    @FXML
    private Label wpmTab;

    @FXML
    private Label errorTab;

    private static int len;
    private static String diff;
    private boolean check = false;

    // Variable for the logic
    private char[] letters;
    private int wrongCharCounter = 0;
    private int charCounter = 0;
    private long start;
    private long end;
    private int intialCheck = 0;
    // .

    @FXML
    public void initialize() {
        populateTextArea();
        reset();
        wpmTab.setText("0");
    }

    @Override
    public void setScreenParent(ScreenController screenController) {
     myController = screenController;
    }

    @FXML
    public void goToHomeScreen(){
        myController.setScreen(Main.mainScreenId);
        reset();
        wpmTab.setText("0");
    }

    @FXML
    public void handleKeyTyped(){
        char[] inputLetters = practice_inputText.getText().toCharArray();

        for(int i=charCounter; i< inputLetters.length; i++){
            if (inputLetters[i] == letters[i]){
                charCounter++;
                textArea_Practice.selectRange(0, charCounter);
                check = false;
            }else {
                if(check == false){
                    wrongCharCounter++;
                    check = true;
                }
                errorTab.setText(wrongCharCounter+"");
            }
        }

        if(inputLetters.length >= letters.length){
            end = System.nanoTime();
            int speed = calculation();
            DataSource.getInstance().updatePracticeRecord(diff, len, speed, wrongCharCounter);
            reset();
        }
    }

    @FXML
    public void handleMouseClick(){
        if(intialCheck <= 1){
            intialCheck++;
            start = System.nanoTime();
        }
    }

    private int calculation(){
        long elapsedTime = end - start;
        long convert = TimeUnit.SECONDS.convert( elapsedTime, TimeUnit.NANOSECONDS);
        double timeMin = (double)convert / 60;

        int grossWPM = (int) ((double) charCounter/5 /timeMin);
        wpmTab.setText(grossWPM+"");
        return grossWPM;
    }

    private void reset(){
        populateTextArea();
        letters = textArea_Practice.getText().toCharArray();
        practice_inputText.clear();
        errorTab.setText("0");
        charCounter = 0;
        wrongCharCounter = 0;
        check = false;
        intialCheck = 0;
    }

    private void populateTextArea(){
        String paragraph = ParagraphGen.getInstance().generateParagraph(len, diff);
        textArea_Practice.setText(paragraph);
    }

    public static void gatherInfo(int length, String difficulty){
        len = length;
        diff = difficulty;
    }
}
