package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import detector.rulles.FeatureEnvy;
import detector.rulles.LongMethod;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import servidores.Servidor;
import software.Method;

public class ResultController implements Initializable {

	private Servidor servidor;

	private Object obj;

	@FXML
	private TableView<Method> tableViewMethod = new TableView<Method>();

	@FXML
	private TableColumn<Method, Double> tableColumnMethodId;

	@FXML
	private TableColumn<Method, String> tableColumnMethod;

	@FXML
	private Label labelResultQua;

	@FXML
	private Label labelNome;

	@FXML
	private Button btQualidade;
	
	@FXML
	private Button btIPlasma;
	
	@FXML
	private Button btPMD;

	@FXML
	private Button btClose;

	private ObservableList<Method> mtdList;

	@FXML
	private void onBtQualidade(ActionEvent event) {
		if (obj instanceof FeatureEnvy) {
			labelResultQua.setText(((FeatureEnvy) obj).isfeatureEnvyDet());
		}
		
		if (obj instanceof LongMethod) {
			labelResultQua.setText(((LongMethod) obj).islongMethod());
		}
	}
	
	@FXML
	private void onBtIPlasma(ActionEvent event) {
		if (obj instanceof LongMethod) {
			labelResultQua.setText(((LongMethod) obj).isIPlasma());
		}
		
		if (obj instanceof FeatureEnvy) {
			labelResultQua.setText(((FeatureEnvy) obj).isIPlasma());
		}
	}
	
	@FXML
	private void onBtPMD(ActionEvent event) {
		if (obj instanceof LongMethod) {
			labelResultQua.setText(((LongMethod) obj).isPMD());
		}
		
		if (obj instanceof FeatureEnvy) {
			labelResultQua.setText(((FeatureEnvy) obj).isPMD());
		}
	}

	@FXML
	private void onBtClose(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
	}

	private void initializeNodes() {

		tableColumnMethodId.setCellValueFactory(new PropertyValueFactory<>("methodID"));
		tableColumnMethod.setCellValueFactory(new PropertyValueFactory<>("methodName"));

		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewMethod.prefHeightProperty().bind(stage.heightProperty());
	}

	public void resultTableViewMethod() {
		if (obj == null) {
			throw new IllegalStateException("Regra não identificado");
		}

		if (obj instanceof LongMethod) {
			labelNome.setText(((LongMethod) obj).getName());
			List<Method> list = ((LongMethod) obj).longMethod(servidor.findAllMethod());
			mtdList = FXCollections.observableArrayList(list);
			tableViewMethod.setItems(mtdList);
		}

		if (obj instanceof FeatureEnvy) {
			labelNome.setText(((FeatureEnvy) obj).getNome());
			List<Method> list = ((FeatureEnvy) obj).feature_envy(servidor.findAllMethod());
			mtdList = FXCollections.observableArrayList(list);
			tableViewMethod.setItems(mtdList);
		}
	}

	public void setService(Servidor servidor) {
		this.servidor = servidor;
	}

	public void setObject(Object obj) {
		this.obj = obj;
	}

}
