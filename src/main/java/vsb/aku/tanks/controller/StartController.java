package vsb.aku.tanks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vsb.aku.tanks.App;

public class StartController {

    private App appInstance;

    public void setAppInstance(App app) {
        this.appInstance = app;
    }
    @FXML
    public void startButton() {
        Stage currentStage = this.appInstance.getMainMenuStage();
        currentStage.close();

        appInstance.startMainGame();
    }

    @FXML
    public void gameExit() {
        Stage currentStage = this.appInstance.getMainMenuStage();
        currentStage.close();

    }
}
