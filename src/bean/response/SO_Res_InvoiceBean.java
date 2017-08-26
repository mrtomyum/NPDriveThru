package bean.response;

public class SO_Res_InvoiceBean {
	private String invoice_no;
	private double cash_amount;
	private double change_amount;
	private double credit_amount;
	private double coupong_amount;
	private double deposit_amount;
	private double remain_amount;
	
	
	public SO_Res_InvoiceBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_InvoiceBean(String invoice_no, double cash_amount,
			double change_amount, double credit_amount, double coupong_amount,
			double deposit_amount, double remain_amount) {
		super();
		this.invoice_no = invoice_no;
		this.cash_amount = cash_amount;
		this.change_amount = change_amount;
		this.credit_amount = credit_amount;
		this.coupong_amount = coupong_amount;
		this.deposit_amount = deposit_amount;
		this.remain_amount = remain_amount;
	}


	public String getInvoice_no() {
		return invoice_no;
	}


	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}


	public double getCash_amount() {
		return cash_amount;
	}


	public void setCash_amount(double cash_amount) {
		this.cash_amount = cash_amount;
	}


	public double getChange_amount() {
		return change_amount;
	}


	public void setChange_amount(double change_amount) {
		this.change_amount = change_amount;
	}


	public double getCredit_amount() {
		return credit_amount;
	}


	public void setCredit_amount(double credit_amount) {
		this.credit_amount = credit_amount;
	}


	public double getCoupong_amount() {
		return coupong_amount;
	}


	public void setCoupong_amount(double coupong_amount) {
		this.coupong_amount = coupong_amount;
	}


	public double getDeposit_amount() {
		return deposit_amount;
	}


	public void setDeposit_amount(double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}


	public double getRemain_amount() {
		return remain_amount;
	}


	public void setRemain_amount(double remain_amount) {
		this.remain_amount = remain_amount;
	}


}
