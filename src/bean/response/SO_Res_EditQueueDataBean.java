package bean.response;

public class SO_Res_EditQueueDataBean {
	private int queue_id;
	private String car_brand;
	private int status;
	private String sale_code;
	private String plate_number;
	
	public SO_Res_EditQueueDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_EditQueueDataBean(int queue_id, String car_brand, int status,
			String sale_code, String plate_number) {
		super();
		this.queue_id = queue_id;
		this.car_brand = car_brand;
		this.status = status;
		this.sale_code = sale_code;
		this.plate_number = plate_number;
	}

	public int getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}

	public String getCar_brand() {
		return car_brand;
	}

	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
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
