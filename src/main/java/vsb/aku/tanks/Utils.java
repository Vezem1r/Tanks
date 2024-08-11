package vsb.aku.tanks;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import vsb.aku.tanks.model.EType;

public class Utils {
	public static Image resize(Image image, Point2D size) {
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(size.getX());
		imageView.setFitHeight(size.getY());
		
		return imageView.snapshot(null, null);
	}
	
	public static Image rotate(Image image, int angle) {
		ImageView imageView = new ImageView(image);
		imageView.setRotate(angle);

		return imageView.snapshot(null, null);
	}
	
	public static EType convert(char symbol) {
		switch (symbol) {
		case '-':
			return EType.BRICK;
		case 'x':
			return EType.TANK;
		case 'y':
			return EType.ENEMY_TANK;
		case '+':
			return EType.SOLID;
		default:
			return EType.NONE;
		}
	}
}
