package application;
	

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;


public class Main extends Application {
	
	private static Scene mainScene;
	
	public static Scene getMainScene() {
		return mainScene;
	}

	
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml"));
			ScrollPane Pane = loader.load();
			
			Pane.setFitToHeight(true);
			Pane.setFitToWidth(true);
			
			mainScene = new Scene(Pane);
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Code Smells");
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);

	}
}
