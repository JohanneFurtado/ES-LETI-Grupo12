package detector.rulles;

import java.util.ArrayList;
import java.util.List;

import software.Method;

public class FeatureEnvy {
	
	private Double l_ATFD;
	private Double l_LAA;
	private String nome;
	
	public FeatureEnvy() {
		
	}
	


	public FeatureEnvy(Double l_ATFD, Double l_LAA, String nome) {
		super();
		this.l_ATFD = l_ATFD;
		this.l_LAA = l_LAA;
		this.nome = nome;
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

		int count = 0;

		List<Method> feature_envy = new ArrayList<Method>();
		for (Method m : aux) {
			if (m.getN_LOC() > l_ATFD && m.getN_CYCLO() > l_LAA) {
				feature_envy.add(m);
				count++;
			}
		}
		System.out.println(count);
		return feature_envy;
	}

}

