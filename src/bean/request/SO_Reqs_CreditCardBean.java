package bean.request;

public class SO_Reqs_CreditCardBean {
    private String card_no;
    private String confirm_no;
    private String credit_type;
    private String bank_code;
    private double amount;
    private double charge_amount;
    
	public SO_Reqs_CreditCardBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Reqs_CreditCardBean(String card_no, String confirm_no,
			String credit_type, String bank_code, double amount,
			double charge_amount) {
		super();
		this.card_no = card_no;
		this.confirm_no = confirm_no;
		this.credit_type = credit_type;
		this.bank_code = bank_code;
		this.amount = amount;
		this.charge_amount = charge_amount;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getConfirm_no() {
		return confirm_no;
	}

	public void setConfirm_no(String confirm_no) {
		this.confirm_no = confirm_no;
	}

	public String getCredit_type() {
		return credit_type;
	}

	public void setCredit_type(String credit_type) {
		this.credit_type = credit_type;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getCharge_amount() {
		return charge_amount;
	}

	public void setCharge_amount(double charge_amount) {
		this.charge_amount = charge_amount;
	}
    
    
}
