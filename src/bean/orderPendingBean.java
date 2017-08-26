package bean;

import java.util.List;

public class orderPendingBean {
	private List<PK_Resp_OrderPendingBean> order;
	
	public orderPendingBean() {

		// TODO Auto-generated constructor stub
	}
	public orderPendingBean(List<PK_Resp_OrderPendingBean> order) {
		super();
		this.order = order;
	}
	public List<PK_Resp_OrderPendingBean> getOrder() {
		return order;
	}
	public void setOrder(List<PK_Resp_OrderPendingBean> order) {
		this.order = order;
	}
	
	



}
