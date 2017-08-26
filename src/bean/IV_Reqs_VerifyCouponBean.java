package bean;

public class IV_Reqs_VerifyCouponBean {
	private String access_token;
	private String couponCode;
	
	
	public IV_Reqs_VerifyCouponBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Reqs_VerifyCouponBean(String access_token, String couponCode) {
		super();
		this.access_token = access_token;
		this.couponCode = couponCode;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getCouponCode() {
		return couponCode;
	}


	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	
	
}
