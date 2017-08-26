package bean.request;

public class SO_Req_SearchQueueDataBean {
	private String access_token;//: "xxx xxx",
	private String keyword;//: "W01-SCV5908-”,       
	private String pickup_date;//: “รับของวันไหน”,
	private String created_date;//: “วันที่ออกใบสั่งขาย”,
	private int status_for_saleorder_current;//: “สถานะใบสั่งขาย”,
	private String page;//: “pickup” หรือ “checkout” (เพื่อแยกรายการว่าแสดงในหน้า pickup หรือ checkout)
	private String queue_id;
	
	
	public SO_Req_SearchQueueDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_SearchQueueDataBean(String access_token, String keyword,
			String pickup_date, String created_date,
			int status_for_saleorder_current, String page, String queue_id) {
		super();
		this.access_token = access_token;
		this.keyword = keyword;
		this.pickup_date = pickup_date;
		this.created_date = created_date;
		this.status_for_saleorder_current = status_for_saleorder_current;
		this.page = page;
		this.queue_id = queue_id;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getPickup_date() {
		return pickup_date;
	}


	public void setPickup_date(String pickup_date) {
		this.pickup_date = pickup_date;
	}


	public String getCreated_date() {
		return created_date;
	}


	public void setCreated_date(String created_date) {
		this.created_date = created_date;
	}


	public int getStatus_for_saleorder_current() {
		return status_for_saleorder_current;
	}


	public void setStatus_for_saleorder_current(int status_for_saleorder_current) {
		this.status_for_saleorder_current = status_for_saleorder_current;
	}


	public String getPage() {
		return page;
	}


	public void setPage(String page) {
		this.page = page;
	}


	public String getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(String queue_id) {
		this.queue_id = queue_id;
	}

	
}
