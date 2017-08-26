package bean.request;

public class CT_Req_SearchInvoiceBean {
	private String access_token;
	private String invoice_no;
	
	
	public CT_Req_SearchInvoiceBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CT_Req_SearchInvoiceBean(String access_token, String invoice_no) {
		super();
		this.access_token = access_token;
		this.invoice_no = invoice_no;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getInvoice_no() {
		return invoice_no;
	}


	public void setInvoice_no(String invoice_no) {
		this.invoice_no = invoice_no;
	}

}
