package vsb.aku.tanks;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import vsb.aku.tanks.controller.GameController;
import vsb.aku.tanks.controller.GameOverConroller;
import vsb.aku.tanks.controller.StartController;
import vsb.aku.tanks.model.Level;

import java.net.URL;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	private Stage mainMenuStage;
	private Stage gameViewStage;
	private Stage gameOverStage;
	private GameController controller;

    public void startMainGame() {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource("GameView.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			this.gameViewStage = new Stage();
			this.gameViewStage.setScene(scene);
			this.gameViewStage.resizableProperty().set(false);
			this.gameViewStage.setTitle("Tanks Game");
			this.gameViewStage.show();

			controller = loader.getController();
			controller.setAppInstance(this);
			scene.setOnKeyPressed(new GameEventHandler(controller.getLevel()));
			controller.game();

			this.gameViewStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//@Override
	public void start(Stage startStage) {
		try {
			this.mainMenuStage = startStage;

			URL resource = App.class.getClassLoader().getResource("MainMenu.fxml");
			FXMLLoader loader = new FXMLLoader(resource);
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			startStage.setScene(scene);
			startStage.resizableProperty().set(false);
			startStage.setTitle("Main Menu");
			startStage.show();

			StartController startController = loader.getController();
			startController.setAppInstance(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void exit() {
		try {
			FXMLLoader loader = new FXMLLoader(App.class.getClassLoader().getResource("GameOver.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			this.gameOverStage = new Stage();
			this.gameOverStage.setScene(scene);
			this.gameOverStage.resizableProperty().set(false);
			this.gameOverStage.setTitle("Tanks Game");
			this.gameOverStage.show();

            GameOverConroller gameOverConroller = loader.getController();
			gameOverConroller.setAppInstance(this);

			this.gameOverStage.setOnCloseRequest(this::exitProgram);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void exitProgram(WindowEvent evt) {
		controller.stopGame();
		System.exit(0);
	}

	public Stage getMainMenuStage() {
		return mainMenuStage;
	}

	public Stage getGameViewStage() {
		return gameViewStage;
	}

	public Stage getGameOverStage() {
		return gameOverStage;
	}
}