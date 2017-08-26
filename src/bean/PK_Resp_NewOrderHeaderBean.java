package bean;

import bean.response.CT_Resp_ResponseBean;

public class PK_Resp_NewOrderHeaderBean {
	private CT_Resp_ResponseBean response; 
	private int qId;
	
	
	public PK_Resp_NewOrderHeaderBean() {

	}


	public PK_Resp_NewOrderHeaderBean(CT_Resp_ResponseBean response, int qId) {
		super();
		this.response = response;
		this.qId = qId;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public int getqId() {
		return qId;
	}


	public void setqId(int qId) {
		this.qId = qId;
	}
	

	
	
}
