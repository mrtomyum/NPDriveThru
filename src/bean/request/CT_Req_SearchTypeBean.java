package bean.request;

public class CT_Req_SearchTypeBean {
	private String access_token;
	private int search_type;
	private String search_data;
	
	public CT_Req_SearchTypeBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CT_Req_SearchTypeBean(String access_token, int search_type,
			String search_data) {
		super();
		this.access_token = access_token;
		this.search_type = search_type;
		this.search_data = search_data;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getSearch_type() {
		return search_type;
	}

	public void setSearch_type(int search_type) {
		this.search_type = search_type;
	}

	public String getSearch_data() {
		return search_data;
	}

	public void setSearch_data(String search_data) {
		this.search_data = search_data;
	}
	
	
}
