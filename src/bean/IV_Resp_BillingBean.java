package bean;

import bean.response.CT_Resp_ResponseBean;

public class IV_Resp_BillingBean {
	CT_Resp_ResponseBean response;
	private String invoiceNo;
	private double totalAmount;
	private double cashAmount;
	private double changeAmount;
	private double creditAmount;
	private double coupongAmount;
	
	
	public IV_Resp_BillingBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Resp_BillingBean(CT_Resp_ResponseBean response, String invoiceNo, double totalAmount, double cashAmount, double changeAmount, double creditAmount, double coupongAmount) {
		super();
		this.response = response;
		this.invoiceNo = invoiceNo;
		this.totalAmount = totalAmount;
		this.cashAmount = cashAmount;
		this.changeAmount= changeAmount;
		this.creditAmount= creditAmount;
		this.coupongAmount = coupongAmount;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	public double getTotalAmount(){
		return totalAmount;
	}
	
	public void setTotalAmount(double totalAmount){
		this.totalAmount = totalAmount;
	}
	
	public double getCashAmount(){
		return cashAmount;
	}
	
	public void setCashAmount(double cashAmount){
		this.cashAmount = cashAmount;
	}
	
	public double getChangeAmount(){
		return changeAmount;
	}
	
	public void setChangeAmount(double changeAmount){
		this.changeAmount = changeAmount;
	}
	
	public double getCreditAmount(){
		return creditAmount;
	}
	
	public void setCreditAmount(double creditAmount){
		this.creditAmount = creditAmount;
	}


	public double getCoupongAmount() {
		return coupongAmount;
	}


	public void setCoupongAmount(double coupongAmount) {
		this.coupongAmount = coupongAmount;
	}
	

}
