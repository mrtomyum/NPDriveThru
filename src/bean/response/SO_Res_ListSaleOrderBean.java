package bean.response;

import java.util.ArrayList;
import java.util.List;

public class SO_Res_ListSaleOrderBean {
	private String doc_no;
	private String doc_date;
	private String ar_code;
	private String ar_name;
	private String receiver_name;
	private String sale_code;
	private String sale_name;
	private String pickup_datetime;
	private double sum_Of_item_amount;
	private double discount_amount;
	private double before_tax_amount;
	private double tax_amount;
	private double total_amount;
	private int delivery_type;
	private int bill_type;
	private int so_status;
	private int holding_status;
	private int is_confirm;
	private String approve_code;
	private String approve_datetime;
	private int is_load;
	private int tax_type;
	List owner_phone;
	List receiver_phone;
	List<SO_Res_ListSaleOrderItemBean> list_item;
	
	
	public SO_Res_ListSaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_ListSaleOrderBean(String doc_no, String doc_date,
			String ar_code, String ar_name, String receiver_name,
			String sale_code, String sale_name, String pickup_datetime,
			double sum_Of_item_amount, double discount_amount,
			double before_tax_amount, double tax_amount, double total_amount,
			int delivery_type, int bill_type, int so_status, int holding_status,
			int is_confirm, String approve_code, String approve_datetime,
			int is_load, int tax_type, List owner_phone, List receiver_phone,
			List<SO_Res_ListSaleOrderItemBean> list_item) {
		super();
		this.doc_no = doc_no;
		this.doc_date = doc_date;
		this.ar_code = ar_code;
		this.ar_name = ar_name;
		this.receiver_name = receiver_name;
		this.sale_code = sale_code;
		this.sale_name = sale_name;
		this.pickup_datetime = pickup_datetime;
		this.sum_Of_item_amount = sum_Of_item_amount;
		this.discount_amount = discount_amount;
		this.before_tax_amount = before_tax_amount;
		this.tax_amount = tax_amount;
		this.total_amount = total_amount;
		this.delivery_type = delivery_type;
		this.bill_type = bill_type;
		this.so_status = so_status;
		this.holding_status = holding_status;
		this.is_confirm = is_confirm;
		this.approve_code = approve_code;
		this.approve_datetime = approve_datetime;
		this.is_load = is_load;
		this.tax_type = tax_type;
		this.owner_phone = owner_phone;
		this.receiver_phone = receiver_phone;
		this.list_item = list_item;
	}


	public String getDoc_no() {
		return doc_no;
	}


	public void setDoc_no(String doc_no) {
		this.doc_no = doc_no;
	}


	public String getDoc_date() {
		return doc_date;
	}


	public void setDoc_date(String doc_date) {
		this.doc_date = doc_date;
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


	public String getReceiver_name() {
		return receiver_name;
	}


	public void setReceiver_name(String receiver_name) {
		this.receiver_name = receiver_name;
	}


	public String getSale_code() {
		return sale_code;
	}


	public void setSale_code(String sale_code) {
		this.sale_code = sale_code;
	}


	public String getSale_name() {
		return sale_name;
	}


	public void setSale_name(String sale_name) {
		this.sale_name = sale_name;
	}


	public String getPickup_datetime() {
		return pickup_datetime;
	}


	public void setPickup_datetime(String pickup_datetime) {
		this.pickup_datetime = pickup_datetime;
	}


	public double getSum_Of_item_amount() {
		return sum_Of_item_amount;
	}


	public void setSum_Of_item_amount(double sum_Of_item_amount) {
		this.sum_Of_item_amount = sum_Of_item_amount;
	}


	public double getDiscount_amount() {
		return discount_amount;
	}


	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}


	public double getBefore_tax_amount() {
		return before_tax_amount;
	}


	public void setBefore_tax_amount(double before_tax_amount) {
		this.before_tax_amount = before_tax_amount;
	}


	public double getTax_amount() {
		return tax_amount;
	}


	public void setTax_amount(double tax_amount) {
		this.tax_amount = tax_amount;
	}


	public double getTotal_amount() {
		return total_amount;
	}


	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}


	public int getDelivery_type() {
		return delivery_type;
	}


	public void setDelivery_type(int delivery_type) {
		this.delivery_type = delivery_type;
	}


	public int getBill_type() {
		return bill_type;
	}


	public void setBill_type(int bill_type) {
		this.bill_type = bill_type;
	}


	public int getSo_status() {
		return so_status;
	}


	public void setSo_status(int so_status) {
		this.so_status = so_status;
	}


	public int getHolding_status() {
		return holding_status;
	}


	public void setHolding_status(int holding_status) {
		this.holding_status = holding_status;
	}


	public int getIs_confirm() {
		return is_confirm;
	}


	public void setIs_confirm(int is_confirm) {
		this.is_confirm = is_confirm;
	}


	public String getApprove_code() {
		return approve_code;
	}


	public void setApprove_code(String approve_code) {
		this.approve_code = approve_code;
	}


	public String getApprove_datetime() {
		return approve_datetime;
	}


	public void setApprove_datetime(String approve_datetime) {
		this.approve_datetime = approve_datetime;
	}


	public int getIs_load() {
		return is_load;
	}


	public void setIs_load(int is_load) {
		this.is_load = is_load;
	}


	public int getTax_type() {
		return tax_type;
	}


	public void setTax_type(int tax_type) {
		this.tax_type = tax_type;
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


	public List<SO_Res_ListSaleOrderItemBean> getList_item() {
		return list_item;
	}


	public void setList_item(List<SO_Res_ListSaleOrderItemBean> list_item) {
		this.list_item = list_item;
	}


}
