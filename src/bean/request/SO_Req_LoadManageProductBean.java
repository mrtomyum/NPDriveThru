package bean.request;

import java.util.List;

public class SO_Req_LoadManageProductBean {
	private String access_token;
	private String otp_password ;
	private int queue_id;
	private List<SO_Req_ListItemBean> item;
	
	
	public SO_Req_LoadManageProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_LoadManageProductBean(String access_token, String otp_password, int queue_id,
			List<SO_Req_ListItemBean> item) {
		super();
		this.access_token = access_token;
		this.otp_password = otp_password;
		this.queue_id = queue_id;
		this.item = item;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getOtp_password() {
		return otp_password;
	}


	public void setOtp_password(String otp_password) {
		this.otp_password = otp_password;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public List<SO_Req_ListItemBean> getItem() {
		return item;
	}


	public void setItem(List<SO_Req_ListItemBean> item) {
		this.item = item;
	}

	
}
