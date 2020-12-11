package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import servidores.Servidor;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuLongMethod;
	
	@FXML
	private MenuItem menuFeatureEnvy;
	
	@FXML
	private MenuItem menuPrincipal;
	
	@FXML
	private Button btView;

	@FXML
	public void onMenuItemLongMethod() {
		loadView("/gui/LongMethod.fxml", (LongMethodListController controller) -> {
			controller.setService(new Servidor());
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemFeatureEnvy() {
		loadView("/gui/FeatureEnvy.fxml", (FeatureEnvyListController controller) -> {
			controller.setService(new Servidor());
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemPrincipal() {
		loadView("/gui/MainView.fxml",x -> {});
	}
	
	
	@FXML
	public void onBtView(ActionEvent event) {
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ExcelGUI.fxml"));
			Pane Pane = loader.load();
		/*
		 * 
		 * fiquei parado aqui
		 * acho que o eclipse esta cansado
		 * 
		 * pergunta ao stor o porque que isso não da erro e tambem não funciona
		 * 
		 * se eu usar o meu alert para indicar o erro, é demonstrado apenas o caminho do ExcelGUI.
		 * 
		 * já fiz o long method, é so seguir o modelo consegues fazer o do Feature Envy
		 * 
		 * Diz ao stor para configurar o maven para poder correr o projeto no maven
		 * 
		 * vemos a noite.	
		 */
//			ExcelGui ex = new ExcelGui();
//			ex.setServidor(new Servidor());
//			ex.updateTableView();
			
			Scene mainScene = new Scene(Pane);
			Stage primaryStage = new Stage();
			primaryStage.setScene(mainScene);
			primaryStage.setTitle("Code Smells-Excel");
			primaryStage.show();			
		}catch (IOException e) {
			e.getMessage();
		}catch (RuntimeException ex) {
			ex.getMessage();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	private synchronized <T> void loadView(String absoluteName, Consumer<T> initializingAction) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVBox = loader.load();

			Scene mainScene = Main.getMainScene();
			VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

			Node mainMenu = mainVBox.getChildren().get(0);
			mainVBox.getChildren().clear();
			mainVBox.getChildren().add(mainMenu);
			mainVBox.getChildren().addAll(newVBox.getChildren());

			T controller = loader.getController();
			initializingAction.accept(controller);

		} catch (IOException e) {
			e.getMessage();
		}
	}

}
