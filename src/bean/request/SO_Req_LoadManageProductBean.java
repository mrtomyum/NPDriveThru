package bean.request;

import java.util.List;

public class SO_Req_LoadManageProductBean {
	private String otp_password ;
	private List<SO_Req_ListItemBean> item;
	
	
	public SO_Req_LoadManageProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_LoadManageProductBean(String otp_password, List<SO_Req_ListItemBean> item) {
		super();
		this.otp_password = otp_password;
		this.item = item;
	}

	public String getOtp_password() {
		return otp_password;
	}
	
	public void setOtp_password(String otp_password) {
		this.otp_password = otp_password;
	}
	
	public List<SO_Req_ListItemBean> getItem(){
		return item;
	}
	
	public void setItem(List<SO_Req_ListItemBean> item) {
		this.item = item;
	}
}
