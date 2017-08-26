package bean.response;

import java.util.List;


public class CT_Res_LoginBean {
	//private CT_Resp_ResponseBean resp;
	boolean success;
	boolean error;
	String message;
	private String access_token;
	private CT_Res_UserBean user;
	
	public CT_Res_LoginBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Res_LoginBean(boolean success, boolean error, String message,
			String access_token, CT_Res_UserBean user) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.access_token = access_token;
		this.user = user;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public CT_Res_UserBean getUser() {
		return user;
	}

	public void setUser(CT_Res_UserBean user) {
		this.user = user;
	}

	
}
