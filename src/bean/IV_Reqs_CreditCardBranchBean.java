package bean;

import java.util.List;

public class IV_Reqs_CreditCardBranchBean {
	private String serverName;
	private String dbName;
	List<IV_Reqs_CreditCardBean> crdCard;
	
	public IV_Reqs_CreditCardBranchBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public IV_Reqs_CreditCardBranchBean(String serverName, String dbName,
			List<IV_Reqs_CreditCardBean> crdCard) {
		super();
		this.serverName = serverName;
		this.dbName = dbName;
		this.crdCard = crdCard;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public List<IV_Reqs_CreditCardBean> getCrdCard() {
		return crdCard;
	}

	public void setCrdCard(List<IV_Reqs_CreditCardBean> crdCard) {
		this.crdCard = crdCard;
	}
	
	

}
