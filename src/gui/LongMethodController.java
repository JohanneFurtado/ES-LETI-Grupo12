package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import detector.rulles.LongMethod;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import servidores.Servidor;

public class LongMethodController implements Initializable {

	private LongMethod obj;

	private Servidor servidor;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtCYCLO;

	@FXML
	private TextField txtLOC;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btEditar;

	@FXML
	private Button btCancelar;
	
	@FXML
	private ComboBox<String> cbTipo;

	
	private ObservableList<String> tipo = FXCollections.observableArrayList("AND", "OR");

	public void setLongMethod(LongMethod obj) {
		this.obj = obj;

	}

	public void setService(Servidor servidor) {
		this.servidor = servidor;

	}

	public void subscribeDataChangeListener(DataChangeListener listener) {
		dataChangeListeners.add(listener);

	}

	@FXML
	public void onBtCancelAction(ActionEvent event) {
		Utils.currentStage(event).close();
	}

	@FXML
	public void onBtSaveAction(ActionEvent event) {
		obj = getFormData();
		if (obj.getL_CYCLO() != null && obj.getL_LOC() != null && obj.getName() != null && obj.getTipo() != null) {
			servidor.saveOrUpdate(obj);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
			Alerts.showAlert("Novo/Edição LongMethod", obj.getName(), "Operacao bem sucedida", AlertType.INFORMATION);

		} else {
			Alerts.showAlert("Erro", "existencia de ponto nulo", "Long Method Indefinido", AlertType.ERROR);
		}

	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private LongMethod getFormData() {
		LongMethod obj = new LongMethod();
		obj.setL_CYCLO(Utils.tryParseToDouble(txtCYCLO.getText()));
		obj.setL_LOC(Utils.tryParseToDouble(txtLOC.getText()));
		obj.setName(txtName.getText());
		obj.setTipo(cbTipo.getValue());
		return obj;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtName, 30);
		Constraints.setTextFieldDouble(txtCYCLO);
		Constraints.setTextFieldDouble(txtLOC);
		for (String s : tipo) {
			cbTipo.getItems().add(s);
		}

	}

	public void updateFormData() {
		if (obj == null) {
			throw new IllegalStateException("LongMethod esta vazio");
		}
		txtName.setText(obj.getName());
		txtCYCLO.setText(String.valueOf(obj.getL_CYCLO()));
		txtLOC.setText(String.valueOf(obj.getL_LOC()));
	}
}
