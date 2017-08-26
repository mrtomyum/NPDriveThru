package bean.request;

public class DT_User_LoginBranchBean {
	private String serverName;
	private String dbName;
	
	
	public DT_User_LoginBranchBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public DT_User_LoginBranchBean(String serverName, String dbName) {
		super();
		this.serverName = serverName;
		this.dbName = dbName;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getDbName() {
		return dbName;
	}


	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	
	
}
