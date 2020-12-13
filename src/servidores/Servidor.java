package servidores;

import java.util.ArrayList;
import java.util.List;

import detector.rulles.FeatureEnvy;
import detector.rulles.LongMethod;
import gui.FeatureEnvyListController;
import gui.LongMethodListController;
import gui.ResultController;
import read_write.Excel;
import read_write.FeatureEnvyTXT;
import read_write.LongMethodTXT;
import software.Method;

public class Servidor {

	/**
	 *  Listas do servidor
	 *  Optamos pela implementacao do servidor de moda a separa os conceitos do backend
	 *   com a interecao do utilizador
	 */
	private List<LongMethod> longMethodList = new ArrayList<LongMethod>();
	private List<FeatureEnvy> featureEnvyList = new ArrayList<FeatureEnvy>();
	private List<Method> featureEnvyResult = new ArrayList<Method>();
	
	/***
	 * Para facilitar a linguagem de programacao o proprio servidor toma 
	 * a liberdade de começar a leitura do ficheiro e a consulta a banco de dados 
	 */
	private  Excel excel;
	private LongMethodTXT lMtxt;
	private FeatureEnvyTXT fEtxt;
	
	/**
	 * 
	 */
	public Servidor(){
		excel = new Excel();
		lMtxt = new LongMethodTXT();
		fEtxt = new FeatureEnvyTXT();
		
		
		excel.readFile();
		longMethodList = lMtxt.leitor();
		featureEnvyList = fEtxt.leitor();
	}
	
	/**
	 * 
	 */
	private LongMethodListController longMethodListController;
	private FeatureEnvyListController featureEnvyListController;
	
	

	/**
	 * 
	 * @param longMethodListController
	 */
	public void setLongMethod(LongMethodListController longMethodListController) {
		this.longMethodListController = longMethodListController;
	}
	
	/**
	 * 
	 * @param featureEnvyListController
	 */
	public void setFeatureEnvy(FeatureEnvyListController featureEnvyListController) {
		this.featureEnvyListController = featureEnvyListController;
		
	}

	public void setResult(ResultController resultController) {
		resultController.resultTableViewMethod();
	}

	/**
	 * Salvar ou alterar uma regra Long Method
	 * @param obj
	 */
	public void saveOrUpdate(LongMethod obj) {
		for (LongMethod lM : longMethodList) {
			if (lM.getName().equals(obj.getName())) {
				lM.setL_CYCLO(obj.getL_CYCLO());
				lM.setL_LOC(obj.getL_LOC());
				lM.setTipo(obj.getTipo());
				lMtxt.escritor(longMethodList);
				return;
			}
		}
		longMethodList.add(obj);
		lMtxt.escritor(longMethodList);
		longMethodListController.updateTableView();
	}

	/**
	 * Salvar ou alterar uma regra Feature Envy
	 * @param obj
	 */
	public void saveOrUpdate(FeatureEnvy obj) {
		for (FeatureEnvy lM : featureEnvyList) {
			if (lM.getNome().equals(obj.getNome())) {
				lM.setL_ATFD(obj.getL_ATFD());
				lM.setL_LAA(obj.getL_LAA());
				lM.setTipo(obj.getTipo());
				fEtxt.escritor(featureEnvyList);
				return;
			}
		}
		featureEnvyList.add(obj);
		fEtxt.escritor(featureEnvyList);
		featureEnvyListController.updateTableView();
		
		
	}
	
	/**
	 * Remocao de uma Long Method
	 * @param obj
	 */
	public void remove(LongMethod obj) {
		for (LongMethod lM : longMethodList) {
			if (lM.getName().equals(obj.getName())) {
				longMethodList.remove(obj);
				lMtxt.escritor(longMethodList);
				longMethodListController.updateTableView();
			}
		}

	}
	
	/**
	 * Remocao de uma Feature Envy
	 * @param obj
	 */
	public void remove(FeatureEnvy obj) {
		for (FeatureEnvy lM : featureEnvyList) {
			if (lM.getNome().equals(obj.getNome())) {
				featureEnvyList.remove(obj);
				fEtxt.escritor(featureEnvyList);
				featureEnvyListController.updateTableView();
			}
		}
		
	}
	
	/**
	 * Todas as regras Long Method existente
	 * @return
	 */
	public List<LongMethod> findAllLongMethod() {
		return longMethodList;	
	}

	/**
	 * Todas as linha do excel que foram transformada em um objeto do tipo Method
	 * @return
	 */
	public List<Method> findAllMethod() {
		List<Method> list = new ArrayList<Method>();
		for(Method m : excel.allMethod() ) {
			list.add(m);
		}
		return list;
	}

	
	/**
	 * Todas as Regras Feature Envy existentes
	 * @return
	 */
	public List<FeatureEnvy> findAllFeatureEnvy() {
		return featureEnvyList;
	}
}
