package bean.response;

public class CT_Res_ResponseBean {
	private boolean success;
	private boolean error;
	
	public CT_Res_ResponseBean() {

	}

	public CT_Res_ResponseBean(boolean success,boolean error) {
		this.success = success;
		this.error = error;
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


	
	

}
