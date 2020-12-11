package servidores;

import java.util.ArrayList;
import java.util.List;

import detector.rulles.FeatureEnvy;
import detector.rulles.LongMethod;
import gui.FeatureEnvyListController;
import gui.LongMethodListController;
import gui.ResultController;
import read_write_Excel.Excel;
import software.Method;

public class Servidor {

	private List<LongMethod> longMethodList = new ArrayList<LongMethod>();
	private List<FeatureEnvy> featureEnvyList = new ArrayList<FeatureEnvy>();
	private List<Method> longMethodResult = new ArrayList<Method>();
	private List<Method> featureEnvyResult = new ArrayList<Method>();
	
	private Excel excel = new Excel();
	private ResultController resultController;
	

	private LongMethodListController longMethodListController;
	private FeatureEnvyListController featureEnvyListController;

	public void setLongMethod(LongMethodListController longMethodListController) {
		this.longMethodListController = longMethodListController;
	}

	public void saveOrUpdate(LongMethod obj) {
		for (LongMethod lM : longMethodList) {
			if (lM.getName().equals(obj.getName())) {
				lM.setL_CYCLO(obj.getL_CYCLO());
				lM.setL_LOC(obj.getL_LOC());
				return;
			}
		}
		longMethodList.add(obj);
		longMethodListController.updateTableView();
	}

	public List<LongMethod> findAllLongMethod() {
		return longMethodList;	
	}

	public void remove(LongMethod obj) {
		for (LongMethod lM : longMethodList) {
			if (lM.getName().equals(obj.getName())) {
				longMethodList.remove(obj);
				longMethodListController.updateTableView();
			}
		}

	}

	public List<Method> findAllMethodToLongMethod(LongMethod longMethod) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Method> findAllMethod() {
		List<Method> list = new ArrayList<Method>();
		Excel e = new Excel();
		for(Method m : e.allMethod() ) {
			list.add(m);
		}
		return list;
	}

	public void saveOrUpdate(FeatureEnvy obj) {
		for (FeatureEnvy lM : featureEnvyList) {
			if (lM.getNome().equals(obj.getNome())) {
				lM.setL_ATFD(obj.getL_ATFD());
				lM.setL_LAA(obj.getL_LAA());
				return;
			}
		}
		featureEnvyList.add(obj);
		featureEnvyListController.updateTableView();
		
		
	}

	public void setFeatureEnvy(FeatureEnvyListController featureEnvyListController) {
		this.featureEnvyListController = featureEnvyListController;
		
	}

	public void remove(FeatureEnvy obj) {
		for (FeatureEnvy lM : featureEnvyList) {
			if (lM.getNome().equals(obj.getNome())) {
				featureEnvyList.remove(obj);
				featureEnvyListController.updateTableView();
			}
		}
		
	}

	public List<FeatureEnvy> findAllFeatureEnvy() {
		return featureEnvyList;
	}

	public List<Method> findAllMethodFeatureEnvy() {
		return featureEnvyResult;
	}
	public List<Method> findAllMethodToFeatureEnvy(FeatureEnvy obj) {
		List<Method> list = new ArrayList<Method>();
		for(Method mt : obj.feature_envy(excel.allMethod())) {
			featureEnvyResult .add(mt);
			list.add(mt);
		}
		return list;
	}

	public void setResult(ResultController resultController) {
		this.resultController = resultController;
	}
}
