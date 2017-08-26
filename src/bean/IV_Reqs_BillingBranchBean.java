package bean;

import java.util.List;

import bean.request.DT_User_LoginBranchBean;

public class IV_Reqs_BillingBranchBean {
	private DT_User_LoginBranchBean dbName;
    private String accessToken;
    private String arCode;
    private int confirm;
    private int qId;
    private double cash;
    private double debtAmount;
    
    
	public IV_Reqs_BillingBranchBean() {
		super();
		// TODO Auto-generated constructor stub
	}


	public IV_Reqs_BillingBranchBean(DT_User_LoginBranchBean dbName,
			String accessToken, String arCode, int confirm, int qId,
			double cash, double debtAmount) {
		super();
		this.dbName = dbName;
		this.accessToken = accessToken;
		this.arCode = arCode;
		this.confirm = confirm;
		this.qId = qId;
		this.cash = cash;
		this.debtAmount = debtAmount;
	}


	public DT_User_LoginBranchBean getDbName() {
		return dbName;
	}


	public void setDbName(DT_User_LoginBranchBean dbName) {
		this.dbName = dbName;
	}


	public String getAccessToken() {
		return accessToken;
	}


	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getArCode() {
		return arCode;
	}


	public void setArCode(String arCode) {
		this.arCode = arCode;
	}


	public int getConfirm() {
		return confirm;
	}


	public void setConfirm(int confirm) {
		this.confirm = confirm;
	}


	public int getqId() {
		return qId;
	}


	public void setqId(int qId) {
		this.qId = qId;
	}


	public double getCash() {
		return cash;
	}


	public void setCash(double cash) {
		this.cash = cash;
	}


	public double getDebtAmount() {
		return debtAmount;
	}


	public void setDebtAmount(double debtAmount) {
		this.debtAmount = debtAmount;
	}


}
