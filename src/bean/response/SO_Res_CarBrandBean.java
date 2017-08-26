package bean.response;

import java.util.List;

public class SO_Res_CarBrandBean {
	boolean success;
	boolean error;
	String message;
	List<SO_Res_ListCarBrandBean>car_brand;
	
	public SO_Res_CarBrandBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_CarBrandBean(boolean success, boolean error, String message,
			List<SO_Res_ListCarBrandBean> car_brand) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.car_brand = car_brand;
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

	public List<SO_Res_ListCarBrandBean> getCar_brand() {
		return car_brand;
	}

	public void setCar_brand(List<SO_Res_ListCarBrandBean> car_brand) {
		this.car_brand = car_brand;
	}

	
}
