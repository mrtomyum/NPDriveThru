package controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connect.QueueConnect;
import connect.SQLConn;

import bean.CM_Resp_MemberListBean;
import bean.CM_Resp_SearchCustomerBean;
import bean.SearchBean;
import bean.response.CT_Resp_ResponseBean;

public class CustomerController {
	private SQLConn sqlDS = SQLConn.INSTANCE;
	private QueueConnect ds = QueueConnect.INSTANCE;
	CM_Resp_SearchCustomerBean respCust = new CM_Resp_SearchCustomerBean();
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	List<CM_Resp_MemberListBean> member = new ArrayList<CM_Resp_MemberListBean>();
	SQLExecController sqlExec = new SQLExecController();
	private boolean isSuccess;
	String vQuery;

	
	public boolean isSuccess() {
		return isSuccess;
	}


	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}


	public CM_Resp_SearchCustomerBean searchCustomer(String dbName,SearchBean keyword){
		int roworder=0;

		try{
			
		Statement st = sqlDS.getSqlStatement(dbName);
		
		if(keyword.getKeyword()==""){
			vQuery = "select top 5 code,name1,billaddress,isnull(telephone,'') as telephone,isnull(emailaddress,'') as emailaddress,isnull(memberid,'') as memberid,isnull(taxno,isnull(idcardno,'')) as taxid,sumofmark1 as point from dbo.bcar where activestatus = 1 order by createdatetime desc,lasteditdatet desc";
		}else{
			vQuery = "select top 5 code,name1,billaddress,isnull(telephone,'') as telephone,isnull(emailaddress,'') as emailaddress,isnull(memberid,'') as memberid,isnull(taxno,isnull(idcardno,'')) as taxid,sumofmark1 as point from dbo.bcar where activestatus = 1 and (code like '%"+keyword.getKeyword()+"%' or name1 like '%"+keyword.getKeyword()+"%' or memberid like '%"+keyword.getKeyword()+"%' or billaddress like '%"+keyword.getKeyword()+"%' or telephone like '%"+keyword.getKeyword()+"%' ) order by createdatetime desc,lasteditdatet desc";
		}
		
		System.out.println(vQuery);
		
		ResultSet rs = st.executeQuery(vQuery);
		
		CM_Resp_MemberListBean evt;
		member.clear();
		while(rs.next()){
			roworder=roworder++;
			evt = new CM_Resp_MemberListBean();
			evt.setArCode(rs.getString("code"));
			evt.setArName(rs.getString("name1"));
			evt.setArAddress(rs.getString("billaddress"));
			evt.setArPoint(rs.getDouble("point"));
			evt.setArId(rs.getString("taxid"));
			evt.setArPhone(rs.getString("telephone"));
			
			member.add(evt);
		}
	   	if (roworder==0) { //§ÈπÀ“‰¡Ëæ∫
	   		respCust.setMember(member);
	   	}
	   	
		response.setIsSuccess(true);
		response.setProcess("Search Customer");
		response.setProcessDesc("Successful");
		
		respCust.setMember(member);
		respCust.setResponse(response);
		
	    rs.close();
	    st.close();
	    
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			sqlDS.close();
		}
		
		return respCust;
	}
	
	
}
