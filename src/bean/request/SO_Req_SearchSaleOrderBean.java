package bean.request;

public class SO_Req_SearchSaleOrderBean {
	private String access_token;
	private String keyword;
	
	
	public SO_Req_SearchSaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_SearchSaleOrderBean(String access_token, String keyword) {
		super();
		this.access_token = access_token;
		this.keyword = keyword;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


}
