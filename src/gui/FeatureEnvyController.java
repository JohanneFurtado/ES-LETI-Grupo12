package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import detector.rulles.FeatureEnvy;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import servidores.Servidor;

public class FeatureEnvyController implements Initializable {

	private FeatureEnvy obj;

	private Servidor servidor;

	private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtNameEdit;

	@FXML
	private TextField txtLAA;

	@FXML
	private TextField txtATFD;

	@FXML
	private Button btSalvar;

	@FXML
	private Button btEditar;

	@FXML
	private Button btCancelar;

	public void setFeatureEnvy(FeatureEnvy obj) {
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
		if (obj.getL_ATFD() != null && obj.getL_LAA() != null && obj.getNome() != null) {
			servidor.saveOrUpdate(obj);
			notifyDataChangeListeners();
			Utils.currentStage(event).close();
			Alerts.showAlert("Novo/Edição FeatureEnvy", obj.getNome(), "Operacao bem sucedida", AlertType.CONFIRMATION);

		} else {
			Alerts.showAlert("Erro", "existencia de ponto nulo", "Long Method Indefinido", AlertType.ERROR);
		}

	}

	private void notifyDataChangeListeners() {
		for (DataChangeListener listener : dataChangeListeners) {
			listener.onDataChanged();
		}
	}

	private FeatureEnvy getFormData() {
		FeatureEnvy obj = new FeatureEnvy();
		obj.setL_LAA(Utils.tryParseToDouble(txtLAA.getText()));
		obj.setL_ATFD(Utils.tryParseToDouble(txtATFD.getText()));
		obj.setNome(txtName.getText());
		return obj;
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		initializeNodes();
	}

	private void initializeNodes() {
		Constraints.setTextFieldMaxLength(txtName, 30);
		Constraints.setTextFieldDouble(txtLAA);
		Constraints.setTextFieldDouble(txtATFD);

	}

	public void updateFormData() {
		if (obj == null) {
			throw new IllegalStateException("FeatureEnvy esta vazio");
		}
		txtName.setText(obj.getNome());
		txtLAA.setText(String.valueOf(obj.getL_LAA()));
		txtATFD.setText(String.valueOf(obj.getL_ATFD()));
	}
}
