package vsb.aku.tanks.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class EnemyTank extends BaseTank {
	private int ticks;
	private int fireCount;
	
	public EnemyTank(int id, Level level, Point2D point, EDirection direction) {
		super(id, "enemyTank.png", level, point, 0);
		this.ticks = 0;
		this.fireCount = 0;
		
		switch (direction) {
		case UP:
			this.moveUp();
			break;
		case DOWN:
			this.moveDown();
			break;
		case LEFT:
			this.moveLeft();
			break;
		case RIGHT:
			this.moveRight();
			break;
		default:
			break;	
		}
	}

	@Override
	public void draw(GraphicsContext context) {
		if (ticks == 0) {
			this.fire(false);
		}

		ticks++;
		if (ticks == 100) {
			ticks = 0;
		}
		
		super.draw(context);
	}

	@Override
	public void fire(boolean killable) {
		this.fireCount++;
		if (this.getDirection() == EDirection.LEFT && this.fireCount == 3) {
			setDirection(EDirection.UP);
			this.fireCount = 0;
		}
		if (this.getDirection() == EDirection.UP && this.fireCount == 3) {
			setDirection(EDirection.RIGHT);
			this.fireCount = 0;
		}
		if (this.getDirection() == EDirection.RIGHT && this.fireCount == 3) {
			setDirection(EDirection.DOWN);
			this.fireCount = 0;
		}
		if (this.getDirection() == EDirection.DOWN && this.fireCount == 3) {
			setDirection(EDirection.LEFT);
			this.fireCount = 0;
		}
		super.fire(killable);
	}
}
