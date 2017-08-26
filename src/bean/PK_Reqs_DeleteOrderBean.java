package bean;

public class PK_Reqs_DeleteOrderBean {
	private String access_token;
	private int qId;
	
	
	public PK_Reqs_DeleteOrderBean() {

	}
	
	public PK_Reqs_DeleteOrderBean(String access_token, int qId) {

		this.access_token = access_token;
		this.qId = qId;
	}
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getqId() {
		return qId;
	}
	public void setqId(int qId) {
		this.qId = qId;
	}
	
	
}
