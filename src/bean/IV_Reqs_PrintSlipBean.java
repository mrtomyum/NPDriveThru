package bean;

public class IV_Reqs_PrintSlipBean {
	private String access_token;
	private int type;
	private String invoiceNo;
	private String arCode;
	
	public IV_Reqs_PrintSlipBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IV_Reqs_PrintSlipBean(String access_token, int type,
			String invoiceNo, String arCode) {
		super();
		this.access_token = access_token;
		this.type = type;
		this.invoiceNo = invoiceNo;
		this.arCode = arCode;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getArCode() {
		return arCode;
	}

	public void setArCode(String arCode) {
		this.arCode = arCode;
	}

	
	
	
}
