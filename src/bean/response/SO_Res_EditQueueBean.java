package bean.response;

public class SO_Res_EditQueueBean {
	boolean success;
	boolean error;
	String message;
	private SO_Res_ListSearchQueueDataBean queue;

	
	public SO_Res_EditQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_EditQueueBean(boolean success, boolean error, String message,
			SO_Res_ListSearchQueueDataBean queue) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.queue = queue;
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


	public SO_Res_ListSearchQueueDataBean getQueue() {
		return queue;
	}


	public void setQueue(SO_Res_ListSearchQueueDataBean queue) {
		this.queue = queue;
	}


}
