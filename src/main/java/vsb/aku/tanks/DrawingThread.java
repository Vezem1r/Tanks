package vsb.aku.tanks;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import vsb.aku.tanks.controller.GameController;
import vsb.aku.tanks.model.Level;

public class DrawingThread extends AnimationTimer {

    private final Canvas canvas;

    private final Level level;
    private final GameController gameController;

    public DrawingThread(Canvas canvas, Level level, GameController gameController) {
        this.canvas = canvas;
        this.level = level;
        this.gameController = gameController;
    }

    @Override
    public void handle(long now) {
        if (!this.level.draw(this.canvas)) {
            this.stop();
        }
    }
}
