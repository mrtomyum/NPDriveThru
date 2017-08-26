package bean;

public class IV_Resp_BankBean {
	private String bankCode;
	private String bankName;
	
	
	public IV_Resp_BankBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_BankBean(String bankCode, String bankName) {
		super();
		this.bankCode = bankCode;
		this.bankName = bankName;
	}
	
	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
