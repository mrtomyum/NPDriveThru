package bean.request;

import java.util.List;

import bean.IV_Reqs_CouponBean;
import bean.IV_Reqs_CreditCardBean;

public class SO_Req_BillingBean {
    private String access_token;
    private String ar_code;
    private int confirm;
    private int queue_id;
    private double cash;
    private String scg_id;
    private List<SO_Reqs_CreditCardBean> credit_card;
    private List<SO_Reqs_CouponBean> coupon_code;
    private List<IV_Req_DepositBean> deposit_amount;
    //private double debt_amount;
    
    
	public SO_Req_BillingBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Req_BillingBean(String access_token, String ar_code, int confirm, int queue_id, double cash,
			String scg_id, List<SO_Reqs_CreditCardBean> credit_card, List<SO_Reqs_CouponBean> coupon_code,
			List<IV_Req_DepositBean> deposit_amount) {
		super();
		this.access_token = access_token;
		this.ar_code = ar_code;
		this.confirm = confirm;
		this.queue_id = queue_id;
		this.cash = cash;
		this.scg_id = scg_id;
		this.credit_card = credit_card;
		this.coupon_code = coupon_code;
		this.deposit_amount = deposit_amount;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getAr_code() {
		return ar_code;
	}


	public void setAr_code(String ar_code) {
		this.ar_code = ar_code;
	}


	public int getConfirm() {
		return confirm;
	}


	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public double getCash() {
		return cash;
	}


	public void setCash(double cash) {
		this.cash = cash;
	}


	public String getScg_id() {
		return scg_id;
	}


	public void setScg_id(String scg_id) {
		this.scg_id = scg_id;
	}


	public List<SO_Reqs_CreditCardBean> getCredit_card() {
		return credit_card;
	}


	public void setCredit_card(List<SO_Reqs_CreditCardBean> credit_card) {
		this.credit_card = credit_card;
	}


	public List<SO_Reqs_CouponBean> getCoupon_code() {
		return coupon_code;
	}


	public void setCoupon_code(List<SO_Reqs_CouponBean> coupon_code) {
		this.coupon_code = coupon_code;
	}


	public List<IV_Req_DepositBean> getDeposit_amount() {
		return deposit_amount;
	}


	public void setDeposit_amount(List<IV_Req_DepositBean> deposit_amount) {
		this.deposit_amount = deposit_amount;
	}


	


//	public double getDebt_amount() {
//		return debt_amount;
//	}
//
//
//	public void setDebt_amount(double debt_amount) {
//		this.debt_amount = debt_amount;
//	}
    
    
}
