package controller;

import java.sql.SQLException;
import java.sql.Statement;

import bean.request.DT_User_LoginBranchBean;

import connect.NPSQLConn;
import connect.QueueConnect;
import connect.S01PosConn;

public class ExcecuteController {
	
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private final NPSQLConn dq = NPSQLConn.INSTANCE;
	private boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean execute(String dbName,String sql) {
		
		try {
			Statement stmt = ds.getStatement(dbName);
	
			stmt.execute(sql);
			if (stmt != null) {
				stmt.close();
			}
			isSuccess=true;
			
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess=false;
		} finally {
			ds.close();
			
		}
		
		return isSuccess;
	}
	
	
public boolean executeSQL(DT_User_LoginBranchBean dbName,String sql) {
		
		try {
			Statement stmt = dq.getSqlStatementBranch(dbName);
	
			stmt.execute(sql);
			if (stmt != null) {
				stmt.close();
			}
			isSuccess=true;
			
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			isSuccess=false;
		} finally {
			ds.close();
			
		}
		
		return isSuccess;
	}

}
