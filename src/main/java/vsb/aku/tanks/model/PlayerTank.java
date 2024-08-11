package vsb.aku.tanks.model;

import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import vsb.aku.tanks.controller.GameController;

public class PlayerTank extends BaseTank {
	private int health;
	private GameController gameController;
	
	public PlayerTank(int id, Level level, Point2D point, GameController gameController) {
		super(id, "playerTank.png", level, point, 3);
		this.health = 3;
		this.gameController = gameController;
	}
	
	public void move(KeyEvent event) {
		KeyCode code = event.getCode();
		
		switch (code) {
		case W:
			this.moveUp();
			break;
		case S:
			this.moveDown();
			break;
		case D:
			this.moveRight();
			break;
		case A:
			this.moveLeft();
			break;
		case SPACE:
			this.fire();
			break;
		default:
			break;
		}
	}
	
	public void decrementHealth() {
		this.health--;
		this.gameController.setLife(this.health);
	}
	
	public int getHelth() {
		return this.health;
	}
}
