package bean.response;

import java.util.List;

public class SO_Res_PickingManageProductBean {
	private boolean success;
	private boolean error;
	private String message;
	List<SO_Res_ListProductQueueBean>item;
	//SO_Res_ListProductQueueBean item;
	
	
	public SO_Res_PickingManageProductBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SO_Res_PickingManageProductBean(boolean success, boolean error,
			String message, List<SO_Res_ListProductQueueBean> item) {
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
	public List<SO_Res_ListProductQueueBean> getItem() {
		return item;
	}
	public void setItem(List<SO_Res_ListProductQueueBean> item) {
		this.item = item;
	}

}
