package bean.response;

import java.util.List;

public class SO_Res_QueueDailyBean {
	CT_Resp_ResponseBean resp;
	List<SO_Res_ListQueueBean> listQueue;
	
	
	public SO_Res_QueueDailyBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_QueueDailyBean(CT_Resp_ResponseBean resp,
			List<SO_Res_ListQueueBean> listQueue) {
		super();
		this.resp = resp;
		this.listQueue = listQueue;
	}


	public CT_Resp_ResponseBean getResp() {
		return resp;
	}


	public void setResp(CT_Resp_ResponseBean resp) {
		this.resp = resp;
	}


	public List<SO_Res_ListQueueBean> getListQueue() {
		return listQueue;
	}


	public void setListQueue(List<SO_Res_ListQueueBean> listQueue) {
		this.listQueue = listQueue;
	}
	
	
	
}
