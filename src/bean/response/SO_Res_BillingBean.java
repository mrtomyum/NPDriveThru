package bean.response;

public class SO_Res_BillingBean {
	private boolean success;
	private boolean error;
	private String message;
	private double total_amount;
	SO_Res_InvoiceBean invoice;
	private int is_print_short_form;
	private int is_print_cash_form;
	private int is_print_credit_form;
	
	public SO_Res_BillingBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_BillingBean(boolean success, boolean error, String message,
			double total_amount, SO_Res_InvoiceBean invoice,
			int is_print_short_form, int is_print_cash_form,
			int is_print_credit_form) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.total_amount = total_amount;
		this.invoice = invoice;
		this.is_print_short_form = is_print_short_form;
		this.is_print_cash_form = is_print_cash_form;
		this.is_print_credit_form = is_print_credit_form;
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

	public double getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(double total_amount) {
		this.total_amount = total_amount;
	}

	public SO_Res_InvoiceBean getInvoice() {
		return invoice;
	}

	public void setInvoice(SO_Res_InvoiceBean invoice) {
		this.invoice = invoice;
	}

	public int getIs_print_short_form() {
		return is_print_short_form;
	}

	public void setIs_print_short_form(int is_print_short_form) {
		this.is_print_short_form = is_print_short_form;
	}

	public int getIs_print_cash_form() {
		return is_print_cash_form;
	}

	public void setIs_print_cash_form(int is_print_cash_form) {
		this.is_print_cash_form = is_print_cash_form;
	}

	public int getIs_print_credit_form() {
		return is_print_credit_form;
	}

	public void setIs_print_credit_form(int is_print_credit_form) {
		this.is_print_credit_form = is_print_credit_form;
	}

	
}
