package bean;

public class User_Resp_CheckDataAccessTokenBean {
	private int exist;
	private String branchCode;
	private String zoneid;
	private String machineNo;
	private String machineCode;
	private String whCode;
	private String shelfCode;
	private String serverName;
	private String dataBaseName;
	
	
	public User_Resp_CheckDataAccessTokenBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public User_Resp_CheckDataAccessTokenBean(int exist, String branchCode,
			String zoneid, String machineNo, String machineCode, String whCode,
			String shelfCode, String serverName, String dataBaseName) {
		super();
		this.exist = exist;
		this.branchCode = branchCode;
		this.zoneid = zoneid;
		this.machineNo = machineNo;
		this.machineCode = machineCode;
		this.whCode = whCode;
		this.shelfCode = shelfCode;
		this.serverName = serverName;
		this.dataBaseName = dataBaseName;
	}


	public int getExist() {
		return exist;
	}


	public void setExist(int exist) {
		this.exist = exist;
	}


	public String getBranchCode() {
		return branchCode;
	}


	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}


	public String getZoneid() {
		return zoneid;
	}


	public void setZoneid(String zoneid) {
		this.zoneid = zoneid;
	}


	public String getMachineNo() {
		return machineNo;
	}


	public void setMachineNo(String machineNo) {
		this.machineNo = machineNo;
	}


	public String getMachineCode() {
		return machineCode;
	}


	public void setMachineCode(String machineCode) {
		this.machineCode = machineCode;
	}


	public String getWhCode() {
		return whCode;
	}


	public void setWhCode(String whCode) {
		this.whCode = whCode;
	}


	public String getShelfCode() {
		return shelfCode;
	}


	public void setShelfCode(String shelfCode) {
		this.shelfCode = shelfCode;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getDataBaseName() {
		return dataBaseName;
	}


	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	
}
