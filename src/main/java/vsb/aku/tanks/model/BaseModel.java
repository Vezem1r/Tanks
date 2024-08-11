package vsb.aku.tanks.model;

import java.net.URL;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import vsb.aku.tanks.App;
import vsb.aku.tanks.Utils;

public class BaseModel implements IModel {
	private Point2D point;
	private Image image;
	private int id;
	
	public BaseModel(int id, String resourceName, Level level, Point2D point) {
		this(id, resourceName, level, point, true);
	}
	
	public BaseModel(int id, String resourceName, Level level, Point2D point, boolean resize) {
		URL filename = App.class.getClassLoader().getResource(resourceName);
		
		this.image = new Image(filename.toExternalForm());
		if (resize)
			this.image = Utils.resize(this.image, level.getBlockSize());
		this.point = point;
		this.id = id;
	}
	
	@Override
	public Point2D getPosition() {
		return this.point;
	}

	@Override
	public Image getSprite() {
		return this.image;
	}

	@Override
	public void draw(GraphicsContext context) {
		context.drawImage(this.image, this.point.getX(), this.point.getY());
	}

	@Override
	public void setPosition(Point2D position) {
		this.point = position;
	}

	@Override
	public int getId() {
		return this.id;
	}

	@Override
	public void setSprite(Image image) {
		this.image = image;
	}

	@Override
	public Point2D getSize() {
		Point2D size = new Point2D(this.image.getWidth(), this.image.getHeight());
		return size;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}
}
