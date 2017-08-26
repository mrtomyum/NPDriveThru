package bean.request;

public class ReqItemSearchBean {
	private String access_token;
	private String keyword;
	//private String key_word;
	private int type;
	
	
	
	public ReqItemSearchBean(){}



	public ReqItemSearchBean(String access_token, String keyword, int type) {
		super();
		this.access_token = access_token;
		this.keyword = keyword;
		this.type = type;
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



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}

		
}
