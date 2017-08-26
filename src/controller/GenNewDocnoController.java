package controller;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.tomcat.jni.Time;

import bean.User_Resp_CheckDataAccessTokenBean;
import bean.request.CT_Req_ServerDataBaseBean;
import bean.request.DT_User_LoginBranchBean;

import connect.NPSQLConn;
import connect.QueueConnect;
import connect.SQLConn;

public class GenNewDocnoController {

	String vQuery ;
	String vQuerySub ;
	String vDocNo;
	String vPosNo;

	String vGenNewDocNo;
	int getID;
	int getInspectID;
	
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private final SQLConn dsSql = SQLConn.INSTANCE;
	private final NPSQLConn dsNP = NPSQLConn.INSTANCE;
	
	DT_User_LoginBranchBean connData = new DT_User_LoginBranchBean();

	private java.text.SimpleDateFormat dtDoc= new java.text.SimpleDateFormat();
	private java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
	CT_Req_ServerDataBaseBean db = new CT_Req_ServerDataBaseBean();

	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	public String genDocNo(int docType){
		int maxNo;
		
		int numMax;
		String header;
		String gDocNo;

		int nowYearInt;
		int nowYearInt1;
		
		String genNo;
		String genNo1;
		
		String  nowMonth;
		String  nowDay;
		
		String nowYear;
		String nowYear2;
		String nowYear3;
		
		genNo1 = "";
		
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			if (docType==0){
				vQuery = "select ifnull(right(MAX(docno),4),0)  as maxno from Queue where year(createdate) = year(CURRENT_DATE) and month(createdate) = month(CURRENT_DATE) and day(createdate) = day(CURRENT_DATE)";
			}
			if (docType==1){
				vQuery = "select ifnull(right(MAX(docno),4),0)  as maxno from Queue where year(createdate) = year(CURRENT_DATE) and month(createdate) = month(CURRENT_DATE) and day(createdate) = day(CURRENT_DATE)";
			}
			
			
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				maxNo = rs.getInt("maxno");
				numMax = maxNo+1;
				genNo = String.valueOf(numMax);
				if(genNo.length() == 1){
					genNo1 = "000"+genNo;
				}
				if(genNo.length() == 2){
					genNo1 = "00"+genNo;
				}
				if(genNo.length() == 3){
					genNo1 = "0"+genNo;
				}
				if(genNo.length() == 4){
					genNo1 = genNo;
				}
				
				System.out.println(genNo1);
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		nowYear = dateFormat.format(date).substring(0, 4);
		nowYearInt = Integer.parseInt(nowYear);
		System.out.println("nowYearInt = "+nowYearInt);
		if (nowYearInt>=2560){
			nowYearInt1 = nowYearInt-543;
		}else{
			nowYearInt1 = nowYearInt;
		}
		nowYear2 = String.valueOf(nowYearInt1);
		nowYear3 = nowYear2.substring(2, 4);
		
		nowMonth = dateFormat.format(date).substring(5, 7);
		nowDay = dateFormat.format(date).substring(8, 10);
		
		header = "S01-QUE";

		gDocNo = header+nowYear3+nowMonth+nowDay+"-"+genNo1;
		System.out.println("year : "+nowYearInt);
		System.out.println("year1 : "+nowYearInt1);
		System.out.println("year2 : "+nowYear2);
		System.out.println("year3 : "+nowYear3);
		System.out.println("month : "+nowMonth);
		System.out.println("day : "+nowDay);
		
		vDocNo = gDocNo;
		//vDocNo = "[{"+"\"genNewDocNo\":\""+gDocNo+"\"}]";
		
		System.out.println(vDocNo);
		
		
		return vDocNo;
	}
	
	
	public String genDocNoBranch(int docType,String branchCode){
		int maxNo;
		
		int numMax;
		String header;
		String gDocNo;

		int nowYearInt;
		int nowYearInt1;
		
		String genNo;
		String genNo1;
		
		String  nowMonth;
		String  nowDay;
		
		String nowYear;
		String nowYear2;
		String nowYear3;
		
		genNo1 = "";
		
		try{
			Statement st = ds.getStatement("SmartQ");
			
			if (docType==0){
				vQuery = "call USP_DT_SearchMaxNoQueue (0,'"+branchCode+"')";
			}
			if (docType==1){
				vQuery = "call USP_DT_SearchMaxNoQueue (0,'"+branchCode+"')";
			}
			
			System.out.println("GenDocno = "+vQuery);
			
			
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				maxNo = rs.getInt("maxno");
				numMax = maxNo+1;
				genNo = String.valueOf(numMax);
				if(genNo.length() == 1){
					genNo1 = "000"+genNo;
				}
				if(genNo.length() == 2){
					genNo1 = "00"+genNo;
				}
				if(genNo.length() == 3){
					genNo1 = "0"+genNo;
				}
				if(genNo.length() == 4){
					genNo1 = genNo;
				}
				
				System.out.println(genNo1);
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		
		Date date = new Date();
		
		System.out.println(dateFormat.format(date));
		
		nowYear = dateFormat.format(date).substring(0, 4);
		nowYearInt = Integer.parseInt(nowYear);
		
		if (nowYearInt >= 2559){
			nowYearInt1 = nowYearInt;
		}else{
			nowYearInt1 = nowYearInt+543;
		}
		
		nowYear2 = String.valueOf(nowYearInt1);
		nowYear3 = nowYear2.substring(2, 4);
		
		nowMonth = dateFormat.format(date).substring(5, 7);
		nowDay = dateFormat.format(date).substring(8, 10);
		
		header = branchCode+"-QUE";

		gDocNo = header+nowYear3+nowMonth+nowDay+"-"+genNo1;
		System.out.println("Date : "+gDocNo);
		
		vDocNo = gDocNo;
		//vDocNo = "[{"+"\"genNewDocNo\":\""+gDocNo+"\"}]";
		
		System.out.println(vDocNo);
		
		
		return vDocNo;
	}
	

	public String genPOSNo(String MachineNo){
		int maxNo;
		
		int numMax;
		String header;
		String gDocNo;

		int nowYearInt;
		int nowYearInt1;
		
		String genNo;
		String genNo1;
		
		String  nowMonth;
		String  nowDay;
		
		String nowYear;
		String nowYear2;
		String nowYear3;
		
		genNo1 = "";
		
		try{
			//Statement st = dsSql.getSqlStatement("nptest");
			Statement st = dsSql.getSqlStatement("bcnp");
			
			vQuery = "select right(MAX(docno),4) as maxno from dbo.BCARINVOICE where DocNo like '"+MachineNo+"%'";
			System.out.println("Search MaxNo :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				maxNo = rs.getInt("maxno");
				numMax = maxNo+1;
				genNo = String.valueOf(numMax);
				if(genNo.length() == 1){
					genNo1 = "000"+genNo;
				}
				if(genNo.length() == 2){
					genNo1 = "00"+genNo;
				}
				if(genNo.length() == 3){
					genNo1 = "0"+genNo;
				}
				if(genNo.length() == 4){
					genNo1 = genNo;
				}
				
				System.out.println("Bill GenNo :"+genNo1);
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dsSql.close();
		}
		
		
		Date date = new Date();
		
		//System.out.println(dateFormat.format(date));
		
		nowYear = dateFormat.format(date).substring(0, 4);
		nowYearInt = Integer.parseInt(nowYear);
		nowYearInt1 = nowYearInt+543;
		nowYear2 = String.valueOf(nowYearInt1);
		nowYear3 = nowYear2.substring(2, 4);
		
		nowMonth = dateFormat.format(date).substring(5, 7);
		nowDay = dateFormat.format(date).substring(8, 10);
		
		header = MachineNo;

		gDocNo = header+nowYear3+nowMonth+nowDay+"-"+genNo1;
		System.out.println("Date : "+gDocNo);
		
		vPosNo= gDocNo;
		//vDocNo = "[{"+"\"genNewDocNo\":\""+gDocNo+"\"}]";
		
		System.out.println("POSNO: "+vPosNo);
		
		
		return vPosNo;
	}
	
	
	public String genPOSInvoiceNo(String MachineNo,String serverName,String dataBaseName){
		int maxNo;
		
		int numMax;
		String header;
		String gDocNo;

		int nowYearInt;
		int nowYearInt1;
		
		String genNo;
		String genNo1;
		
		String  nowMonth;
		String  nowDay;
		
		String nowYear;
		String nowYear2;
		String nowYear3;
		
		genNo1 = "";
		
		try{
			Statement st = dsSql.getSqlStatement(serverName,dataBaseName);
			
			vQuery = "select right(MAX(docno),4) as maxno from dbo.BCARINVOICE where DocNo like '"+MachineNo+"%'";
			System.out.println("Search MaxNo :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				maxNo = rs.getInt("maxno");
				numMax = maxNo+1;
				genNo = String.valueOf(numMax);
				if(genNo.length() == 1){
					genNo1 = "000"+genNo;
				}
				if(genNo.length() == 2){
					genNo1 = "00"+genNo;
				}
				if(genNo.length() == 3){
					genNo1 = "0"+genNo;
				}
				if(genNo.length() == 4){
					genNo1 = genNo;
				}
				
				System.out.println("Bill GenNo :"+genNo1);
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dsSql.close();
		}
		
		
		Date date = new Date();
		
		//System.out.println(dateFormat.format(date));
		
		nowYear = dateFormat.format(date).substring(0, 4);
		nowYearInt = Integer.parseInt(nowYear);
		nowYearInt1 = nowYearInt+543;
		nowYear2 = String.valueOf(nowYearInt1);
		nowYear3 = nowYear2.substring(2, 4);
		
		nowMonth = dateFormat.format(date).substring(5, 7);
		nowDay = dateFormat.format(date).substring(8, 10);
		
		header = MachineNo;

		gDocNo = header+nowYear3+nowMonth+nowDay+"-"+genNo1;
		System.out.println("Date : "+gDocNo);
		
		vPosNo= gDocNo;
		//vDocNo = "[{"+"\"genNewDocNo\":\""+gDocNo+"\"}]";
		
		System.out.println("POSNO: "+vPosNo);
		
		
		return vPosNo;
	}
	
	User_Resp_CheckDataAccessTokenBean access_token = new User_Resp_CheckDataAccessTokenBean();
	public String genInvoiceNo(String access_token,int bill_type){
		int maxNo;
		
		int numMax;
		String header;
		String gDocNo;

		int nowYearInt;
		int nowYearInt1;
		
		String genNo;
		String genNo1;
		
		String  nowMonth;
		String  nowDay;
		
		String nowYear;
		String nowYear2;
		String nowYear3;
		
		String company_code;
		String header_no;
				
		genNo1 = "";
		
		company_code = "S01";
		
		java.util.Date yourDate = new java.util.Date();
				
		Date date = new Date();
		
		System.out.println(dateFormat.format(yourDate));
		
		db.setServerName("192.168.0.7");
		//db.setDatabaseName("nptest");
		db.setDatabaseName("bcnp");
		
		nowYear = dateFormat.format(date).substring(0, 4);
		nowYearInt = Integer.parseInt(nowYear);
		if(nowYear.equals("2560")){
			nowYearInt1 = nowYearInt;
		}else{
			nowYearInt1 = nowYearInt+543;
		}
		nowYear2 = String.valueOf(nowYearInt1);
		nowYear3 = nowYear2.substring(2, 4);
		
		nowMonth = dateFormat.format(date).substring(5, 7);
		nowDay = dateFormat.format(date).substring(8, 10);
		
		System.out.println("nowYear = "+nowYear+",nowYear2="+nowYear2+",nowYear3="+nowYear3+",nowMonth="+nowMonth);
		
		if(company_code=="S01"){
			if(bill_type==0){
				header_no = "S01-IHV"+nowYear3+nowMonth;
			}else{
				header_no = "W01-ICV"+nowYear3+nowMonth;
			}
		}else{
			if(bill_type==0){
				header_no = "S02-IHV"+nowYear3+nowMonth;
			}else{
				header_no = "W02-ICV"+nowYear3+nowMonth;
			}	
		}
		
		try{
			Statement st = dsSql.getSqlStatement(db.getServerName(),db.getDatabaseName());
			
			vQuery = "select right(MAX(docno),4) as maxno from dbo.BCARINVOICE where DocNo like '"+header_no+"%'";
			System.out.println("Search MaxNo :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				maxNo = rs.getInt("maxno");
				numMax = maxNo+1;
				genNo = String.valueOf(numMax);
				if(genNo.length() == 1){
					genNo1 = "000"+genNo;
				}
				if(genNo.length() == 2){
					genNo1 = "00"+genNo;
				}
				if(genNo.length() == 3){
					genNo1 = "0"+genNo;
				}
				if(genNo.length() == 4){
					genNo1 = genNo;
				}
				
				System.out.println("Bill GenNo :"+genNo1);
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dsSql.close();
		}
		

		gDocNo = header_no+"-"+genNo1;
		System.out.println("Date : "+gDocNo);
		
		vPosNo= gDocNo;
		//vDocNo = "[{"+"\"genNewDocNo\":\""+gDocNo+"\"}]";
		
		System.out.println("InvoiceNo: "+vPosNo);
		
		
		return vPosNo;
	}
	
	
	public String genPOSNoBranch(String MachineNo,String serverName,String dataBaseName){
		int maxNo;
		
		int numMax;
		String header;
		String gDocNo;

		int nowYearInt;
		int nowYearInt1;
		
		String genNo;
		String genNo1;
		
		String  nowMonth;
		String  nowDay;
		
		String nowYear;
		String nowYear2;
		String nowYear3;
		
		genNo1 = "";
		
		connData.setServerName(serverName);
		connData.setDbName(dataBaseName);
		
		try{
			Statement st = dsNP.getSqlStatementBranch(connData);
			
			vQuery = "select right(MAX(docno),4) as maxno from dbo.BCARINVOICE where DocNo like '"+MachineNo+"%'";
			System.out.println("Search MaxNo :"+vQuery);
			ResultSet rs = st.executeQuery(vQuery);
			

//			vQuery = "exec dbo.USP_DT_SearchMaxPos '"+MachineNo+"'";
//			System.out.println("Search MaxNo Branch :"+vQuery);
//			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				maxNo = rs.getInt("maxno");
				numMax = maxNo+1;
				genNo = String.valueOf(numMax);
				if(genNo.length() == 1){
					genNo1 = "000"+genNo;
				}
				if(genNo.length() == 2){
					genNo1 = "00"+genNo;
				}
				if(genNo.length() == 3){
					genNo1 = "0"+genNo;
				}
				if(genNo.length() == 4){
					genNo1 = genNo;
				}
				
				System.out.println("Bill GenNo :"+genNo1);
			}
			
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			dsNP.close();
		}
		
		
		Date date = new Date();
		
		System.out.println("Format Date :"+dateFormat.format(date));
		
		nowYear = dateFormat.format(date).substring(0, 4);
		nowYearInt = Integer.parseInt(nowYear);
		if (nowYearInt >= 2559){
			nowYearInt1 = nowYearInt;
		}else{
			nowYearInt1 = nowYearInt+543;
		}
		
		nowYear2 = String.valueOf(nowYearInt1);
		nowYear3 = nowYear2.substring(2, 4);
		
		nowMonth = dateFormat.format(date).substring(5, 7);
		nowDay = dateFormat.format(date).substring(8, 10);
		
		header = MachineNo;

		gDocNo = header+nowYear3+nowMonth+nowDay+"-"+genNo1;
		System.out.println("Date : "+gDocNo);
		
		vPosNo= gDocNo;
		//vDocNo = "[{"+"\"genNewDocNo\":\""+gDocNo+"\"}]";
		
		System.out.println("POSNO: "+vPosNo);
		
		
		return vPosNo;
	}
	
	
	
	public int genqId(){
		int qId;
		
		qId = 0;
		try{
			//Statement st = ds.getStatement("DriveThru_Test");
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "select ifnull(MAX(qId),0)+1  as qId from Queue where year(createdate) = year(CURRENT_DATE) and month(createdate) = month(CURRENT_DATE) and day(createdate) = day(CURRENT_DATE)";

			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				qId = rs.getInt("qId");

				System.out.println(qId);
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		System.out.println(vDocNo);
		
		
		return qId;
	}
	
	
	public int genqIdBranch(String branchCode){
		int qId;
		
		qId = 0;
		try{
			Statement st = ds.getStatement("SmartQ");
			
			vQuery = "call USP_DT_SearchMaxID ('"+ branchCode +"')";
			
			System.out.println("GenScript QID"+vQuery);

			ResultSet rs = st.executeQuery(vQuery);
			while(rs.next()){
				qId = rs.getInt("qId");

				System.out.println("QueueID = " +qId);
			}
		    rs.close();
		    st.close();
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ds.close();
		}
		
		System.out.println(vDocNo);
		
		
		return qId;
	}
}
