package bean.request;

import java.util.List;

public class SO_Req_EditSaleOrderBean {
	private String access_token;
	private int queue_id;//: 1,"W01-SCV5908-0098",
	private String receiver_name;//: "xxx xxx",
	private String pickup_datetime;//: "2015-04-03 10:00",
	private String car_brand;//: "toyota",
	private String plate_number;//: "¡·7777",
	private int bill_type;
	List owner_phone;//: [“012398492”,”99887663”]
	List receiver_phone;//: ["0826667733", "0826667733"],
	List<SO_Req_ListEditItemSaleOrderBean> item;
	
	public SO_Req_EditSaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Req_EditSaleOrderBean(String access_token, int queue_id, String receiver_name, String pickup_datetime,
			String car_brand, String plate_number, int bill_type, List owner_phone, List receiver_phone,
			List<SO_Req_ListEditItemSaleOrderBean> item) {
		super();
		this.access_token = access_token;
		this.queue_id = queue_id;
		this.receiver_name = receiver_name;
		this.pickup_datetime = pickup_datetime;
		this.car_brand = car_brand;
		this.plate_number = plate_number;
		this.bill_type = bill_type;
		this.owner_phone = owner_phone;
		this.receiver_phone = receiver_phone;
		this.item = item;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getQueue_id() {
		return queue_id;
	}

	public void setQueue_id(int queue_id) {
		this.queue_id = queue_id;
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

	public int getBill_type() {
		return bill_type;
	}

	public void setBill_type(int bill_type) {
		this.bill_type = bill_type;
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

	public List<SO_Req_ListEditItemSaleOrderBean> getItem() {
		return item;
	}

	public void setItem(List<SO_Req_ListEditItemSaleOrderBean> item) {
		this.item = item;
	}

	
}
