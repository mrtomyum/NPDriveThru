package bean;

import java.util.Date;

import bean.response.CT_Resp_ResponseBean;

public class LoginResponseBean {
	
	private CT_Resp_ResponseBean response;
	private String accessToken;
	private String accessDatetime;
	private String pathPHPUpload;
	private String pathFile;
	private UserBean user;
	
	public LoginResponseBean(){}

	public LoginResponseBean(CT_Resp_ResponseBean response, String accessToken,
			String accessDatetime, String pathPHPUpload, String pathFile,
			UserBean user) {
		super();
		this.response = response;
		this.accessToken = accessToken;
		this.accessDatetime = accessDatetime;
		this.pathPHPUpload = pathPHPUpload;
		this.pathFile = pathFile;
		this.user = user;
	}

	public CT_Resp_ResponseBean getResponse() {
		return response;
	}

	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessDatetime() {
		return accessDatetime;
	}

	public void setAccessDatetime(String accessDatetime) {
		this.accessDatetime = accessDatetime;
	}

	public String getPathPHPUpload() {
		return pathPHPUpload;
	}

	public void setPathPHPUpload(String pathPHPUpload) {
		this.pathPHPUpload = pathPHPUpload;
	}

	public String getPathFile() {
		return pathFile;
	}

	public void setPathFile(String pathFile) {
		this.pathFile = pathFile;
	}

	public UserBean getUser() {
		return user;
	}

	public void setUser(UserBean user) {
		this.user = user;
	}

}
