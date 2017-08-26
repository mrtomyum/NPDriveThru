package bean.response;

import java.util.List;

public class SO_Res_SaleOrderInvoiceBean {
	private boolean success;
	private boolean error;
	private String message;
	
	private String doc_no;
	private String doc_date;
	private String ar_code;
	private String ar_name;
	private String ar_address;
	private String ar_telephone;
	private String ar_fax;
	private String sale_code;
	private String sale_name;
	private String so_refno;
	private String creator_code;
	private String creator_name;
	private String tax_no;
	private int tax_rate;
	private int tax_type;
	private String my_description;
	private double sum_item_amount;
	private double discount_amount;
	private double after_discount_amount;
	private double before_tax_amount;
	private double tax_amount;
	private double total_amount;
	private int is_condition_send;
	private int credit_day;
	private String contact_code;
	private String contact_name;
	private String approve_code;
	private String approve_name;
	private String so_doc_date;
	private double point;
	private String form_type;
	private List<SO_Res_ListItemInvoiceBean> items;
	
	
	public SO_Res_SaleOrderInvoiceBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_SaleOrderInvoiceBean(boolean success, boolean error, String message, String doc_no, String doc_date,
			String ar_code, String ar_name, String ar_address, String ar_telephone, String ar_fax, String sale_code,
			String sale_name, String so_refno, String creator_code, String creator_name, String tax_no, int tax_rate,
			int tax_type, String my_description, double sum_item_amount, double discount_amount,
			double after_discount_amount, double before_tax_amount, double tax_amount, double total_amount,
			int is_condition_send, int credit_day, String contact_code, String contact_name, String approve_code,
			String approve_name, String so_doc_date, double point, String form_type,
			List<SO_Res_ListItemInvoiceBean> items) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.doc_no = doc_no;
		this.doc_date = doc_date;
		this.ar_code = ar_code;
		this.ar_name = ar_name;
		this.ar_address = ar_address;
		this.ar_telephone = ar_telephone;
		this.ar_fax = ar_fax;
		this.sale_code = sale_code;
		this.sale_name = sale_name;
		this.so_refno = so_refno;
		this.creator_code = creator_code;
		this.creator_name = creator_name;
		this.tax_no = tax_no;
		this.tax_rate = tax_rate;
		this.tax_type = tax_type;
		this.my_description = my_description;
		this.sum_item_amount = sum_item_amount;
		this.discount_amount = discount_amount;
		this.after_discount_amount = after_discount_amount;
		this.before_tax_amount = before_tax_amount;
		this.tax_amount = tax_amount;
		this.total_amount = total_amount;
		this.is_condition_send = is_condition_send;
		this.credit_day = credit_day;
		this.contact_code = contact_code;
		this.contact_name = contact_name;
		this.approve_code = approve_code;
		this.approve_name = approve_name;
		this.so_doc_date = so_doc_date;
		this.point = point;
		this.form_type = form_type;
		this.items = items;
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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
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


	public String getAr_address() {
		return ar_address;
	}


	public void setAr_address(String ar_address) {
		this.ar_address = ar_address;
	}


	public String getAr_telephone() {
		return ar_telephone;
	}


	public void setAr_telephone(String ar_telephone) {
		this.ar_telephone = ar_telephone;
	}


	public String getAr_fax() {
		return ar_fax;
	}


	public void setAr_fax(String ar_fax) {
		this.ar_fax = ar_fax;
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


	public String getSo_refno() {
		return so_refno;
	}


	public void setSo_refno(String so_refno) {
		this.so_refno = so_refno;
	}


	public String getCreator_code() {
		return creator_code;
	}


	public void setCreator_code(String creator_code) {
		this.creator_code = creator_code;
	}


	public String getCreator_name() {
		return creator_name;
	}


	public void setCreator_name(String creator_name) {
		this.creator_name = creator_name;
	}


	public String getTax_no() {
		return tax_no;
	}


	public void setTax_no(String tax_no) {
		this.tax_no = tax_no;
	}


	public int getTax_rate() {
		return tax_rate;
	}


	public void setTax_rate(int tax_rate) {
		this.tax_rate = tax_rate;
	}


	public int getTax_type() {
		return tax_type;
	}


	public void setTax_type(int tax_type) {
		this.tax_type = tax_type;
	}


	public String getMy_description() {
		return my_description;
	}


	public void setMy_description(String my_description) {
		this.my_description = my_description;
	}


	public double getSum_item_amount() {
		return sum_item_amount;
	}


	public void setSum_item_amount(double sum_item_amount) {
		this.sum_item_amount = sum_item_amount;
	}


	public double getDiscount_amount() {
		return discount_amount;
	}


	public void setDiscount_amount(double discount_amount) {
		this.discount_amount = discount_amount;
	}


	public double getAfter_discount_amount() {
		return after_discount_amount;
	}


	public void setAfter_discount_amount(double after_discount_amount) {
		this.after_discount_amount = after_discount_amount;
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


	public int getCredit_day() {
		return credit_day;
	}


	public void setCredit_day(int credit_day) {
		this.credit_day = credit_day;
	}


	public String getContact_code() {
		return contact_code;
	}


	public void setContact_code(String contact_code) {
		this.contact_code = contact_code;
	}


	public String getContact_name() {
		return contact_name;
	}


	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}


	public String getApprove_code() {
		return approve_code;
	}


	public void setApprove_code(String approve_code) {
		this.approve_code = approve_code;
	}


	public String getApprove_name() {
		return approve_name;
	}


	public void setApprove_name(String approve_name) {
		this.approve_name = approve_name;
	}


	public String getSo_doc_date() {
		return so_doc_date;
	}


	public void setSo_doc_date(String so_doc_date) {
		this.so_doc_date = so_doc_date;
	}


	public double getPoint() {
		return point;
	}


	public void setPoint(double point) {
		this.point = point;
	}


	public String getForm_type() {
		return form_type;
	}


	public void setForm_type(String form_type) {
		this.form_type = form_type;
	}


	public List<SO_Res_ListItemInvoiceBean> getItems() {
		return items;
	}


	public void setItems(List<SO_Res_ListItemInvoiceBean> items) {
		this.items = items;
	}

}
