package bean.response;

public class SO_Res_QueueBean {
	private int queue_id;
	private String otp_password;
	private String receiver_name;
	private String pickup_datetime;
    private String plate_number;
    private String car_brand;
    
    
	public SO_Res_QueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_QueueBean(int queue_id, String otp_password,
			String receiver_name, String pickup_datetime, String plate_number,
			String car_brand) {
		super();
		this.queue_id = queue_id;
		this.otp_password = otp_password;
		this.receiver_name = receiver_name;
		this.pickup_datetime = pickup_datetime;
		this.plate_number = plate_number;
		this.car_brand = car_brand;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public String getOtp_password() {
		return otp_password;
	}


	public void setOtp_password(String otp_password) {
		this.otp_password = otp_password;
	}


	public String getReceiver_name() {
		return receiver_name;
	}


	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getPickup_datetime() {
		return pickup_datetime;
	}


	public void setPickup_datetime(String pickup_datetime) {
		this.pickup_datetime = pickup_datetime;
	}


	public String getPlate_number() {
		return plate_number;
	}


	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}


	public String getCar_brand() {
		return car_brand;
	}


	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}

	
}
