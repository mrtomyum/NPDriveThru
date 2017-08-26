package bean.response;

import java.util.List;

public class SO_Res_ARDepositBean {
	boolean success;
	boolean error;
	String message;
	List<SO_Res_ListARDepositBean>deposit;
	
	public SO_Res_ARDepositBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Res_ARDepositBean(boolean success, boolean error, String message,
			List<SO_Res_ListARDepositBean> deposit) {
		super();
		this.success = success;
		this.error = error;
		this.message = message;
		this.deposit = deposit;
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

	public List<SO_Res_ListARDepositBean> getDeposit() {
		return deposit;
	}

	public void setDeposit(List<SO_Res_ListARDepositBean> deposit) {
		this.deposit = deposit;
	}
	
	
}
