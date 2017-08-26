package bean.response;

import java.util.List;

import bean.ResponseBean;


public class ApiItemSearchBean {
	boolean success;
	boolean error;
	String message;
	private List<ResItemSearchBean> item;
	/**
	 * @param response
	 * @param item
	 */
	
	public ApiItemSearchBean() {}
	public ApiItemSearchBean(boolean success, boolean error, String message,
			List<ResItemSearchBean> item) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.item = item;
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
	public List<ResItemSearchBean> getItem() {
		return item;
	}
	public void setItem(List<ResItemSearchBean> item) {
		this.item = item;
	}
	

}
