package bean;

import java.util.List;

import bean.response.SO_Res_ListProductQueueBean;
import bean.response.SO_Res_ListQueueStatusBean;
import bean.response.SO_Res_ListSaleOrderItemBean;

public class PK_Reqs_EditOrderBean {
	private String access_token;
	private String carNumber;
	private String carBrand;
	//private int queue_id;
	private int qId;
	private int status;
	private String saleCode;
	
//	private String access_token;
//	private int queue_id;
//	private String sale_code;
//	private String plate_number;
//	private String car_brand;
//	private int status_for_saleorder_current;

	
	
	public PK_Reqs_EditOrderBean() {
		super();
		// TODO Auto-generated constructor stub
	}

public PK_Reqs_EditOrderBean(String access_token, String carNumber, String carBrand, int qId, int status,
		String saleCode) {
	super();
	this.access_token = access_token;
	this.carNumber = carNumber;
	this.carBrand = carBrand;
	this.qId = qId;
	this.status = status;
	this.saleCode = saleCode;
}

public String getAccess_token() {
	return access_token;
}

public void setAccess_token(String access_token) {
	this.access_token = access_token;
}

public String getCarNumber() {
	return carNumber;
}

public void setCarNumber(String carNumber) {
	this.carNumber = carNumber;
}

public String getCarBrand() {
	return carBrand;
}

public void setCarBrand(String carBrand) {
	this.carBrand = carBrand;
}

public int getqId() {
	return qId;
}

public void setqId(int qId) {
	this.qId = qId;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public String getSaleCode() {
	return saleCode;
}

public void setSaleCode(String saleCode) {
	this.saleCode = saleCode;
}


}
