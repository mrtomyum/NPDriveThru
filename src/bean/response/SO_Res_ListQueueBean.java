package bean.response;

import java.util.Date;
import java.util.List;

public class SO_Res_ListQueueBean {
	private int queue_id;
	private double number_of_item;
	private Date time_created;
	private int status;// 0 (0=new 1=pickup 2=confirm 3=complete)
	private String pick_state;// : ì√Õ®—¥î,  
	private int is_cancel;//, "ZZZZ",
	private String doc_no;//: îxxxxxxxî  ( Ëß¡“°√≥’∑’Ë source ‡ªÁπ Sale Order),
	private String source;//, "drivethru" À√◊Õ "sale_order",
	private String reciver_name;//: "xxx xxx",
	private Date pickup_datetime;//: "2015-04-03 10:00",
	private String car_brand;//: "toyota",
	private String plate_number;//: "°∑7777",
	private String status_for_saleorder_current;// : ìnewî
	List<SO_Res_ListOwnerPhoneBean> owner_phone;
	List<SO_Res_ListOwnerPhoneBean> trust_phone;
	List<SO_Res_ListQueueStatusBean> status_for_saleorder_history;
	
	
	public SO_Res_ListQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_ListQueueBean(int queue_id, double number_of_item,
			Date time_created, int status, String pick_state, int is_cancel,
			String doc_no, String source, String reciver_name,
			Date pickup_datetime, String car_brand, String plate_number,
			String status_for_saleorder_current,
			List<SO_Res_ListOwnerPhoneBean> owner_phone,
			List<SO_Res_ListOwnerPhoneBean> trust_phone,
			List<SO_Res_ListQueueStatusBean> status_for_saleorder_history) {
		super();
		this.queue_id = queue_id;
		this.number_of_item = number_of_item;
		this.time_created = time_created;
		this.status = status;
		this.pick_state = pick_state;
		this.is_cancel = is_cancel;
		this.doc_no = doc_no;
		this.source = source;
		this.reciver_name = reciver_name;
		this.pickup_datetime = pickup_datetime;
		this.car_brand = car_brand;
		this.plate_number = plate_number;
		this.status_for_saleorder_current = status_for_saleorder_current;
		this.owner_phone = owner_phone;
		this.trust_phone = trust_phone;
		this.status_for_saleorder_history = status_for_saleorder_history;
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


	public Date getTime_created() {
		return time_created;
	}


	public void setTime_created(Date time_created) {
		this.time_created = time_created;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public String getPick_state() {
		return pick_state;
	}


	public void setPick_state(String pick_state) {
		this.pick_state = pick_state;
	}


	public int getIs_cancel() {
		return is_cancel;
	}


	public void setIs_cancel(int is_cancel) {
		this.is_cancel = is_cancel;
	}


	public String getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}


	public String getSource() {
		return source;
	}


	public void setSource(String source) {
		this.source = source;
	}


	public String getReciver_name() {
		return reciver_name;
	}


	public void setReciver_name(String reciver_name) {
		this.reciver_name = reciver_name;
	}


	public Date getPickup_datetime() {
		return pickup_datetime;
	}


	public void setPickup_datetime(Date pickup_datetime) {
		this.pickup_datetime = pickup_datetime;
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


	public String getStatus_for_saleorder_current() {
		return status_for_saleorder_current;
	}


	public void setStatus_for_saleorder_current(String status_for_saleorder_current) {
		this.status_for_saleorder_current = status_for_saleorder_current;
	}


	public List<SO_Res_ListOwnerPhoneBean> getOwner_phone() {
		return owner_phone;
	}


	public void setOwner_phone(List<SO_Res_ListOwnerPhoneBean> owner_phone) {
		this.owner_phone = owner_phone;
	}


	public List<SO_Res_ListOwnerPhoneBean> getTrust_phone() {
		return trust_phone;
	}


	public void setTrust_phone(List<SO_Res_ListOwnerPhoneBean> trust_phone) {
		this.trust_phone = trust_phone;
	}


	public List<SO_Res_ListQueueStatusBean> getStatus_for_saleorder_history() {
		return status_for_saleorder_history;
	}


	public void setStatus_for_saleorder_history(
			List<SO_Res_ListQueueStatusBean> status_for_saleorder_history) {
		this.status_for_saleorder_history = status_for_saleorder_history;
	}


}
