package vsb.aku.tanks.controller;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import vsb.aku.tanks.App;

public class GameOverConroller {

    private App appInstance;

    public void setAppInstance(App app) {
        this.appInstance = app;
    }
    @FXML
    public void gameExit() {
        Stage currentStage = this.appInstance.getGameOverStage();
        currentStage.close();

        System.exit(0);
    }

    @FXML
    public void mainMenu() {
        Stage currentStage = this.appInstance.getGameOverStage();
        currentStage.close();

        appInstance.start(new Stage());
    }
}
