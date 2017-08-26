package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class IV_Resp_PrintSlipBean {
	CT_Resp_ResponseBean response;
	private String docNo;
	private String docDate;
	private String companyName;
	private String taxId;
	private String posId;
	private String cashier;
	private String saleCode;
	private String billTime;
	private double totalAmount;
	private int	tax;
	private double taxAmount;
	private double cashAmount;
	private double creditAmount;
	private double change;
	private String greeting1;
	private String greeting2;
	private String greeting3;
	private String greeting4;
	private String greeting5;
	private String remark;
	private int point;
	private String promotionDesc1;
	private String promotionDesc2;
	private String promotionDesc3;
	private String promotionDesc4;
	private String promotionDesc5;
	List<IV_Resp_PrintSlipSubBean> item;
	
	public IV_Resp_PrintSlipBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IV_Resp_PrintSlipBean(CT_Resp_ResponseBean response, String docNo,
			String docDate, String companyName, String taxId, String posId,
			String cashier, String saleCode, String billTime,
			double totalAmount, int tax, double taxAmount, double cashAmount,
			double creditAmount, double change, String greeting1,
			String greeting2, String greeting3, String greeting4,
			String greeting5, String remark, int point, String promotionDesc1,
			String promotionDesc2, String promotionDesc3,
			String promotionDesc4, String promotionDesc5,
			List<IV_Resp_PrintSlipSubBean> item) {
		super();
		this.response = response;
		this.docNo = docNo;
		this.docDate = docDate;
		this.companyName = companyName;
		this.taxId = taxId;
		this.posId = posId;
		this.cashier = cashier;
		this.saleCode = saleCode;
		this.billTime = billTime;
		this.totalAmount = totalAmount;
		this.tax = tax;
		this.taxAmount = taxAmount;
		this.cashAmount = cashAmount;
		this.creditAmount = creditAmount;
		this.change = change;
		this.greeting1 = greeting1;
		this.greeting2 = greeting2;
		this.greeting3 = greeting3;
		this.greeting4 = greeting4;
		this.greeting5 = greeting5;
		this.remark = remark;
		this.point = point;
		this.promotionDesc1 = promotionDesc1;
		this.promotionDesc2 = promotionDesc2;
		this.promotionDesc3 = promotionDesc3;
		this.promotionDesc4 = promotionDesc4;
		this.promotionDesc5 = promotionDesc5;
		this.item = item;
	}

	public CT_Resp_ResponseBean getResponse() {
		return response;
	}

	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getDocDate() {
		return docDate;
	}

	public void setDocDate(String docDate) {
		this.docDate = docDate;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTaxId() {
		return taxId;
	}

	public void setTaxId(String taxId) {
		this.taxId = taxId;
	}

	public String getPosId() {
		return posId;
	}

	public void setPosId(String posId) {
		this.posId = posId;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getBillTime() {
		return billTime;
	}

	public void setBillTime(String billTime) {
		this.billTime = billTime;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getCashAmount() {
		return cashAmount;
	}

	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}

	public double getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public String getGreeting1() {
		return greeting1;
	}

	public void setGreeting1(String greeting1) {
		this.greeting1 = greeting1;
	}

	public String getGreeting2() {
		return greeting2;
	}

	public void setGreeting2(String greeting2) {
		this.greeting2 = greeting2;
	}

	public String getGreeting3() {
		return greeting3;
	}

	public void setGreeting3(String greeting3) {
		this.greeting3 = greeting3;
	}

	public String getGreeting4() {
		return greeting4;
	}

	public void setGreeting4(String greeting4) {
		this.greeting4 = greeting4;
	}

	public String getGreeting5() {
		return greeting5;
	}

	public void setGreeting5(String greeting5) {
		this.greeting5 = greeting5;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getPromotionDesc1() {
		return promotionDesc1;
	}

	public void setPromotionDesc1(String promotionDesc1) {
		this.promotionDesc1 = promotionDesc1;
	}

	public String getPromotionDesc2() {
		return promotionDesc2;
	}

	public void setPromotionDesc2(String promotionDesc2) {
		this.promotionDesc2 = promotionDesc2;
	}

	public String getPromotionDesc3() {
		return promotionDesc3;
	}

	public void setPromotionDesc3(String promotionDesc3) {
		this.promotionDesc3 = promotionDesc3;
	}

	public String getPromotionDesc4() {
		return promotionDesc4;
	}

	public void setPromotionDesc4(String promotionDesc4) {
		this.promotionDesc4 = promotionDesc4;
	}

	public String getPromotionDesc5() {
		return promotionDesc5;
	}

	public void setPromotionDesc5(String promotionDesc5) {
		this.promotionDesc5 = promotionDesc5;
	}

	public List<IV_Resp_PrintSlipSubBean> getItem() {
		return item;
	}

	public void setItem(List<IV_Resp_PrintSlipSubBean> item) {
		this.item = item;
	}

	
}
