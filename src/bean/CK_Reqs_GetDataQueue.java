package bean;

public class CK_Reqs_GetDataQueue {
	private String access_token;
	private int qId;
	
	
	public CK_Reqs_GetDataQueue() {

	}


	public CK_Reqs_GetDataQueue(String access_token, int qId) {
		
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
