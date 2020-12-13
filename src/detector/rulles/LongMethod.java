package detector.rulles;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import software.Method;

/**
 * Classe representativa de Long Method
 *
 *
 */
public class LongMethod {

	/**
	 * Atributos
	 */
	private Double l_LOC;
	private Double l_CYCLO;
	private String name;
	private String tipo;

	private Double count = 0.0;
	private Integer acerto = 0;
	private Integer cDCI = 0;
	private Integer cDII = 0;
	private Integer cADCI = 0;
	private Integer cADII = 0;

	private List<Method> loc;
	private List<Method> locRes;

	/**
	 * Construtor sem parametro
	 */
	public LongMethod() {

	}

	/**
	 * Construtor
	 * @param l_LOC
	 * @param l_CYCLO
	 * @param name
	 * @param tipo
	 */
	public LongMethod(Double l_LOC, Double l_CYCLO, String name, String tipo) {
		super();
		this.l_LOC = l_LOC;
		this.l_CYCLO = l_CYCLO;
		this.name = name;
		this.tipo = tipo;
	}

	/**
	 * get Tipo usado AND ou OR
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * set Tipo AND ou OR
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * get Name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * set Name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get LOC
	 * @return
	 */
	public Double getL_LOC() {
		return l_LOC;
	}

	/**
	 * set LOC
	 * @param l_LOC
	 */
	public void setL_LOC(Double l_LOC) {
		this.l_LOC = l_LOC;
	}

	/**
	 * get CYCLO
	 * @return
	 */
	public Double getL_CYCLO() {
		return l_CYCLO;
	}

	/**
	 * set CYCLO
	 * @param l_CYCLO
	 */
	public void setL_CYCLO(Double l_CYCLO) {
		this.l_CYCLO = l_CYCLO;
	}

	/**
	 * Lista local usado para Teste
	 * @return
	 */
	public List<Method> getLocRes(){
		return locRes;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LongMethod other = (LongMethod) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	/**
	 * Lista de Methods retornado segundo Long Method
	 * @param soft
	 * @return
	 */
	public List<Method> longMethod(List<Method> soft) {
		loc = new ArrayList<Method>();
		locRes = new ArrayList<Method>();
		count = 0.0;
		List<Method> longMethod = new ArrayList<Method>();
		
		/**
		 * AND
		 */
		if (tipo.equals("AND")) {
			for (Method m : soft) {
				loc.add(m);
				if (m.getCYCLO() > l_CYCLO && m.getLOC() > l_LOC) {
					longMethod.add(m);
					locRes.add(m);
					count++;
				}
			}

		}
		
		/**
		 * OR
		 */
		if(tipo.equals("OR")) {
			for (Method m : soft) {
				loc.add(m);
				if (m.getCYCLO() > l_CYCLO || m.getLOC() > l_LOC) {
					longMethod.add(m);
					locRes.add(m);
					count++;
				}
			}
		}
		return longMethod;
	}

	/**
	 * Is IPlasma Long Method
	 * @return
	 */
	public String isIPlasma() {
		cDCI = 0;
		cDII = 0;
		cADCI = 0;
		cADII = 0;
		for (Method test : loc) {
			for (Method m : locRes) {
				if (m.getMethodID().equals(test.getMethodID())) {
					if (test.getIPlasma() == true) {
						cDCI++;
					}
					if (test.getIPlasma() == false) {
						cADII++;
					}
				} else {
					if (test.getIPlasma() == true) {
						cDII++;
					}

					if (test.getIPlasma() == false) {
						cADCI++;
					}
				}
			}
		}

		DecimalFormat df = new DecimalFormat("###,##0.00");
		Double DCI = (cDCI * 100) / count;
		Double DII = (count * 100) / cDII;
		Double ADCI = (count * 100) / cADCI;
		Double ADII = (cADII * 100) / count;
		return "Is IPlasma: "+ "DCI=" + df.format(DCI) + "%, "+"DII=" + df.format(DII) + "% ,"+"ADCI=" + df.format(ADCI) + "% ,"
				+"ADII="+ df.format(ADII) + "%.";
	}

	/**
	 * Is PMD Long Method
	 * @return
	 */
	public String isPMD() {
		cDCI = 0;
		cDII = 0;
		cADCI = 0;
		cADII = 0;
		for (Method test : loc) {
			for (Method m : locRes) {
				if (m.getMethodID().equals(test.getMethodID())) {
					if (test.getPMD() == true) {
						cDCI++;
					}
					if (test.getPMD() == false) {
						cADII++;
					}
				} else {
					if (test.getPMD() == true) {
						cDII++;
					}

					if (test.getPMD() == false) {
						cADCI++;
					}
				}
			}
		}

		DecimalFormat df = new DecimalFormat("###,##0.00");
		Double DCI = (cDCI * 100) / count;
		Double DII = (count * 100) / cDII;
		Double ADCI = (count * 100) / cADCI;
		Double ADII = (cADII * 100) / count;
		return "Is PMD: "+ "DCI=" + df.format(DCI) + "%, "+"DII=" + df.format(DII) + "%, "+"ADCI=" + df.format(ADCI) + "%, "
				+"ADII="+ df.format(ADII) + "%.";
	}
	
	/**
	 * is Long Method
	 * @return
	 */
	public String islongMethod() {
		acerto = 0;
		for(Method m : locRes) {
			for(Method test : loc) {
				if(m.getMethodID().equals(test.getMethodID())) {
					if(test.getIslongmethod() == true) {
						acerto++;
					}
				}
			}
		}
		DecimalFormat df = new DecimalFormat("###,##0.00"); 
		Double perc = (acerto * 100 ) / count;
		return "Is-Long Method: " +  df.format(perc)+"%," + " com acerto de: " + acerto + " dos " + count;
	}

	@Override
	public String toString() {
		return  name + " " + l_LOC + " " + l_CYCLO  + " " + tipo;
	}
	
	

}
