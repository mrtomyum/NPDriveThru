package bean;

public class IV_Reqs_InvoiceDataBean {
	private String access_token;
	private String invoiceNo;
	
	
	public IV_Reqs_InvoiceDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Reqs_InvoiceDataBean(String access_token, String invoiceNo) {
		super();
		this.access_token = access_token;
		this.invoiceNo = invoiceNo;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getInvoiceNo() {
		return invoiceNo;
	}


	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	
	
}
