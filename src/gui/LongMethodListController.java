package gui;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import application.Main;
import detector.rulles.LongMethod;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import servidores.Servidor;

public class LongMethodListController implements Initializable, DataChangeListener {

	private Servidor servidor;

	@FXML
	private TableView<LongMethod> tableViewLongMethod;

	@FXML
	private TableColumn<LongMethod, String> tableColumnName;

	@FXML
	private TableColumn<LongMethod, LongMethod> tableColumnEDIT;

	@FXML
	private TableColumn<LongMethod, LongMethod> tableColumnREMOVE;

	@FXML
	private TableColumn<LongMethod, LongMethod> tableColumnAPLAY;

	@FXML
	private Button btNew;

	private ObservableList<LongMethod> obsList;

	public void setService(Servidor servidor) {
		this.servidor = servidor;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();

	}

	@FXML
	public void onBtNew(ActionEvent event) {
		Stage parentStage = Utils.currentStage(event);
		createDialogFrom("/gui/LongMethodNew.fxml", parentStage);
	}

	private void createDialogFrom(String absoluto, Stage parentStage) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluto));
			Pane pane = loader.load();

			servidor.setLongMethod(this);

			LongMethodController controller = loader.getController();
			controller.setService(servidor);
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com nova edicao de regras");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}
	
	private void createDialogFromAplay(LongMethod obj ,String absoluto, Stage parentStage) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluto));
			Pane pane = loader.load();

			servidor.setLongMethod(this);
			
			ResultController controller = new ResultController();
			controller.setLongMethod(obj);
			controller.setService(servidor);
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Resultado");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private void createDialogFromEdit(LongMethod obj, String absoluto, Stage parentStage) {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluto));
			Pane pane = loader.load();
			
			servidor.setLongMethod(this);

			LongMethodController controller = loader.getController();
			controller.setLongMethod(obj);
			controller.setService(servidor);
			controller.subscribeDataChangeListener(this);
			controller.updateFormData();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Entre com nova edicao de regras");
			dialogStage.setScene(new Scene(pane));
			dialogStage.setResizable(false);
			dialogStage.initOwner(parentStage);
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.showAndWait();

		} catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
	}

	private void initializeNodes() {
		tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewLongMethod.prefHeightProperty().bind(stage.heightProperty());

	}

	@Override
	public void onDataChanged() {
		updateTableView();
	}

	public void updateTableView() {
		List<LongMethod> list = servidor.findAllLongMethod();
		obsList = FXCollections.observableArrayList(list);
		tableViewLongMethod.setItems(obsList);
		initEditButtons();
		initRemoveButtons();
		initAplayButtons();
	}

	private void initRemoveButtons() {
		tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnREMOVE.setCellFactory(param -> new TableCell<LongMethod, LongMethod>() {
			private final Button button = new Button("remover");

			@Override
			protected void updateItem(LongMethod obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(event -> removeEntity(obj));
			}
		});
	}

	private void removeEntity(LongMethod obj) {
		Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Tens a certeza que queres apagar?");

		if (result.get() == ButtonType.OK) {
			if (servidor == null) {
				throw new IllegalStateException("Service was null");
			}
			try {
				servidor.setLongMethod(this);
				servidor.remove(obj);
				updateTableView();
			} catch (RuntimeException e) {
				Alerts.showAlert("Error removing object", null, e.getMessage(), AlertType.ERROR);
			}
		}
	}

	private void initEditButtons() {
		tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnEDIT.setCellFactory(param -> new TableCell<LongMethod, LongMethod>() {
			private final Button button = new Button("editar");

			@Override
			protected void updateItem(LongMethod obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogFromEdit(obj,"/gui/LongMethodEdit.fxml", Utils.currentStage(event)));
			}
		});
	}

	private void initAplayButtons() {
		tableColumnAPLAY.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tableColumnAPLAY.setCellFactory(param -> new TableCell<LongMethod, LongMethod>() {
			private final Button button = new Button("aplay");

			@Override
			protected void updateItem(LongMethod obj, boolean empty) {
				super.updateItem(obj, empty);
				if (obj == null) {
					setGraphic(null);
					return;
				}
				setGraphic(button);
				button.setOnAction(
						event -> createDialogFromAplay(obj ,"/gui/LongMethodResult.fxml", Utils.currentStage(event)));
			}
		});
	}

}
