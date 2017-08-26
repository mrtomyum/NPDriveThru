package bean;

public class IV_Reqs_CreditCardBean {
    private String cardNo;
    private String confirmNo;
    private String creditType;
    private String bankCode;
    private double amount;
    private double chargeAmount;
    
    
	public IV_Reqs_CreditCardBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Reqs_CreditCardBean(String cardNo, String confirmNo,
			String creditType, String bankCode, double amount,
			double chargeAmount) {
		super();
		this.cardNo = cardNo;
		this.confirmNo = confirmNo;
		this.creditType = creditType;
		this.bankCode = bankCode;
		this.amount = amount;
		this.chargeAmount = chargeAmount;
	}


	public String getCardNo() {
		return cardNo;
	}


	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}


	public String getConfirmNo() {
		return confirmNo;
	}


	public void setConfirmNo(String confirmNo) {
		this.confirmNo = confirmNo;
	}


	public String getCreditType() {
		return creditType;
	}


	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}


	public String getBankCode() {
		return bankCode;
	}


	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public double getChargeAmount() {
		return chargeAmount;
	}


	public void setChargeAmount(double chargeAmount) {
		this.chargeAmount = chargeAmount;
	}
    
    
}
