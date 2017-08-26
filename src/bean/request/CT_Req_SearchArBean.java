package bean.request;

public class CT_Req_SearchArBean {
    private String access_token;
    private String ar_code;
    
	public CT_Req_SearchArBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Req_SearchArBean(String access_token, String ar_code) {
		super();
		this.access_token = access_token;
		this.ar_code = ar_code;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getAr_code() {
		return ar_code;
	}

	public void setAr_code(String ar_code) {
		this.ar_code = ar_code;
	}
    
    
}
