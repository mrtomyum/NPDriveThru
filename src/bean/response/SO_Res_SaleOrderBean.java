package bean.response;

import java.util.ArrayList;
import java.util.List;


public class SO_Res_SaleOrderBean {
	//CT_Resp_ResponseBean resp;
	boolean success;
	boolean error;
	String message;
	List<SO_Res_ListSaleOrderBean> sale_order;
	//boolean response;
	
	
	public SO_Res_SaleOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SO_Res_SaleOrderBean(boolean success, boolean error, String message,
			List<SO_Res_ListSaleOrderBean> sale_order) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.sale_order = sale_order;
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
	public List<SO_Res_ListSaleOrderBean> getSale_order() {
		return sale_order;
	}
	public void setSale_order(List<SO_Res_ListSaleOrderBean> sale_order) {
		this.sale_order = sale_order;
	}


}
