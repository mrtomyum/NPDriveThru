package bean;

public class IV_Resp_ValidateCreditCardBean {
	private String process;
	private String processDesc;
	private boolean isSuccess;
	private int isUsed;
	
	
	public IV_Resp_ValidateCreditCardBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_ValidateCreditCardBean(String process, String processDesc,
			boolean isSuccess, int isUsed) {
		super();
		this.process = process;
		this.processDesc = processDesc;
		this.isSuccess = isSuccess;
		this.isUsed = isUsed;
	}


	public String getProcess() {
		return process;
	}


	public void setProcess(String process) {
		this.process = process;
	}


	public String getProcessDesc() {
		return processDesc;
	}


	public void setProcessDesc(String processDesc) {
		this.processDesc = processDesc;
	}


	public boolean isSuccess() {
		return isSuccess;
	}


	public void setIsSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	public int getIsUsed() {
		return isUsed;
	}


	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}
	
	
}
