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
	private TableColumn<Method, Double> tableColumnMethodId = new TableColumn<Method, Double>();
	
	@FXML
	private TableColumn<Method, String> tableColumnPackage = new TableColumn<Method, String>();
	
	@FXML
	private TableColumn<Method, String> tableColumnClass = new TableColumn<Method, String>();
	
	@FXML
	private TableColumn<Method, String> tableColumnMethod = new TableColumn<Method, String>();
	
	@FXML
	private TableColumn<Method, Double> tableColumnLAA = new TableColumn<Method, Double>();

	@FXML
	private TableColumn<Method, Double> tableColumnCYCLO = new TableColumn<Method, Double>();

	@FXML
	private TableColumn<Method, Double> tableColumnLOC = new TableColumn<Method, Double>();

	@FXML
	private TableColumn<Method, Double> tableColumnATFD = new TableColumn<Method, Double>();
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnIsLongMethod = new TableColumn<Method, Boolean>();
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnIPlasma= new TableColumn<Method, Boolean>();
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnPMD = new TableColumn<Method, Boolean>();
	
	@FXML
	private TableColumn<Method, Boolean> tableColumnIsFeatureEnvy = new TableColumn<Method, Boolean>();
	
	
	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	
	private void initializeNodes() {
		tableColumnMethodId.setCellValueFactory(new PropertyValueFactory<>("methodID"));
		tableColumnPackage.setCellValueFactory(new PropertyValueFactory<>("packageName"));
		tableColumnClass.setCellValueFactory(new PropertyValueFactory<>("className"));
		tableColumnMethod.setCellValueFactory(new PropertyValueFactory<>("methodName"));
		tableColumnLAA.setCellValueFactory(new PropertyValueFactory<>("LAA"));
		tableColumnCYCLO.setCellValueFactory(new PropertyValueFactory<>("CYCLO"));
		tableColumnLOC.setCellValueFactory(new PropertyValueFactory<>("LOC"));
		tableColumnATFD.setCellValueFactory(new PropertyValueFactory<>("ATFD"));
		tableColumnIsLongMethod.setCellValueFactory(new PropertyValueFactory<>("islongmethod"));
		tableColumnIPlasma.setCellValueFactory(new PropertyValueFactory<>("iPlasma"));
		tableColumnPMD.setCellValueFactory(new PropertyValueFactory<>("PMD"));
		tableColumnIsFeatureEnvy.setCellValueFactory(new PropertyValueFactory<>("isfeatureenvy"));
		
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
