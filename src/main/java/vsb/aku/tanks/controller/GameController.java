package vsb.aku.tanks.controller;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import vsb.aku.tanks.App;
import vsb.aku.tanks.DrawingThread;
import vsb.aku.tanks.model.Level;

public class GameController {
    @FXML
    public Canvas canvas;
    @FXML
    public Text life;
    @FXML
    public Text score;
    private int scoreInt = 0;
    private AnimationTimer animationTimer;

    private App appInstance;
    private final Level level = new Level(this);

    public void setAppInstance(App app) {
        this.appInstance = app;
    }

    public void game() {
        this.animationTimer = new DrawingThread(canvas, level, this);
        animationTimer.start();
    }

    public void stopGame() {
        this.animationTimer.stop();
    }

    public void gameOver() {
        Stage gameOver = this.appInstance.getGameViewStage();
        gameOver.close();
        this.appInstance.exit();
    }

    public Level getLevel() {
        return level;
    }

    public void setLife(int life) {
        this.life.setText(String.valueOf(life));
    }

    public void incrementScore() {
        this.scoreInt += 100;
        this.score.setText(String.valueOf(this.scoreInt));
    }

    public void win(){
        Stage win = this.appInstance.getGameViewStage();
        win.close();
        appInstance.start(new Stage());
    }
}
