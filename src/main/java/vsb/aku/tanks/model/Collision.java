package vsb.aku.tanks.model;

import javafx.geometry.Point2D;

public class Collision {
	private final Level level;
	
	public Collision(Level level) {
		this.level = level;
	}

	public IModel check(IModel object) {
		Point2D objectPosition = object.getPosition();

		for (IModel model : this.level.getModels()) {
			if (model == null) {
				continue;
			}
			Point2D modelPosition = model.getPosition();
			Point2D modelSize = model.getSize();
			boolean checkX = objectPosition.getX() > modelPosition.getX() &&
					objectPosition.getX() < modelPosition.getX() + modelSize.getX();
			boolean checkY = objectPosition.getY()  > modelPosition.getY() &&
					objectPosition.getY() < modelPosition.getY() + modelSize.getY();

			if (checkX && checkY) {
				return model;
			}
		}

		return null;
	}
}
