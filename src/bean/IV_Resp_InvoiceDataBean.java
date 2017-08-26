package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class IV_Resp_InvoiceDataBean {
	CT_Resp_ResponseBean response;
	private String docNo;
	private String docDate;
	private String companyName;
	private String taxId;
	private String posId;
	private String cashierCode;
	private String cashierName;
	private String saleCode;
	private String saleName;
	private String billTime;
	private double totalAmount;
	private int	taxRate;
	private double sumOfItemAmount;
	private double afterDiscount;
	private double beforeTaxAmount;
	private double taxAmount;
	private double cashAmount;
	private double creditAmount;
	private double sumBankAmount;
	private double sumChqAmount;
	private double changeAmount;
	private String arCode;
	private String arName;
	private String billAddress;
	private String telePhone;
	
	List<IV_Resp_PrintSlipSubBean> item;

	public IV_Resp_InvoiceDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IV_Resp_InvoiceDataBean(CT_Resp_ResponseBean response, String docNo,
			String docDate, String companyName, String taxId, String posId,
			String cashierCode, String cashierName, String saleCode,
			String saleName, String billTime, double totalAmount, int taxRate,
			double sumOfItemAmount, double afterDiscount,
			double beforeTaxAmount, double taxAmount, double cashAmount,
			double creditAmount, double sumBankAmount, double sumChqAmount,
			double changeAmount, String arCode, String arName,
			String billAddress, String telePhone,
			List<IV_Resp_PrintSlipSubBean> item) {
		super();
		this.response = response;
		this.docNo = docNo;
		this.docDate = docDate;
		this.companyName = companyName;
		this.taxId = taxId;
		this.posId = posId;
		this.cashierCode = cashierCode;
		this.cashierName = cashierName;
		this.saleCode = saleCode;
		this.saleName = saleName;
		this.billTime = billTime;
		this.totalAmount = totalAmount;
		this.taxRate = taxRate;
		this.sumOfItemAmount = sumOfItemAmount;
		this.afterDiscount = afterDiscount;
		this.beforeTaxAmount = beforeTaxAmount;
		this.taxAmount = taxAmount;
		this.cashAmount = cashAmount;
		this.creditAmount = creditAmount;
		this.sumBankAmount = sumBankAmount;
		this.sumChqAmount = sumChqAmount;
		this.changeAmount = changeAmount;
		this.arCode = arCode;
		this.arName = arName;
		this.billAddress = billAddress;
		this.telePhone = telePhone;
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

	public String getCashierCode() {
		return cashierCode;
	}

	public void setCashierCode(String cashierCode) {
		this.cashierCode = cashierCode;
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
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

	public int getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(int taxRate) {
		this.taxRate = taxRate;
	}

	public double getSumOfItemAmount() {
		return sumOfItemAmount;
	}

	public void setSumOfItemAmount(double sumOfItemAmount) {
		this.sumOfItemAmount = sumOfItemAmount;
	}

	public double getAfterDiscount() {
		return afterDiscount;
	}

	public void setAfterDiscount(double afterDiscount) {
		this.afterDiscount = afterDiscount;
	}

	public double getBeforeTaxAmount() {
		return beforeTaxAmount;
	}

	public void setBeforeTaxAmount(double beforeTaxAmount) {
		this.beforeTaxAmount = beforeTaxAmount;
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

	public double getSumBankAmount() {
		return sumBankAmount;
	}

	public void setSumBankAmount(double sumBankAmount) {
		this.sumBankAmount = sumBankAmount;
	}

	public double getSumChqAmount() {
		return sumChqAmount;
	}

	public void setSumChqAmount(double sumChqAmount) {
		this.sumChqAmount = sumChqAmount;
	}

	public double getChangeAmount() {
		return changeAmount;
	}

	public void setChangeAmount(double changeAmount) {
		this.changeAmount = changeAmount;
	}

	public String getArCode() {
		return arCode;
	}

	public void setArCode(String arCode) {
		this.arCode = arCode;
	}

	public String getArName() {
		return arName;
	}

	public void setArName(String arName) {
		this.arName = arName;
	}

	public String getBillAddress() {
		return billAddress;
	}

	public void setBillAddress(String billAddress) {
		this.billAddress = billAddress;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public List<IV_Resp_PrintSlipSubBean> getItem() {
		return item;
	}

	public void setItem(List<IV_Resp_PrintSlipSubBean> item) {
		this.item = item;
	}
	
	
	
}
