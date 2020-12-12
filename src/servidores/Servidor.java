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

	private List<LongMethod> longMethodList = new ArrayList<LongMethod>();
	private List<FeatureEnvy> featureEnvyList = new ArrayList<FeatureEnvy>();
	private List<Method> featureEnvyResult = new ArrayList<Method>();
	
	
	private  Excel excel;
	private LongMethodTXT lMtxt;
	private FeatureEnvyTXT fEtxt;
	
	
	public Servidor(){
		excel = new Excel();
		lMtxt = new LongMethodTXT();
		fEtxt = new FeatureEnvyTXT();
		
		
		excel.readFile();
		longMethodList = lMtxt.leitor();
		featureEnvyList = fEtxt.leitor();
	}
	
	public void  writeLMtxt() {
	}
	

	private LongMethodListController longMethodListController;
	private FeatureEnvyListController featureEnvyListController;

	public void setLongMethod(LongMethodListController longMethodListController) {
		this.longMethodListController = longMethodListController;
	}
	
	public void setFeatureEnvy(FeatureEnvyListController featureEnvyListController) {
		this.featureEnvyListController = featureEnvyListController;
		
	}

	public void setResult(ResultController resultController) {
		resultController.resultTableViewMethod();
	}

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
	
	public void remove(LongMethod obj) {
		for (LongMethod lM : longMethodList) {
			if (lM.getName().equals(obj.getName())) {
				longMethodList.remove(obj);
				lMtxt.escritor(longMethodList);
				longMethodListController.updateTableView();
			}
		}

	}
	
	public void remove(FeatureEnvy obj) {
		for (FeatureEnvy lM : featureEnvyList) {
			if (lM.getNome().equals(obj.getNome())) {
				featureEnvyList.remove(obj);
				fEtxt.escritor(featureEnvyList);
				featureEnvyListController.updateTableView();
			}
		}
		
	}
	
	public List<LongMethod> findAllLongMethod() {
		return longMethodList;	
	}

	public List<Method> findAllMethod() {
		List<Method> list = new ArrayList<Method>();
		for(Method m : excel.allMethod() ) {
			list.add(m);
		}
		return list;
	}

	
	public List<FeatureEnvy> findAllFeatureEnvy() {
		return featureEnvyList;
	}

	public List<Method> findAllMethodFeatureEnvy() {
		return featureEnvyResult;
	}
}
