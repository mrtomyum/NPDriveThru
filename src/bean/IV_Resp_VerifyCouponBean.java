package bean;

import bean.response.CT_Resp_ResponseBean;

public class IV_Resp_VerifyCouponBean {
	private CT_Resp_ResponseBean response;
	private IV_Resp_CouponAmount coupon;
	
	
	public IV_Resp_VerifyCouponBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_VerifyCouponBean(CT_Resp_ResponseBean response, IV_Resp_CouponAmount coupon) {
		super();
		this.response = response;
		this.coupon = coupon;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public IV_Resp_CouponAmount getCoupon() {
		return coupon;
	}


	public void setCoupon(IV_Resp_CouponAmount coupon) {
		this.coupon = coupon;
	}
	
	
	
}
