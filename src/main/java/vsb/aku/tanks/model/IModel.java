package vsb.aku.tanks.model;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public interface IModel {
	Point2D getPosition();
	
	void setPosition(Point2D position);
	
	Image getSprite();
	
	void setSprite(Image image);
	
	void draw(GraphicsContext context);
	
	Point2D getSize();
	
	int getId();
	
	void setId(int id);
}
