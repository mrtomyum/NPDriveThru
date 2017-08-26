package bean.response;

import java.util.List;


public class SO_Res_SaleOrderDetailsBean {
	private boolean success;
	private boolean error;
	
	private String doc_no;
	private String doc_date;
	private String ar_code;
	private String ar_name;
	private String receiver_name;
	private String sale_code;
	private String sale_name;
	private String pickup_datetime;
	List<SO_Res_ListOwnerPhoneBean>owner_phone;
	List<SO_Res_ListOwnerPhoneBean>trust_phone;
	private double sum_Of_item_amount;
	private double discount_amount;
	private double before_tax_amount;
	private double tax_amount;
	private double total_amount;
	private int is_condition_send;
	private int so_status;
	private int holding_status;
	private int is_confirm;
	private String approve_code;
	private String approve_datetime;
	private String message;
	List<SO_Res_ListSaleOrderItemBean> list_item;
	
	
	public SO_Res_SaleOrderDetailsBean() {

		// TODO Auto-generated constructor stub
	}


	public SO_Res_SaleOrderDetailsBean(boolean success, boolean error,
			String doc_no, String doc_date, String ar_code, String ar_name,
			String receiver_name, String sale_code, String sale_name,
			String pickup_datetime,
			List<SO_Res_ListOwnerPhoneBean> owner_phone,
			List<SO_Res_ListOwnerPhoneBean> trust_phone,
			double sum_Of_item_amount, double discount_amount,
			double before_tax_amount, double tax_amount, double total_amount,
			int is_condition_send, int so_status, int holding_status,
			int is_confirm, String approve_code, String approve_datetime,
			String message, List<SO_Res_ListSaleOrderItemBean> list_item) {
		super();
		this.success = success;
		this.error = error;
		this.doc_no = doc_no;
		this.doc_date = doc_date;
		this.ar_code = ar_code;
		this.ar_name = ar_name;
		this.receiver_name = receiver_name;
		this.sale_code = sale_code;
		this.sale_name = sale_name;
		this.pickup_datetime = pickup_datetime;
		this.owner_phone = owner_phone;
		this.trust_phone = trust_phone;
		this.sum_Of_item_amount = sum_Of_item_amount;
		this.discount_amount = discount_amount;
		this.before_tax_amount = before_tax_amount;
		this.tax_amount = tax_amount;
		this.total_amount = total_amount;
		this.is_condition_send = is_condition_send;
		this.so_status = so_status;
		this.holding_status = holding_status;
		this.is_confirm = is_confirm;
		this.approve_code = approve_code;
		this.approve_datetime = approve_datetime;
		this.message = message;
		this.list_item = list_item;
	}


	public boolean isSuccess() {
		return success;
	}


	public void setSuccess(boolean success) {
		this.success = success;
	}


	public boolean isError() {
		return error;
	}


	public void setError(boolean error) {
		this.error = error;
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


	public int getIs_condition_send() {
		return is_condition_send;
	}


	public void setIs_condition_send(int is_condition_send) {
		this.is_condition_send = is_condition_send;
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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public List<SO_Res_ListSaleOrderItemBean> getList_item() {
		return list_item;
	}


	public void setList_item(List<SO_Res_ListSaleOrderItemBean> list_item) {
		this.list_item = list_item;
	}


}
