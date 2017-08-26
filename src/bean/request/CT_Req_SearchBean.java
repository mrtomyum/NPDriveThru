package bean.request;

public class CT_Req_SearchBean {
	private String access_token;
	private String search_data;
	
	public CT_Req_SearchBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Req_SearchBean(String access_token, String search_data) {
		super();
		this.access_token = access_token;
		this.search_data = search_data;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getSearch_data() {
		return search_data;
	}

	public void setSearch_data(String search_data) {
		this.search_data = search_data;
	}
	
	
}
