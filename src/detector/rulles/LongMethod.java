package detector.rulles;

import java.util.ArrayList;
import java.util.List;

import software.Method;

public class LongMethod {

	private Double l_LOC;
	private Double l_CYCLO;
	private String name;
	
	public LongMethod() {
		
	}
	
	
	

	public LongMethod(Double l_LOC, Double l_CYCLO, String name) {
		super();
		this.l_LOC = l_LOC;
		this.l_CYCLO = l_CYCLO;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getL_LOC() {
		return l_LOC;
	}

	public void setL_LOC(Double l_LOC) {
		this.l_LOC = l_LOC;
	}

	public Double getL_CYCLO() {
		return l_CYCLO;
	}

	public void setL_CYCLO(Double l_CYCLO) {
		this.l_CYCLO = l_CYCLO;
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




	public List<Method> longMethod(List<Method> soft) {

		int count = 0;

		List<Method> longMethod = new ArrayList<Method>();
		for (Method m : soft) {
			if (m.getN_LOC() > l_LOC && m.getN_CYCLO() > l_CYCLO) {
				longMethod.add(m);
				count++;
			}
		}
		System.out.println(count);
		return longMethod;
	}
	
	@Override
	public String toString() {
		return "LongMethod: name=" + name;
	}

}
