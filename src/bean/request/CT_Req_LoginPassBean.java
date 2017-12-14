package bean.request;

public class CT_Req_LoginPassBean {
	private String user_code;
	private String password;
	private int branch_id;
	private String server_name;
	private String database_name;
	
	public CT_Req_LoginPassBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Req_LoginPassBean(String user_code, String password, int branch_id, String server_name,
			String database_name) {
		super();
		this.user_code = user_code;
		this.password = password;
		this.branch_id = branch_id;
		this.server_name = server_name;
		this.database_name = database_name;
	}

	public String getUser_code() {
		return user_code;
	}

	public void setUser_code(String user_code) {
		this.user_code = user_code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getBranch_id() {
		return branch_id;
	}

	public void setBranch_id(int branch_id) {
		this.branch_id = branch_id;
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
