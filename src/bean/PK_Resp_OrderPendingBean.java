package bean;

public class PK_Resp_OrderPendingBean {
	private int qId; 
	private String carNumber;
	private int numberOfItem;
	private String carBrand;
	private String timeCreate;
	private int status;
	private int iscancel;
	
	public PK_Resp_OrderPendingBean() {

	}
	public PK_Resp_OrderPendingBean(int qId, String carNumber,
			int numberOfItem, String carBrand, String timeCreate, int status, int iscancel) {

		this.qId = qId;
		this.carNumber = carNumber;
		this.numberOfItem = numberOfItem;
		this.carBrand = carBrand;
		this.timeCreate = timeCreate;
		this.status = status;
		this.iscancel = iscancel;
	}

	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	public String getCarNumber() {
		return carNumber;
	}
	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}
	public int getNumberOfItem() {
		return numberOfItem;
	}
	public void setNumberOfItem(int numberOfItem) {
		this.numberOfItem = numberOfItem;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(String timeCreate) {
		this.timeCreate = timeCreate;
	}
	
	public int getStatus(){
		return status;
	}
	
	public void setStatus(int status){
		this.status= status;
	}
	
	public int getIscancel(){
		return iscancel;
	}
	
	public void setIscancel(int iscancel){
		this.iscancel = iscancel;
	}
}
