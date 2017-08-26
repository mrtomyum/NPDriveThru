package controller;

import java.sql.SQLException;
import java.sql.Statement;

import bean.request.DT_User_LoginBranchBean;

import connect.NPSQLConn;

public class NPSQLExecController {
	
	private NPSQLConn ds = NPSQLConn.INSTANCE;
	
	private boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean executeSql(String svName,String dbName,String sql) {
		
		try {
			Statement stmt = ds.getSqlStatement(svName, dbName);
	
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
	
	
	public boolean executeSqlBranch(DT_User_LoginBranchBean dbName,String sql) {
		
		try {
			Statement stmt = ds.getSqlStatementBranch(dbName);
	
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
