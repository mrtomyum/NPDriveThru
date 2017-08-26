package bean.response;

public class SO_Res_Response {
	boolean success;
	boolean error;
	String message;
	
	
	public SO_Res_Response() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_Response(boolean success, boolean error, String message) {
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
	
	
}
