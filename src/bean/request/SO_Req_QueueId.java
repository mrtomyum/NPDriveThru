package bean.request;

public class SO_Req_QueueId {
	private String access_token;
	private int qid;
	private String cancel_remark;
	
	public SO_Req_QueueId() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SO_Req_QueueId(String access_token, int qid, String cancel_remark) {
		super();
		this.access_token = access_token;
		this.qid = qid;
		this.cancel_remark = cancel_remark;
	}

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getQid() {
		return qid;
	}

	public void setQid(int qid) {
		this.qid = qid;
	}

	public String getCancel_remark() {
		return cancel_remark;
	}

	public void setCancel_remark(String cancel_remark) {
		this.cancel_remark = cancel_remark;
	}
	
}
