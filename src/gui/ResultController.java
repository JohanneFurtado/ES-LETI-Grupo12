package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import servidores.Servidor;
import software.Method;

public class ResultController implements Initializable {

	private Servidor servidor;
	private LongMethod longMethod;

	@FXML
	private TableView<Method> tableViewMethod;

	@FXML
	private TableColumn<Method, Double> tableColumnMethodId;

	@FXML
	private TableColumn<Method, String> tableColumnMethod;

	@FXML
	private Label labelResultQua;

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
		tableColumnMethodId.setCellValueFactory(new PropertyValueFactory<>("methodID"));
		tableColumnMethod.setCellValueFactory(new PropertyValueFactory<>("methodName"));
	}

	public void resultTableViewMethod() {
		if (longMethod == null) {
			throw new IllegalStateException("LongMethod inexistente");
		}

		List<Method> list = servidor.findAllMethodToLongMethod(longMethod);
		mtdList = FXCollections.observableArrayList(list);
		tableViewMethod.setItems(mtdList);
	}

	public void setService(Servidor servidor) {
		this.servidor = servidor;
	}

	public void setLongMethod(LongMethod obj) {
		this.longMethod = obj;
	}
}
