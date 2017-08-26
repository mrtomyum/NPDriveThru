package bean.response;

import java.util.List;

public class SO_Res_ListSearchQueueDataBean {
	private int queue_id;//: "XXXX",
	private double number_of_item;//, "10",
	private String time_created;//, "วันเวลาสร้าง queue",
	private int status;//, 0 (0=new 1=pickup 2=confirm 3=complete) ระบบเดิม
	private int is_cancel;//, "0 หรือ 1",
	private String ar_code;//: “รหัสลุกค้า”,
	private String ar_name;//: “ชื่อลูกค้า”,
	private String sale_name;//: “ชื่อพนักงานขาย”,
	private String sale_code;
	private String doc_no;//: ”xxxxxxx”  (ส่งมากรณีที่ source เป็น Sale Order),
	private int source;//, "drivethru" หรือ "sale_order",
	private String receiver_name;//: "xxx xxx",
	private String doc_date;//: “วันที่ออกใบสั่งขาย”,
	private String pickup_datetime;//: "2015-04-03 10:00",
	private double total_amount;//: “มูลค่า”,
	private int is_loaded;//: “โหลดของขึ้นรถแล้วหรือยัง 0หรือ1”,
	private String car_brand;//: "toyota",
	private String plate_number;//: "กท7777",
	private int status_for_saleorder_current ;//: “new”
	private double total_before_amount;//: 2222,
	private double total_after_amount;//: 4444,
	private String otp_password;
	private int bill_type;
	private String cancel_remark;
	private String who_cancel;
	
	List owner_phone;
	List receiver_phone;
	List<SO_Res_ListQueueStatusBean>status_for_saleorder_history;
	List<SO_Res_ListProductQueueBean>item;
	
	
	public SO_Res_ListSearchQueueDataBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_ListSearchQueueDataBean(int queue_id, double number_of_item, String time_created, int status,
			int is_cancel, String ar_code, String ar_name, String sale_name, String sale_code, String doc_no,
			int source, String receiver_name, String doc_date, String pickup_datetime, double total_amount,
			int is_loaded, String car_brand, String plate_number, int status_for_saleorder_current,
			double total_before_amount, double total_after_amount, String otp_password, int bill_type,
			String cancel_remark, String who_cancel, List owner_phone, List receiver_phone,
			List<SO_Res_ListQueueStatusBean> status_for_saleorder_history, List<SO_Res_ListProductQueueBean> item) {
		super();
		this.queue_id = queue_id;
		this.number_of_item = number_of_item;
		this.time_created = time_created;
		this.status = status;
		this.is_cancel = is_cancel;
		this.ar_code = ar_code;
		this.ar_name = ar_name;
		this.sale_name = sale_name;
		this.sale_code = sale_code;
		this.doc_no = doc_no;
		this.source = source;
		this.receiver_name = receiver_name;
		this.doc_date = doc_date;
		this.pickup_datetime = pickup_datetime;
		this.total_amount = total_amount;
		this.is_loaded = is_loaded;
		this.car_brand = car_brand;
		this.plate_number = plate_number;
		this.status_for_saleorder_current = status_for_saleorder_current;
		this.total_before_amount = total_before_amount;
		this.total_after_amount = total_after_amount;
		this.otp_password = otp_password;
		this.bill_type = bill_type;
		this.cancel_remark = cancel_remark;
		this.who_cancel = who_cancel;
		this.owner_phone = owner_phone;
		this.receiver_phone = receiver_phone;
		this.status_for_saleorder_history = status_for_saleorder_history;
		this.item = item;
	}


	public int getQueue_id() {
		return queue_id;
	}


	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
	}


	public double getNumber_of_item() {
		return number_of_item;
	}


	public void setNumber_of_item(double number_of_item) {
		this.number_of_item = number_of_item;
	}


	public String getTime_created() {
		return time_created;
	}


	public void setTime_created(String time_created) {
		this.time_created = time_created;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getIs_cancel() {
		return is_cancel;
	}


	public void setIs_cancel(int is_cancel) {
		this.is_cancel = is_cancel;
	}


	public String getAr_code() {
		return ar_code;
	}


	public void setAr_code(String ar_code) {
		this.ar_code = ar_code;
	}


	public String getAr_name() {
		return ar_name;
	}


	public void setAr_name(String ar_name) {
		this.ar_name = ar_name;
	}


	public String getSale_name() {
		return sale_name;
	}


	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}


	public String getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}


	public int getSource() {
		return source;
	}


	public void setSource(int source) {
		this.source = source;
	}


	public String getReceiver_name() {
		return receiver_name;
	}


	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getDoc_date() {
		return doc_date;
	}


	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
	}


	public String getPickup_datetime() {
		return pickup_datetime;
	}


	public void setPickup_datetime(String pickup_datetime) {
		this.pickup_datetime = pickup_datetime;
	}


	public double getTotal_amount() {
		return total_amount;
	}


	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}


	public int getIs_loaded() {
		return is_loaded;
	}


	public void setIs_loaded(int is_loaded) {
		this.is_loaded = is_loaded;
	}


	public String getCar_brand() {
		return car_brand;
	}


	public void setCar_brand(String car_brand) {
		this.car_brand = car_brand;
	}


	public String getPlate_number() {
		return plate_number;
	}


	public void setPlate_number(String plate_number) {
		this.plate_number = plate_number;
	}


	public int getStatus_for_saleorder_current() {
		return status_for_saleorder_current;
	}


	public void setStatus_for_saleorder_current(int status_for_saleorder_current) {
		this.status_for_saleorder_current = status_for_saleorder_current;
	}


	public double getTotal_before_amount() {
		return total_before_amount;
	}


	public void setTotal_before_amount(double total_before_amount) {
		this.total_before_amount = total_before_amount;
	}


	public double getTotal_after_amount() {
		return total_after_amount;
	}


	public void setTotal_after_amount(double total_after_amount) {
		this.total_after_amount = total_after_amount;
	}


	public String getOtp_password() {
		return otp_password;
	}


	public void setOtp_password(String otp_password) {
		this.otp_password = otp_password;
	}


	public int getBill_type() {
		return bill_type;
	}


	public void setBill_type(int bill_type) {
		this.bill_type = bill_type;
	}


	public String getCancel_remark() {
		return cancel_remark;
	}


	public void setCancel_remark(String cancel_remark) {
		this.cancel_remark = cancel_remark;
	}


	public String getWho_cancel() {
		return who_cancel;
	}


	public void setWho_cancel(String who_cancel) {
		this.who_cancel = who_cancel;
	}


	public List getOwner_phone() {
		return owner_phone;
	}


	public void setOwner_phone(List owner_phone) {
		this.owner_phone = owner_phone;
	}


	public List getReceiver_phone() {
		return receiver_phone;
	}


	public void setReceiver_phone(List receiver_phone) {
		this.receiver_phone = receiver_phone;
	}


	public List<SO_Res_ListQueueStatusBean> getStatus_for_saleorder_history() {
		return status_for_saleorder_history;
	}


	public void setStatus_for_saleorder_history(List<SO_Res_ListQueueStatusBean> status_for_saleorder_history) {
		this.status_for_saleorder_history = status_for_saleorder_history;
	}


	public List<SO_Res_ListProductQueueBean> getItem() {
		return item;
	}


	public void setItem(List<SO_Res_ListProductQueueBean> item) {
		this.item = item;
	}
	
	

}
