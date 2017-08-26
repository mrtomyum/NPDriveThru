package bean.response;

import java.util.List;

public class SO_Res_QueueStatusBean {
	List<SO_Res_ListQueueStatusBean>status;

	public SO_Res_QueueStatusBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_QueueStatusBean(List<SO_Res_ListQueueStatusBean> status) {
		super();
		this.status = status;
	}

	public List<SO_Res_ListQueueStatusBean> getStatus() {
		return status;
	}

	public void setStatus(List<SO_Res_ListQueueStatusBean> status) {
		this.status = status;
	}
	
	
}
