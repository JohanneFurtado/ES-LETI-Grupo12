package detector.rulles;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import software.Method;

/**
 * 
 * @author gatun
 *
 */
public class FeatureEnvy {

	/**
	 * 
	 * Atributos
	 * 
	 */
	private Double l_ATFD;
	private Double l_LAA;
	private String nome;
	private String tipo;
	
	private Double count = 0.0;
	private Integer acerto = 0;
	
	private Integer cDCI = 0;
	private Integer cDII = 0;
	private Integer cADCI = 0;
	private Integer cADII = 0;
	
	/**
	 * Lista dos Methods locais
	 */
	private List<Method> loc;
	private List<Method> locRes;
	

	/**
	 * Construtor 
	 * sem parametro
	 */
	public FeatureEnvy() {

	}

	/**
	 * Construtor
	 * @param l_ATFD
	 * @param l_LAA
	 * @param nome
	 * @param tipo
	 */
	public FeatureEnvy(Double l_ATFD, Double l_LAA, String nome, String tipo) {
		super();
		this.l_ATFD = l_ATFD;
		this.l_LAA = l_LAA;
		this.nome = nome;
		this.tipo = tipo;
	}
	
	

	/**
	 * get ATFD
	 * @return
	 */
	public Double getL_ATFD() {
		return l_ATFD;
	}

	/**
	 * set ATFD
	 * @param l_ATFD
	 */
	public void setL_ATFD(Double l_ATFD) {
		this.l_ATFD = l_ATFD;
	}

	/**
	 * get LAA
	 * @return
	 */
	public Double getL_LAA() {
		return l_LAA;
	}

	/**
	 * set LAA
	 * @param l_LAA
	 */
	public void setL_LAA(Double l_LAA) {
		this.l_LAA = l_LAA;
	}

	/**
	 * get Name
	 * @return
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * set Name
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Tipo de operador AND ou OR
	 * 
	 *get tipo
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * set tipo
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		FeatureEnvy other = (FeatureEnvy) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * 
	 * Lista de Methods resultantes
	 * @param aux
	 * @return
	 */
	public List<Method> feature_envy(List<Method> aux) {
		loc = new ArrayList<Method>();
		locRes= new ArrayList<Method>();
		count = 0.0;
		List<Method> feature_envy = new ArrayList<Method>();
		
		/**
		 * AND
		 */
		if (tipo.equals("AND")) {
			for (Method m : aux) {
				loc.add(m);
				if (m.getATFD() > l_ATFD && m.getLAA() > l_LAA) {
					feature_envy.add(m);
					locRes.add(m);
					count++;
				}
			}
			
		} 
		
		/**
		 * OR
		 */
		if(tipo.equals("OR")){
			for (Method m : aux) {
				loc.add(m);
				if (m.getATFD() > l_ATFD || m.getLAA() > l_LAA) {
					feature_envy.add(m);
					locRes.add(m);
					count++;
				}
			}
		}
		return feature_envy;
	}


	
	/**
	 * is Feature Envy
	 * @return
	 */
	public String isfeatureEnvyDet() {
		acerto = 0;
		for(Method m : locRes) {
			for(Method test : loc) {
				if(m.getMethodID().equals(test.getMethodID())) {
					if(test.getIsfeatureenvy() == true) {
						acerto++;
					}
				}
			}
		}
		DecimalFormat df = new DecimalFormat("###,##0.00"); 
		Double perc = (acerto * 100 ) / count;
		return "Is-FeatureEnvy: " +  df.format(perc)+"%," + " com acerto de: " + acerto + " dos " + count + " considerados.";
	}
	
	/**
	 * Is IPlasma de Feature Envy
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
	 * Is PMD de Feature Envy
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
	 * Lista local usado para Test
	 * @return
	 */
	public Object getLocRes() {
		// TODO Auto-generated method stub
		return locRes;
	}
	
	@Override
	public String toString() {
		return nome + " " + l_ATFD + " " + l_LAA  + " " + tipo;
	}
}
