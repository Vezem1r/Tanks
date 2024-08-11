package vsb.aku.tanks.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import vsb.aku.tanks.Utils;

public class BaseTank extends BaseModel {
	private final int speed;
	private final Level level;
	private EDirection direction;
	private final Image originalImage;
	private final Collision collision;
	
	public BaseTank(int id, String resourceName, Level level, Point2D point, int speed) {
		super(id, resourceName, level, point);
		this.speed = speed;
		this.level = level;
		this.direction = EDirection.RIGHT;
		this.originalImage = this.getSprite();
		this.collision = new Collision(level);
	}
	
	public void moveUp() {
		Point2D position = this.getPosition();
		this.setPosition(position.add(0, -speed));
		if (collision.check(this) != null) {
			this.setPosition(position);
		}
		this.direction = EDirection.UP;
	}
	
	public void moveDown() {
		Point2D position = this.getPosition();
		this.setPosition(position.add(0, speed));
		if (collision.check(this) != null) {
			this.setPosition(position);
		}
		this.direction = EDirection.DOWN;
	}
	
	public void moveLeft() {
		Point2D position = this.getPosition();
		this.setPosition(position.add(-speed, 0));
		if (collision.check(this) != null) {
			this.setPosition(position);
		}
		this.direction = EDirection.LEFT;
	}
	
	public void moveRight() {
		Point2D position = this.getPosition();
		this.setPosition(position.add(speed, 0));
		if (collision.check(this) != null) {
			this.setPosition(position);
		}
		this.direction = EDirection.RIGHT;
	}
	
	public void fire() {
		fire(true);
	}

	public EDirection getDirection() {
		return direction;
	}

	public void setDirection(EDirection direction) {
		this.direction = direction;
	}

	public void fire(boolean killable) {
		Point2D position = new Point2D(this.getPosition().getX(), this.getPosition().getY());
		double x = this.getSprite().getWidth();
		double y = this.getSprite().getHeight();
		
		switch(this.direction) {
		case UP:
			position = position.add(x / 2, -y);
			break;
		case DOWN:
			position = position.add(x / 2, y);
			break;
		case LEFT:
			position = position.add(-x, y / 2);
			break;
		case RIGHT:
			position = position.add(x, y / 2);
			break;
		default:
			break;
		}
		
		this.level.spawn(new Bullet(0, level, position, direction, killable));
	}

	@Override
	public void draw(GraphicsContext context) {
		int angle = 0;
		switch (this.direction) {
		case RIGHT:
			angle = 90;
			break;
		case LEFT:
			angle = 270;
			break;
		case DOWN:
			angle = 180;
			break;
		default:
			break;	
		}
		
		this.setSprite(Utils.rotate(this.originalImage, angle));
		super.draw(context);
	}
}
