package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class RP_Resp_QueueMasterBean {
	CT_Resp_ResponseBean response;
	List<RP_Resp_QueueMaster> data;
	
	
	public RP_Resp_QueueMasterBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RP_Resp_QueueMasterBean(CT_Resp_ResponseBean response,
			List<RP_Resp_QueueMaster> data) {
		super();
		this.response = response;
		this.data = data;
	}


	public CT_Resp_ResponseBean getResponse() {
		return response;
	}


	public void setResponse(CT_Resp_ResponseBean response) {
		this.response = response;
	}


	public List<RP_Resp_QueueMaster> getData() {
		return data;
	}


	public void setData(List<RP_Resp_QueueMaster> data) {
		this.data = data;
	}
	
	
}
