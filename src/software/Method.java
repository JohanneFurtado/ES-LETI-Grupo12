package software;

public class Method {

	private Double methodID;
	private String packageName;
	private String className;
	private String methodName;
	private Double n_LOC;
	private Double n_CYCLO;
	private Double n_ATFD;
	private Double n_LAA;
	private Boolean is_long_method;
	private Boolean iPlasma;
	private Boolean PMD;
	private Boolean is_feature_envy;
	
	
//	private List<Method> listSoft = new ArrayList<Method>();
	
	
	public Method() {
		
	}
	
	
	public Method(Double methodID, String packageName, String className, String methodName, Double n_LOC,
			Double n_CYCLO,Double n_ATFD, Double n_LAA, Boolean is_long_method, Boolean iPlasma, Boolean pMD,
			Boolean is_feature_envy) {
		super();
		this.methodID = methodID;
		this.packageName = packageName;
		this.className = className;
		this.methodName = methodName;
		this.n_LOC = n_LOC;
		this.n_CYCLO = n_CYCLO;
		this.n_ATFD = n_ATFD;
		this.n_LAA = n_LAA;
		this.is_long_method = is_long_method;
		this.iPlasma = iPlasma;
		PMD = pMD;
		this.is_feature_envy = is_feature_envy;
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


	public Double getN_LOC() {
		return n_LOC;
	}


	public void setN_LOC(Double n_LOC) {
		this.n_LOC = n_LOC;
	}


	public Double getN_CYCLO() {
		return n_CYCLO;
	}


	public void setN_CYCLO(Double n_CYCLO) {
		this.n_CYCLO = n_CYCLO;
	}


	public Double getN_ATFD() {
		return n_ATFD;
	}


	public void setN_ATFD(Double n_ATFD) {
		this.n_ATFD = n_ATFD;
	}


	public Double getN_LAA() {
		return n_LAA;
	}


	public void setN_LAA(Double n_LAA) {
		this.n_LAA = n_LAA;
	}


	public Boolean getIs_long_method() {
		return is_long_method;
	}


	public void setIs_long_method(Boolean is_long_method) {
		this.is_long_method = is_long_method;
	}


	public Boolean getiPlasma() {
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


	public Boolean getIs_feature_envy() {
		return is_feature_envy;
	}


	public void setIs_feature_envy(Boolean is_feature_envy) {
		this.is_feature_envy = is_feature_envy;
	}


	@Override
	public String toString() {
		return "methodID=" + methodID + ", " + packageName + ", " + className
				+ ", " + methodName + ", LOC=" + n_LOC + ", CYCLO=" + n_CYCLO + ", ATFD=" + n_ATFD
				+ ", LAA=" + n_LAA + ", is_long_method=" + is_long_method + ", iPlasma=" + iPlasma + ", PMD=" + PMD
				+ ", is_feature_envy=" + is_feature_envy;
	}
	
	
	
	
	
	
}