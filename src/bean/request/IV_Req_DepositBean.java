package bean.request;

public class IV_Req_DepositBean {
	private String deposit_id;
	private Double amount;
	
	
	public IV_Req_DepositBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Req_DepositBean(String deposit_id, Double amount) {
		super();
		this.deposit_id = deposit_id;
		this.amount = amount;
	}


	public String getDeposit_id() {
		return deposit_id;
	}


	public void setDeposit_id(String deposit_id) {
		this.deposit_id = deposit_id;
	}


	public Double getAmount() {
		return amount;
	}


	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
