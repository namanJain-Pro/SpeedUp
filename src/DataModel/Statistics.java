package DataModel;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Statistics {
    SimpleStringProperty difficulty;
    SimpleIntegerProperty paraLength;
    SimpleIntegerProperty wpm;
    SimpleIntegerProperty error;
    SimpleIntegerProperty time;

    public Statistics(String difficulty, int paraLength, int wpm, int error, int time) {
        this.difficulty = new SimpleStringProperty(difficulty);
        this.paraLength = new SimpleIntegerProperty(paraLength);
        this.wpm = new SimpleIntegerProperty(wpm);
        this.error = new SimpleIntegerProperty(error);
        this.time = new SimpleIntegerProperty(time != 0 ? time : 0);
    }

    public String getDifficulty() {
        return difficulty.get();
    }

    public SimpleStringProperty difficultyProperty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty.set(difficulty);
    }

    public int getParaLength() {
        return paraLength.get();
    }

    public SimpleIntegerProperty paraLengthProperty() {
        return paraLength;
    }

    public void setParaLength(int paraLength) {
        this.paraLength.set(paraLength);
    }

    public int getWpm() {
        return wpm.get();
    }

    public SimpleIntegerProperty wpmProperty() {
        return wpm;
    }

    public void setWpm(int wpm) {
        this.wpm.set(wpm);
    }

    public int getError() {
        return error.get();
    }

    public SimpleIntegerProperty errorProperty() {
        return error;
    }

    public void setError(int error) {
        this.error.set(error);
    }

    public int getTime() {
        return time.get();
    }

    public SimpleIntegerProperty timeProperty() {
        return time;
    }

    public void setTime(int time) {
        this.time.set(time);
    }
}