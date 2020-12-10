package gui;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import servidores.Servidor;
import software.Method;

public class ExcelGui implements Initializable{
	
	private Servidor servidor;
	
	private ObservableList<Method> list;
	
	
	@FXML
	private TableView<Method> tableViewMethod;

	@FXML
	private TableColumn<Method, Double> tableColumnMethodId;
	
	@FXML
	private TableColumn<Method, String> tableColumnPackage;
	
	@FXML
	private TableColumn<Method, String> tableColumnClass;
	
	@FXML
	private TableColumn<Method, String> tableColumnMethod;
	
	@FXML
	private TableColumn<Method, Double> tableColumnLAA;

	@FXML
	private TableColumn<Method, Double> tableColumnCYCLO;

	@FXML
	private TableColumn<Method, Double> tableColumnLOC;

	@FXML
	private TableColumn<Method, Double> tableColumnATFD;
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnIsLongMethod;
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnIPlasma;
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnPMD;
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnIsFeatureEnvy;
	
	
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	
	private void initializeNodes() {
		tableColumnMethodId.setCellValueFactory(new PropertyValueFactory<>("methodID"));
		tableColumnPackage.setCellValueFactory(new PropertyValueFactory<>("packageName"));
		tableColumnClass.setCellValueFactory(new PropertyValueFactory<>("className"));
		tableColumnMethod.setCellValueFactory(new PropertyValueFactory<>("methodName"));
		tableColumnLAA.setCellValueFactory(new PropertyValueFactory<>(" n_LAA"));
		tableColumnCYCLO.setCellValueFactory(new PropertyValueFactory<>(" n_CYCLO"));
		tableColumnLOC.setCellValueFactory(new PropertyValueFactory<>(" n_LOC"));
		tableColumnATFD.setCellValueFactory(new PropertyValueFactory<>(" n_ATFD"));
		tableColumnIsLongMethod.setCellValueFactory(new PropertyValueFactory<>("is_long_method"));
		tableColumnIPlasma.setCellValueFactory(new PropertyValueFactory<>("iPlasma"));
		tableColumnPMD.setCellValueFactory(new PropertyValueFactory<>("PMD"));
		tableColumnIsFeatureEnvy.setCellValueFactory(new PropertyValueFactory<>("is_feature_envy"));
		
		Stage stage = (Stage) Main.getMainScene().getWindow();
		tableViewMethod.prefHeightProperty().bind(stage.heightProperty());
	}
	
	
	public void updateTableView() {
		List<Method> lista = servidor.findAllMethod();
		list = FXCollections.observableArrayList(lista);
		tableViewMethod.setItems(list);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
		
	}

	
	

}
