package bean.request;

public class SO_Reqs_CouponBean {
    private String coupon_code;
    private double amount;
    
	public SO_Reqs_CouponBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Reqs_CouponBean(String coupon_code, double amount) {
		super();
		this.coupon_code = coupon_code;
		this.amount = amount;
	}

	public String getCoupon_code() {
		return coupon_code;
	}

	public void setCoupon_code(String coupon_code) {
		this.coupon_code = coupon_code;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
    
    
}
