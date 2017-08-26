package controller;

import java.sql.SQLException;
import java.sql.Statement;

import connect.SQLConn;

public class SQLExecController {
	
	private final SQLConn ds = SQLConn.INSTANCE;
	private boolean isSuccess;
	public boolean isSuccess() {
		return isSuccess;
	}
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	public boolean executeSql(String dbName,String sql) {
		
		try {
			Statement stmt = ds.getSqlStatement(dbName);
	
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
