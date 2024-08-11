package vsb.aku.tanks.model;

import javafx.geometry.Point2D;

public class Brick extends BaseModel {
	public Brick(int id, Level level, Point2D point) {
		super(id, "brick.png", level, point);
	}
}
