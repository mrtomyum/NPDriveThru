package bean.request;

public class SO_Req_ChangeStatusBean {
	private String access_token;
	private int queue_id;
	private String status_for_saleorder_current;
	private int is_loaded;
	private String password;
	private String cancel_remark;
	
	
	public SO_Req_ChangeStatusBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_ChangeStatusBean(String access_token, int queue_id, String status_for_saleorder_current,
			int is_loaded, String password, String cancel_remark) {
		super();
		this.access_token = access_token;
		this.queue_id = queue_id;
		this.status_for_saleorder_current = status_for_saleorder_current;
		this.is_loaded = is_loaded;
		this.password = password;
		this.cancel_remark = cancel_remark;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public String getStatus_for_saleorder_current() {
		return status_for_saleorder_current;
	}


	public void setStatus_for_saleorder_current(String status_for_saleorder_current) {
		this.status_for_saleorder_current = status_for_saleorder_current;
	}


	public int getIs_loaded() {
		return is_loaded;
	}


	public void setIs_loaded(int is_loaded) {
		this.is_loaded = is_loaded;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getCancel_remark() {
		return cancel_remark;
	}


	public void setCancel_remark(String cancel_remark) {
		this.cancel_remark = cancel_remark;
	}

	
}
