package bean.response;

import java.util.List;

public class SO_Res_ProductQueueBean {
	private boolean success;
	private boolean error;
	private String message;
	List<SO_Res_ListProductQueueBean>list;
	
	public SO_Res_ProductQueueBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ProductQueueBean(boolean success, boolean error,
			String message, List<SO_Res_ListProductQueueBean> list) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.list = list;
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

	public List<SO_Res_ListProductQueueBean> getList() {
		return list;
	}

	public void setList(List<SO_Res_ListProductQueueBean> list) {
		this.list = list;
	}
	
	
}
