module Speed.Up.Mini.Project.With.Planning {

    requires javafx.fxml;
    requires javafx.controls;

    opens Run;
    opens Scene;
    opens Scene.MainScreen;
    opens Scene.Login;
    opens Scene.PracticeScreen;
    opens Scene.TestScreen;
}