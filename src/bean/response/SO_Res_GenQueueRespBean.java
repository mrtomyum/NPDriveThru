package bean.response;

import java.util.ArrayList;
import java.util.List;

import bean.request.SO_Req_ListOwnerPhoneBean;

public class SO_Res_GenQueueRespBean {
	boolean success;
	boolean error;
	String message;
	SO_Res_ListSearchQueueDataBean queue;
    List<SO_Res_QueuePickupBean> pickup_id =new ArrayList<SO_Res_QueuePickupBean>();
    
    
	public SO_Res_GenQueueRespBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_GenQueueRespBean(boolean success, boolean error,
			String message, SO_Res_ListSearchQueueDataBean queue,
			List<SO_Res_QueuePickupBean> pickup_id) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.queue = queue;
		this.pickup_id = pickup_id;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public SO_Res_ListSearchQueueDataBean getQueue() {
		return queue;
	}


	public void setQueue(SO_Res_ListSearchQueueDataBean queue) {
		this.queue = queue;
	}


	public List<SO_Res_QueuePickupBean> getPickup_id() {
		return pickup_id;
	}


	public void setPickup_id(List<SO_Res_QueuePickupBean> pickup_id) {
		this.pickup_id = pickup_id;
	}


}
