package bean;

import java.util.List;

import bean.UserBean;
import bean.response.CT_Resp_ResponseBean;

public class UserSearchResponseBean {
	private CT_Resp_ResponseBean response;
	private List<UserBean> user;
	
	public UserSearchResponseBean(){}

	public UserSearchResponseBean(CT_Resp_ResponseBean response, List<UserBean> user) {
		this.response = response;
		this.user = user;
	}

	public CT_Resp_ResponseBean getResponse() {
		return response;
	}

	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}

	public List<UserBean> getUser() {
		return user;
	}

	public void setUser(List<UserBean> user) {
		this.user = user;
	}
	
	

	
	

}
