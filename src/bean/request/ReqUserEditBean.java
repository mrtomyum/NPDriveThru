package bean.request;

public class ReqUserEditBean {
	String accessToken;
	userEditBean user;
	
	
	public ReqUserEditBean(String accessToken, userEditBean user) {
		//super();
		this.accessToken = accessToken;
		this.user = user;
	}
	
	public ReqUserEditBean() {}
	
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public userEditBean getUser() {
		return user;
	}
	public void setUser(userEditBean user) {
		this.user = user;
	}
	
}
