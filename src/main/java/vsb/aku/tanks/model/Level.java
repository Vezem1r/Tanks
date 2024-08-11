package vsb.aku.tanks.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import vsb.aku.tanks.App;
import vsb.aku.tanks.Utils;
import vsb.aku.tanks.controller.GameController;

public class Level {
	private List<IModel> models;
	private final Point2D blockSize; 
	private int playerId;
	private GameController gameController;
	
	public Level(GameController gameController) {
		this.models = new ArrayList<>();
		this.blockSize = new Point2D(39, 39);
		this.playerId = -1;
		this.gameController = gameController;
		
		Init("level.txt");
	}
	
	public boolean draw(Canvas canvas) {
		if (this.playerId != -1 
				&& ((PlayerTank) this.models.get(this.playerId)).getHelth() == 0) {
			this.gameController.gameOver();
			return false;
		}

		if (this.countEnemies() == 0) {
			this.gameController.win();
			return false;
		}
		GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        List<IModel> copyModels = new ArrayList<IModel>(this.models);  // Otherwise race condition
        
        for (IModel model : copyModels) {
        	if (model == null) {
        		continue;
        	}
        	
    		if (model.getPosition().getX() >= canvas.getWidth() || model.getPosition().getY() >= canvas.getHeight()) {
    			this.destroy(model.getId()); 
    		}
    		model.draw(gc);
        }

		return true;
	}
	
	public Point2D getBlockSize() {
		return this.blockSize;
	}

	private int countEnemies() {
		int count = 0;
		List<IModel> copyModels = new ArrayList<IModel>(this.models);
		for (IModel model : copyModels) {
			if (model instanceof EnemyTank) {
				count++;
			}
		}

		return count;
	}
	
	private void Init(String resourceName) {
		InputStream stream = App.class.getClassLoader().getResourceAsStream(resourceName);
		List<String> lines = new ArrayList<String>();
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
			String line;
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < lines.size(); i++) {
			for (int j = 0; j < lines.get(i).length(); j++) {
				double x = j * this.blockSize.getX();
				double y = i * this.blockSize.getY();
				Point2D point = new Point2D(x, y);
				
				
				switch (Utils.convert(lines.get(i).charAt(j))) {
				case BRICK:
					this.spawn(new Brick(0, this, point));
					break;
				case SOLID:
					this.spawn(new Solid(0, this, point));
					break;
				case TANK:
					this.spawn(new PlayerTank(0, this, point, this.gameController));
					break;
				case ENEMY_TANK:
					this.spawn(new EnemyTank(0, this, point, EDirection.LEFT));
				default:
					break;
				}
			}
		}
	}
	
	public void spawn(IModel model) {
		int id = this.models.size();
		
		for (int i = 0; i < this.models.size(); ++i) {
			if (this.models.get(i) == null) {
				id = i;
				break;
			}
		}
		model.setId(id);

		if (model instanceof PlayerTank) {
			this.playerId = id;
		}
		
		if (id == this.models.size()) {
			this.models.add(model);
		} else {
			this.models.set(id, model);
		}
	}
	
	public void destroy(int id) {
		this.models.set(id, null);
	}
	
	public PlayerTank getPlayer() {
		if (this.playerId == -1) {
			return null;
		}
		
		return (PlayerTank) this.models.get(playerId);
	}
	
	public List<IModel> getModels() {
		return this.models;
	}

	public GameController getGameController() {
		return gameController;
	}
}
