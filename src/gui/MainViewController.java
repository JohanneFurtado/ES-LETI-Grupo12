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
import javafx.scene.layout.VBox;
import servidores.Servidor;

public class MainViewController implements Initializable {

	@FXML
	private MenuItem menuLongMethod;
	
	@FXML
	private MenuItem menuAbout;
	
	@FXML
	private MenuItem menuAboutLongMethod;
	
	@FXML
	private MenuItem menuFeatureEnvy;
	
	@FXML
	private MenuItem menuAboutFeatureEnvy;
	

	
	@FXML
	private Button btView;
	
	private Servidor service = new Servidor();

	@FXML
	public void onMenuItemLongMethod() {
		loadView("/gui/LongMethod.fxml", (LongMethodListController controller) -> {
			controller.setService(service);
			controller.updateTableView();
		});
	}

	@FXML
	public void onMenuItemFeatureEnvy() {
		loadView("/gui/FeatureEnvy.fxml", (FeatureEnvyListController controller) -> {
			controller.setService(service);
			controller.updateTableView();
		});
	}
	
	@FXML
	public void onMenuItemAboutLongMethod() {
		loadView("/gui/AboutLongMethod.fxml",x -> {});
	}
	
	@FXML
	public void onMenuItemAboutFeatureEnvy() {
		loadView("/gui/AboutFeatureEnvy.fxml",x -> {});
	}
	
	@FXML
	public void onMenuItemAbout() {
		loadView("/gui/About.fxml",x -> {});
	}
	
	
	@FXML
	public void onBtView(ActionEvent event) {
		
		loadView("/gui/ExcelGUI.fxml", (ExcelGui controller) -> {
			controller.setServidor(service);
			controller.updateTableView();
		});
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
