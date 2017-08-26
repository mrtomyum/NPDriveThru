package bean.request;

import java.util.List;

public class SO_Req_GenOTPSaleOrderBean {
	private String access_token;
	private String doc_no;
	private int bill_type;
	private int tax_type;
	private int delivery_type;
	private String receiver_name;
	private String pickup_datetime;
	List<SO_Req_ListOwnerPhoneBean> owner_phone;//เบอร์โทร คนรับของ
    List<SO_Req_ListOwnerPhoneBean> receiver_phone;//เบอร์โทร คนรับของ
    //private String otp_password;
    private String plate_number;
    private String car_brand;
    List<SO_Req_ListItemToQueueBean> item;
    
    
	public SO_Req_GenOTPSaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Req_GenOTPSaleOrderBean(String access_token, String doc_no,
			int bill_type, int tax_type, int delivery_type,
			String receiver_name, String pickup_datetime,
			List<SO_Req_ListOwnerPhoneBean> owner_phone,
			List<SO_Req_ListOwnerPhoneBean> receiver_phone,
			String plate_number, String car_brand,
			List<SO_Req_ListItemToQueueBean> item) {
		super();
		this.access_token = access_token;
		this.doc_no = doc_no;
		this.bill_type = bill_type;
		this.tax_type = tax_type;
		this.delivery_type = delivery_type;
		this.receiver_name = receiver_name;
		this.pickup_datetime = pickup_datetime;
		this.owner_phone = owner_phone;
		this.receiver_phone = receiver_phone;
		//this.otp_password = otp_password;
		this.plate_number = plate_number;
		this.car_brand = car_brand;
		this.item = item;
	}


	public String getAccess_token() {
		return access_token;
	}


	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}


	public String getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}


	public int getBill_type() {
		return bill_type;
	}


	public void setBill_type(int bill_type) {
		this.bill_type = bill_type;
	}


	public int getTax_type() {
		return tax_type;
	}


	public void setTax_type(int tax_type) {
		this.tax_type = tax_type;
	}


	public int getDelivery_type() {
		return delivery_type;
	}


	public void setDelivery_type(int delivery_type) {
		this.delivery_type = delivery_type;
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


	public List<SO_Req_ListOwnerPhoneBean> getOwner_phone() {
		return owner_phone;
	}


	public void setOwner_phone(List<SO_Req_ListOwnerPhoneBean> owner_phone) {
		this.owner_phone = owner_phone;
	}


	public List<SO_Req_ListOwnerPhoneBean> getReceiver_phone() {
		return receiver_phone;
	}


	public void setReceiver_phone(List<SO_Req_ListOwnerPhoneBean> receiver_phone) {
		this.receiver_phone = receiver_phone;
	}


//	public String getOtp_password() {
//		return otp_password;
//	}
//
//
//	public void setOtp_password(String otp_password) {
//		this.otp_password = otp_password;
//	}


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


	public List<SO_Req_ListItemToQueueBean> getItem() {
		return item;
	}


	public void setItem(List<SO_Req_ListItemToQueueBean> item) {
		this.item = item;
	}

}
