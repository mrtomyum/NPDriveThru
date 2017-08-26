package bean;

public class IV_Reqs_CouponBean {
    private String couponCode;
    private double amount;
    
    
	public IV_Reqs_CouponBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Reqs_CouponBean(String couponCode, double amount) {
		super();
		this.couponCode = couponCode;
		this.amount = amount;
	}


	public String getCouponCode() {
		return couponCode;
	}


	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}
    
    
}
