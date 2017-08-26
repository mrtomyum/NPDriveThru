package bean;

public class UserRegisterBean {
	private String accessToken;
	private UserBean user;
	
	public UserRegisterBean(){}

	public UserRegisterBean(String accessToken, UserBean user) {
		this.accessToken = accessToken;
		this.user = user;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}
	
	

}
