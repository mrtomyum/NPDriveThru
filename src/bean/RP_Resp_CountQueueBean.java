package bean;

import java.util.List;

import bean.response.CT_Resp_ResponseBean;

public class RP_Resp_CountQueueBean {
	CT_Resp_ResponseBean response;
	List<RP_Resp_CountQueue> data;
	
	public RP_Resp_CountQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RP_Resp_CountQueueBean(CT_Resp_ResponseBean response,
			List<RP_Resp_CountQueue> data) {
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

	public List<RP_Resp_CountQueue> getData() {
		return data;
	}

	public void setData(List<RP_Resp_CountQueue> data) {
		this.data = data;
	}
	
	
}
