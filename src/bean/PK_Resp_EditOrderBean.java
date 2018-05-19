package bean;

import bean.response.CT_Resp_ResponseBean;

public class PK_Resp_EditOrderBean {
	boolean success;
	boolean error;
	String message;
	
	public PK_Resp_EditOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PK_Resp_EditOrderBean(boolean success, boolean error, String message) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
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
	
	
//	
//	private CT_Resp_ResponseBean response;
//
//	public PK_Resp_EditOrderBean() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	public PK_Resp_EditOrderBean(CT_Resp_ResponseBean response) {
//		super();
//		this.response = response;
//	}
//
//	public CT_Resp_ResponseBean getResponse() {
//		return response;
//	}
//
//	public void setResponse(CT_Resp_ResponseBean response) {
//		this.response = response;
//	}
	
	
}
