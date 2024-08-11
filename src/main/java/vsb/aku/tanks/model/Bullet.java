package vsb.aku.tanks.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import vsb.aku.tanks.controller.GameController;

public class Bullet extends BaseModel {
	private final int speed;
	private final EDirection direction;
	private final Collision collision;
	private final Level level;
	private final boolean killable;
	private final GameController gameController;
	
	public Bullet(int id, Level level, Point2D point, EDirection direction, boolean killable) {
		super(id, "bullet.png", level, point, false);
		this.speed = 5;
		this.direction = direction;
		this.collision = new Collision(level);
		this.level = level;
		this.killable = killable;
		this.gameController = level.getGameController();
	}

	@Override
	public void draw(GraphicsContext context) {
		Point2D position = this.getPosition();
		switch (this.direction) {
		case UP:
			this.setPosition(position.add(0, -this.speed));
			break;
		case DOWN:
			this.setPosition(position.add(0, this.speed));
			break;
		case RIGHT:
			this.setPosition(position.add(this.speed, 0));
			break;
		case LEFT:
			this.setPosition(position.add(-this.speed, 0));
			break;
		}
		IModel model = this.collision.check(this);
		if (model != null) {
			this.level.destroy(this.getId());
			if (this.killable) {
				if (model instanceof EnemyTank) {
					this.gameController.incrementScore();
				}
				if (!(model instanceof Solid)) {
					this.level.destroy(model.getId());
				}
			}
			if (model instanceof PlayerTank player) {
				player.decrementHealth();
			}
			return;
		}
 
		super.draw(context);
	}
}
