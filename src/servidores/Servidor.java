package servidores;

import java.util.ArrayList;
import java.util.List;

import detector.rulles.FeatureEnvy;
import detector.rulles.LongMethod;
import gui.LongMethodListController;
import read_write_Excel.Excel;
import software.Method;

public class Servidor {

	private List<LongMethod> longMethodList = new ArrayList<LongMethod>();
	private List<FeatureEnvy> featureEnvyList = new ArrayList<FeatureEnvy>();

	private LongMethodListController longMethodListController;

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
		for(int i =0; i<10; i++) {
			System.out.println(i);
		}
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
		featureEnvyList.add(obj);
		
	}
}
