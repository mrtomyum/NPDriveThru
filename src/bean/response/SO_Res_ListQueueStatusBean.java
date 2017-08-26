package bean.response;

public class SO_Res_ListQueueStatusBean {
	private int status_id;
	private String status_for_saleorder;
	private String create_datetime;

	public SO_Res_ListQueueStatusBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ListQueueStatusBean(int status_id,
			String status_for_saleorder, String create_datetime) {
		super();
		this.status_id = status_id;
		this.status_for_saleorder = status_for_saleorder;
		this.create_datetime = create_datetime;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getStatus_for_saleorder() {
		return status_for_saleorder;
	}

	public void setStatus_for_saleorder(String status_for_saleorder) {
		this.status_for_saleorder = status_for_saleorder;
	}

	public String getCreate_datetime() {
		return create_datetime;
	}

	public void setCreate_datetime(String create_datetime) {
		this.create_datetime = create_datetime;
	}

	
}
