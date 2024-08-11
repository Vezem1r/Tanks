package vsb.aku.tanks;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import vsb.aku.tanks.model.Level;
import vsb.aku.tanks.model.PlayerTank;

public class GameEventHandler implements EventHandler<KeyEvent> {
    private Level level;

    public GameEventHandler(Level level) {
        this.level = level;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        PlayerTank player = level.getPlayer();
        if (player != null) {
            player.move(keyEvent);
        }
    }
}
