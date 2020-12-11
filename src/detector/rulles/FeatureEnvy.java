package detector.rulles;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import software.Method;

public class FeatureEnvy {

	private Double l_ATFD;
	private Double l_LAA;
	private String nome;
	private String tipo;
	
	private Double count = 0.0;
	private Integer acerto = 0;
	
	private List<Method> loc;
	private List<Method> locRes;

	public FeatureEnvy() {

	}

	public FeatureEnvy(Double l_ATFD, Double l_LAA, String nome, String tipo) {
		super();
		this.l_ATFD = l_ATFD;
		this.l_LAA = l_LAA;
		this.nome = nome;
		this.tipo = tipo;
	}

	public Double getL_ATFD() {
		return l_ATFD;
	}

	public void setL_ATFD(Double l_ATFD) {
		this.l_ATFD = l_ATFD;
	}

	public Double getL_LAA() {
		return l_LAA;
	}

	public void setL_LAA(Double l_LAA) {
		this.l_LAA = l_LAA;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "FeatureEnvy [l_ATFD=" + l_ATFD + ", l_LAA=" + l_LAA + ", nome=" + nome + "]";
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

	public List<Method> feature_envy(List<Method> aux) {
		loc = new ArrayList<Method>();
		locRes= new ArrayList<Method>();
		count = 0.0;
		List<Method> feature_envy = new ArrayList<Method>();
		if (tipo.equals("AND")) {
			for (Method m : aux) {
				loc.add(m);
				if (m.getATFD() > l_ATFD && m.getLAA() > l_LAA) {
					feature_envy.add(m);
					locRes.add(m);
					count++;
				}
			}
			
		} else {
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


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	
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
		return "Is-FeatureEnvy: " +  df.format(perc)+"%," + " com acerto de: " + acerto + " dos " + count;
	}
}
