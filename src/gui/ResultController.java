package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import detector.rulles.FeatureEnvy;
import detector.rulles.LongMethod;
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
	private TableView<Method> tableViewMethod;

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
	private Button btClose;

	private ObservableList<Method> mtdList;

	@FXML
	private void onBtQualidade(ActionEvent event) {

	}

	@FXML
	private void onBtClose(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initializeNodes();
	}

	private void initializeNodes() {
		servidor.setResult(this);
		
		if (obj instanceof LongMethod) {
			labelNome.setText(((LongMethod) obj).getName());
			
		}
		if (obj instanceof FeatureEnvy) {
			labelNome.setText(((FeatureEnvy) obj).getNome());
		}
		
		
		
		tableColumnMethodId.setCellValueFactory(new PropertyValueFactory<>("methodID"));
		tableColumnMethod.setCellValueFactory(new PropertyValueFactory<>("methodName"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewMethod.prefHeightProperty().bind(stage.heightProperty());
		
		resultTableViewMethod();
		
	}

	private void resultTableViewMethod() {
		if (obj == null) {
			throw new IllegalStateException("Regra não identificado");
		}

		
		if (obj instanceof LongMethod) {
			List<Method> list = servidor.findAllMethodToLongMethod((LongMethod) obj);
			mtdList = FXCollections.observableArrayList(list);
			tableViewMethod.setItems(mtdList);
		}
		
		if (obj instanceof FeatureEnvy) {
			List<Method> list = servidor.findAllMethodToFeatureEnvy((FeatureEnvy) obj);
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
