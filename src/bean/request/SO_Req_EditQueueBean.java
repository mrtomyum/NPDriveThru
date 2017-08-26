package bean.request;

public class SO_Req_EditQueueBean {
	private String access_token;
	private String car_brand;
	private int queue_id;
	private int status;
	private String sale_code;
	private String plate_number;
	
	
	public SO_Req_EditQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_EditQueueBean(String access_token, String car_brand,
			int queue_id, int status, String sale_code, String plate_number) {
		super();
		this.access_token = access_token;
		this.car_brand = car_brand;
		this.queue_id = queue_id;
		this.status = status;
		this.sale_code = sale_code;
		this.plate_number = plate_number;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getCar_brand() {
		return car_brand;
	}


	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}


	public String getPlate_number() {
		return plate_number;
	}


	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}
	
}
