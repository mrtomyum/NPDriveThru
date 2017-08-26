package bean.request;

public class CT_Req_ServerDataBaseBean {
	private String serverName;
	private String databaseName;
	
	
	public CT_Req_ServerDataBaseBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CT_Req_ServerDataBaseBean(String serverName, String databaseName) {
		super();
		this.serverName = serverName;
		this.databaseName = databaseName;
	}


	public String getServerName() {
		return serverName;
	}


	public void setServerName(String serverName) {
		this.serverName = serverName;
	}


	public String getDatabaseName() {
		return databaseName;
	}


	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	
	
}
