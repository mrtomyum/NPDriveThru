package bean.response;

public class SO_Res_QueuePickupBean {
	private int queue_id;
	private String zone_id;
	
	
	public SO_Res_QueuePickupBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_QueuePickupBean(int queue_id, String zone_id) {
		super();
		this.queue_id = queue_id;
		this.zone_id = zone_id;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public String getZone_id() {
		return zone_id;
	}


	public void setZone_id(String zone_id) {
		this.zone_id = zone_id;
	}
	
	
	
}
