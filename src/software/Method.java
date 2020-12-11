package software;

public class Method {

	private Double methodID;
	private String packageName;
	private String className;
	private String methodName;
	private Double LOC;
	private Double CYCLO;
	private Double ATFD;
	private Double LAA;
	private Boolean islongmethod;
	private Boolean iPlasma;
	private Boolean PMD;
	private Boolean isfeatureenvy;
	
	
	
	public Method() {
		
	}
	
	
	public Method(Double methodID, String packageName, String className, String methodName, Double n_LOC,
			Double n_CYCLO,Double n_ATFD, Double n_LAA, Boolean islongmethod, Boolean iPlasma, Boolean pMD,
			Boolean isfeatureenvy) {
		super();
		this.methodID = methodID;
		this.packageName = packageName;
		this.className = className;
		this.methodName = methodName;
		this.LOC = n_LOC;
		this.CYCLO = n_CYCLO;
		this.ATFD = n_ATFD;
		this.LAA = n_LAA;
		this.islongmethod = islongmethod;
		this.iPlasma = iPlasma;
		this.PMD = pMD;
		this.isfeatureenvy = isfeatureenvy;
	}


	public Double getMethodID() {
		return methodID;
	}


	public void setMethodID(Double methodID) {
		this.methodID = methodID;
	}


	public String getPackageName() {
		return packageName;
	}


	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}


	public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
	}


	public String getMethodName() {
		return methodName;
	}


	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}


	public Double getLOC() {
		return LOC;
	}


	public void setN_LOC(Double n_LOC) {
		this.LOC = n_LOC;
	}


	public Double getCYCLO() {
		return CYCLO;
	}


	public void setN_CYCLO(Double n_CYCLO) {
		this.CYCLO = n_CYCLO;
	}


	public Double getATFD() {
		return ATFD;
	}


	public void setN_ATFD(Double n_ATFD) {
		this.ATFD = n_ATFD;
	}


	public Double getLAA() {
		return LAA;
	}


	public void setN_LAA(Double n_LAA) {
		this.LAA = n_LAA;
	}


	public Boolean getIslongmethod() {
		return islongmethod;
	}


	public void setIs_long_method(Boolean islongmethod) {
		this.islongmethod = islongmethod;
	}


	public Boolean getIPlasma() {
		return iPlasma;
	}


	public void setiPlasma(Boolean iPlasma) {
		this.iPlasma = iPlasma;
	}


	public Boolean getPMD() {
		return PMD;
	}


	public void setPMD(Boolean pMD) {
		PMD = pMD;
	}


	public Boolean getIsfeatureenvy() {
		return isfeatureenvy;
	}


	public void setIs_feature_envy(Boolean isfeatureenvy) {
		this.isfeatureenvy = isfeatureenvy;
	}
	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((methodID == null) ? 0 : methodID.hashCode());
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
		Method other = (Method) obj;
		if (methodID == null) {
			if (other.methodID != null)
				return false;
		} else if (!methodID.equals(other.methodID))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "methodID=" + methodID + ", " + packageName + ", " + className
				+ ", " + methodName + ", LOC=" + LOC + ", CYCLO=" + CYCLO + ", ATFD=" + ATFD
				+ ", LAA=" + LAA + ", islongmethod=" + islongmethod + ", iPlasma=" + iPlasma + ", PMD=" + PMD
				+ ", isfeatureenvy=" + isfeatureenvy;
	}
	
	
	
	
	
	
}