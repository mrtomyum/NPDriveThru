package bean.response;

public class SO_Res_ListARDepositBean {
	private String deposit_id;
	private double deposit_amount;
	private double deposit_remain;
	
	public SO_Res_ListARDepositBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ListARDepositBean(String deposit_id, double deposit_amount,
			double deposit_remain) {
		super();
		this.deposit_id = deposit_id;
		this.deposit_amount = deposit_amount;
		this.deposit_remain = deposit_remain;
	}

	public String getDeposit_id() {
		return deposit_id;
	}

	public void setDeposit_id(String deposit_id) {
		this.deposit_id = deposit_id;
	}

	public double getDeposit_amount() {
		return deposit_amount;
	}

	public void setDeposit_amount(double deposit_amount) {
		this.deposit_amount = deposit_amount;
	}

	public double getDeposit_remain() {
		return deposit_remain;
	}

	public void setDeposit_remain(double deposit_remain) {
		this.deposit_remain = deposit_remain;
	}

}
