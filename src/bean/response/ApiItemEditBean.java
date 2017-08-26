package bean.response;

import bean.ResponseBean;

public class ApiItemEditBean {
	private ResponseBean response;

	public ApiItemEditBean(ResponseBean response) {
		super();
		this.response = response;
	}
	
	public ApiItemEditBean() {};
	
	public ResponseBean getResponse() {
		return response;
	}

	public void setResponse(ResponseBean response) {
		this.response = response;
	}
	
}
