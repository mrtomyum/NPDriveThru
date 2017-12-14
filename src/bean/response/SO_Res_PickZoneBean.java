package bean.response;

import java.util.List;

public class SO_Res_PickZoneBean {
	private boolean success;
	private boolean error;
	private String message;
	List<SO_Res_ListPickZoneBean> pick_zone;
	

	public SO_Res_PickZoneBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public SO_Res_PickZoneBean(boolean success, boolean error, String message,
			List<SO_Res_ListPickZoneBean> pick_zone) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.pick_zone = pick_zone;
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


	public List<SO_Res_ListPickZoneBean> getPick_zone() {
		return pick_zone;
	}


	public void setPick_zone(List<SO_Res_ListPickZoneBean> pick_zone) {
		this.pick_zone = pick_zone;
	}


}
