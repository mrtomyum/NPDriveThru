package bean.request;

public class SO_Req_SearchQueueBean {
	private String access_token;//: "xxx xxx",
	private String keyword;//: "W01-SCV5908-”,       
	private String page;//: “pickup” หรือ “checkout” (เพื่อแยกรายการว่าแสดงในหน้า pickup หรือ checkout)
	
	
	public SO_Req_SearchQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_SearchQueueBean(String access_token, String keyword,
			String page) {
		super();
		this.access_token = access_token;
		this.keyword = keyword;
		this.page = page;
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


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}
	
	
}
