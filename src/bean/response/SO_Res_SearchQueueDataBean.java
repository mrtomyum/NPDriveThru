package bean.response;

import java.util.List;

public class SO_Res_SearchQueueDataBean {
	boolean success;
	boolean error;
	String message;
	List<SO_Res_ListSearchQueueDataBean> order;
	
	
	public SO_Res_SearchQueueDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_SearchQueueDataBean(boolean success, boolean error,
			String message, List<SO_Res_ListSearchQueueDataBean> order) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.order = order;
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


	public List<SO_Res_ListSearchQueueDataBean> getOrder() {
		return order;
	}


	public void setOrder(List<SO_Res_ListSearchQueueDataBean> order) {
		this.order = order;
	}


}
