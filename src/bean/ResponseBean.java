package bean;

public class ResponseBean {
	
	
	private String process;
	private String processDesc;
	private boolean isSuccess;
	

	public ResponseBean(){}
	public ResponseBean(String process, boolean isSuccess, String processDesc) {
		this.process = process;
		this.isSuccess = isSuccess;
		this.processDesc = processDesc;
		
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getProcessDesc() {
		return processDesc;
	}

	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}
	
	

}
