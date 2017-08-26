package bean;

public class LoginBean {
	
	private String employeeCode;
	private int branchId;
	private String employeeName;
	private String server_name;
	private String database_name;
	
	public LoginBean() {}

	public LoginBean(String employeeCode, int branchId, String employeeName,
			String server_name, String database_name) {
		super();
		this.employeeCode = employeeCode;
		this.branchId = branchId;
		this.employeeName = employeeName;
		this.server_name = server_name;
		this.database_name = database_name;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}

	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getServer_name() {
		return server_name;
	}

	public void setServer_name(String server_name) {
		this.server_name = server_name;
	}

	public String getDatabase_name() {
		return database_name;
	}

	public void setDatabase_name(String database_name) {
		this.database_name = database_name;
	}

	

}
