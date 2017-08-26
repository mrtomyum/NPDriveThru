package controller;


import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import bean.LoginBean;
import bean.LoginResponseBean;
import bean.UserBean;
import bean.UserRegisterBean;
import bean.UserSearchBean;
import bean.UserSearchResponseBean;
import bean.request.DT_User_LoginBranchBean;
import bean.response.ApiItemEditBean;
import bean.response.CT_Resp_ResponseBean;
import connect.QueueConnect;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;
import bean.ResponseBean;


public class UserController {
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private String Textstring;
	
	CT_Resp_ResponseBean response = new CT_Resp_ResponseBean();
	ResponseBean resp = new ResponseBean();
	ApiItemEditBean apiResp = new ApiItemEditBean();
	UserBean user = new UserBean();
	List<UserBean> userList = new ArrayList<UserBean>();
	LoginResponseBean loginResponse = new LoginResponseBean();
	UserSearchResponseBean userSearchResponse = new UserSearchResponseBean();
	ExcecuteController excecute = new  ExcecuteController();
	BranchController branch = new BranchController();
	
	
	NPSQLExecController npexec = new NPSQLExecController();
	DT_User_LoginBranchBean server = new DT_User_LoginBranchBean();
	getDataFromData getData = new getDataFromData();
	LoginBean userCode = new LoginBean();
	
	private boolean isSuccess;
		public boolean isSuccess() {
			return isSuccess;
		}
		public void setSuccess(boolean isSuccess) {
			this.isSuccess = isSuccess;
		}
		
	private void loginResponseTemplage(String keyword) {
		response.setIsSuccess(false);
 		response.setProcessDesc("Not found :" +keyword);
 		
 		user.setId(-1);
		    user.setCode(null);
		    user.setName(null);
		    user.setPassword(null);
		    user.setImage_filename(null);
		    user.setEmail(null);
		    user.setRole(-1);
		    user.setActiveStatus(-1);
		    user.setIsConfirm(-1);
		    user.setCreatorCode(null);
		    user.setCreatedateTime(null);
		    user.setLasteditorCode(null);
		    user.setLasteditdateTime(null);
	}
	
	
	public LoginResponseBean login(String dbName,LoginBean login) {
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		String branchCode="S01";
		
		try {
			
			branchCode = branch.getBranchID(dbName, login.getBranchId()).getCode();
			// get connection
			Statement stmt = ds.getStatement(dbName);
		    
		    //Textstring="select * from User where code='"+login.getEmployeeCode()+"'";
			
			Textstring="select * from User where code='"+login.getEmployeeCode()+"' and branchCode='"+branchCode+"'";
		    
		   	System.out.println("SQL LogIn :" + Textstring);
		   	System.out.println("สาขาที่เข้าใช้งาน : "+login.getBranchId());
		    
		    ResultSet rs = stmt.executeQuery(Textstring);
		    

		   
		    response.setProcess("login");
		        if ( rs.next() != false ) 
		        	{	
		        		response.setIsSuccess(true);
		        		response.setProcessDesc("successful");
		        		
		        		user.setId(rs.getInt("id"));
		    		    user.setCode(rs.getString("code"));
		    		    user.setName(rs.getString("name"));
		    		    user.setPassword(rs.getString("password"));
		    		    user.setImage_filename(rs.getString("picturePath"));
		    		    user.setEmail(rs.getString("email"));
		    		    user.setRole(rs.getInt("role"));
		    		    user.setActiveStatus(rs.getInt("activeStatus"));
		    		    user.setIsConfirm(rs.getInt("isConfirm"));
		    		    user.setCreatorCode(rs.getString("creatorCode"));
		    		    user.setCreatedateTime(rs.getString("createdateTime"));
		    		    user.setLasteditorCode(rs.getString("lasteditorCode"));
		    		    user.setLasteditdateTime(rs.getString("lasteditdateTime"));
		        		
		        		String uuid = UUID.randomUUID().toString(); 
		        		loginResponse.setAccessToken(uuid);
		    		    loginResponse.setAccessDatetime(dt.format(dateNow));

		    			 
		        	} else 
		        	{
		        		loginResponseTemplage(login.getEmployeeCode());
		        	}
		        
	       	
		    loginResponse.setResponse(response); 
		    loginResponse.setUser(user); 
		    loginResponse.setPathFile("http://qserver.nopadol.com/drivethru/tmp/");
		    loginResponse.setPathPHPUpload("http://qserver.nopadol.com/drivethru/upload.php");
		    
		    System.out.println("uuid = " + loginResponse.getAccessToken() + " : "+ loginResponse.getResponse().getProcessDesc()+ "-"+loginResponse.getAccessDatetime() ); 

		    rs.close();
		    stmt.close();
		    
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		    ds.close();
		}
		
		// เก็บ log UserAccess
		
		if (loginResponse.getAccessToken()!=null){
		
		Textstring="insert UserAccess(userId,userCode,userUUID,branchCode,dateTimeStamp)"
				+" select '" +loginResponse.getUser().getId()+"','"+loginResponse.getUser().getCode()
					+"','"+loginResponse.getAccessToken()+"','"+branchCode+"','"+loginResponse.getAccessDatetime()+"'";
		
						
		 System.out.println(Textstring);
		 System.out.println("BranchID = "+branchCode);
		
		// return true;
		 try {
			isSuccess= excecute.execute(dbName,Textstring);
		} catch (Exception e) {
			isSuccess=false;
			loginResponseTemplage(login.getEmployeeCode());
		}
		}
		
		return loginResponse;
		
	}
	
	//User Search
	public UserSearchResponseBean userSearch(String dbName,UserSearchBean userSearch) {
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		
		try {
			// get connection
			Statement stmt = ds.getStatement(dbName);
		    
		    Textstring="select u.code,u.role from  User as u inner join UserAccess as ua on u.code=ua.userCode and ua.userUUID='"+userSearch.getAccess_token()+"'"
		    		+" order by ua.dateTimeStamp DESC LIMIT 1" ;
		    
		   	System.out.println(Textstring);
		    
		    ResultSet rs = stmt.executeQuery(Textstring);
		    
		    //template
		    response.setProcess("userSearch");
		    loginResponseTemplage(userSearch.getKeyword());
		    userSearchResponse.setResponse(response);
		    userList.clear();
		    userList.add(user);
		    userSearchResponse.setUser(userList);
		   
		    if ( rs.next() != false ) 
	        	{	
		    	System.out.println("role="+rs.getInt("role"));
	        		if (rs.getInt("role")==1) {
	        			response.setIsSuccess(true);
		        		response.setProcessDesc("successful");
		        		
	        			
	        		} else {
	        			response.setIsSuccess(false);
		        		response.setProcessDesc("User not allowed!");
		        		
	        		}
	        		
	        	} else 
	        	{
	        		response.setIsSuccess(false);
	        		response.setProcessDesc("Not found user assign!");
	        	}
		        
		    rs.close();
		    stmt.close();
		    
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    ds.close();
		}
		if (response.getIsSuccess()==true)  { // ตรวจสอบ security
			try {
				
				// get connection
				Statement stmt = ds.getStatement(dbName);
			    
			    Textstring="select * from User where code ='"+userSearch.getKeyword()+"'";
			    
			   	System.out.println("UserController="+Textstring);
			    
			    ResultSet rs = stmt.executeQuery(Textstring);
			    
			    
			    response.setProcess("userSearch");
			    
			    int roworder=0;
			    
			    userList.clear();
			   	while (rs.next()) {
			   		roworder++;
			   		
			   		user = new UserBean();	
			   		
					response.setIsSuccess(true);
	        		response.setProcessDesc("successful");
	        		 
	        		user.setId(rs.getInt("id"));
	    		    user.setCode(rs.getString("code"));
	    		    user.setName(rs.getString("name"));
	    		    System.out.println("User Name UserController ="+rs.getString("name"));
	    		    System.out.println("picturePath UserController ="+rs.getString("picturePath"));
	    		    user.setPassword(rs.getString("password"));
	    		    user.setImage_filename(rs.getString("picturePath"));
	    		    user.setEmail(rs.getString("email"));
	    		    user.setRole(rs.getInt("role"));
	    		    user.setActiveStatus(rs.getInt("activeStatus"));
	    		    user.setIsConfirm(rs.getInt("isConfirm"));
	    		    user.setCreatorCode(rs.getString("creatorCode"));
	    		    user.setCreatedateTime(rs.getString("createdateTime"));
	    		    user.setLasteditorCode(rs.getString("lasteditorCode"));
	    		    user.setLasteditdateTime(rs.getString("lasteditdateTime"));
	    		    user.setBranchCode(rs.getString("branchCode"));
	        		
	    		    userList.add(user);
					
	    		    
				}
			   	if (roworder==0) { //ค้นหาไม่พบ
			   		loginResponseTemplage(userSearch.getKeyword());
			   		userList.clear();
			   		userList.add(user);
			   	}  
		       	userSearchResponse.setResponse(response);
			    userSearchResponse.setUser(userList);
			    
			    rs.close();
			    stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			    ds.close();
			}
		}

		return userSearchResponse;
		
	}
	
	//register method
	public CT_Resp_ResponseBean register(String dbName,UserRegisterBean userRegister) {
		//System.out.println("filePath = " + "");
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		
		System.out.println("filePath = " + "");
		
		   String fromPath="http://api.nopadol.com/drivethru/tmp/";
		   String toPath = "//var//www//html//pictures//employee//";
		   String filePathTo ;
		   String filePathUpload;
		   String fileFromPath;
		   filePathTo = "http://qserver.nopadol.com/pictures/employee/";
		   
		   String fileName= userRegister.getUser().getImage_filename();
			
						
	    	BufferedImage image = null;
	        try {
	        	if(fileName.length() > 0){
					filePathTo = filePathTo+fileName;
					filePathUpload = toPath +fileName;
					fileFromPath = fromPath+fileName;
					
		        	if(fileName!=null && fileName !=""){  
		        	URL url = new URL(fileFromPath);
		            image = ImageIO.read(url);
		            
		            ImageIO.write(image, "jpg",new File(filePathUpload));
		            
	
		            // register
		            Textstring="insert User(code, name, password, picturePath, email, role,"
								+"creatorCode, createdateTime)" //, lasteditorCode, lasteditdateTime
						+" select '" +userRegister.getUser().getCode()+"','"+userRegister.getUser().getName()+"','"+userRegister.getUser().getPassword()+"','"
						+userRegister.getUser().getImage_filename()+"','"+userRegister.getUser().getEmail()+"',"+userRegister.getUser().getRole()
						+",'"+userRegister.getUser().getCreatorCode()+"','"
						+dt.format(dateNow)+"'";
			
	        	}else{
		            Textstring="insert User(code, name, password, email, role,creatorCode, createdateTime)"
						+" select '" +userRegister.getUser().getCode()+"','"+userRegister.getUser().getName()+"','"+userRegister.getUser().getPassword()+"','"
						+userRegister.getUser().getEmail()+"',"+userRegister.getUser().getRole()
						+",'"+userRegister.getUser().getCreatorCode()+"','"
						+dt.format(dateNow)+"'";
	        	}
			
					System.out.println(Textstring);
					
					//return true;
					 try {
						isSuccess= true;//excecute.execute(dbName,Textstring);
						if (isSuccess==false) {
							response.setIsSuccess(false);
				    		response.setProcessDesc("register error on save!");
						};
					} catch (Exception e) {
		//				System.out.println("yyy");
						isSuccess=false;
						response.setIsSuccess(false);
			    		response.setProcessDesc("register error on save!");
					}
			 
	        	}
		
        } catch (IOException e) {
        	e.printStackTrace();
        }
		return response;
		
	}
	

	
	//user edit
	public ApiItemEditBean userEdit(String dbName,UserRegisterBean userRegister) {
		
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		Date dateNow = new Date();
		
		System.out.println("edit:"+userRegister.getUser().getId());
		System.out.println("edit:"+userRegister.getUser().getCode());
		
			//userCode = getData.searchUserAccessToken(reqItemEdit.getAccessToken());
			//creatorCode = userCode.getEmployeeCode();
		    
		   String fromPath="http://qserver.nopadol.com/drivethru/tmp/";
		   String toPath = "//var//www//html//pictures//employee//";
		   String creatorCode="";
		   String vQryItem;
		   String filePathTo ;
		   String filePathUpload;
		   String fileFromPath;
		   
		   String fileName= userRegister.getUser().getImage_filename();
		
		   filePathTo = "http://qserver.nopadol.com/pictures/employee/";
		   String delPath = "//var//www//html//drivethru//tmp//";
		   
		   
		   
	    	BufferedImage image = null;
	    	
	        try {
	        	
				userCode = getData.searchUserAccessToken(userRegister.getAccessToken());
				creatorCode = userCode.getEmployeeCode();
				
				System.out.println("fileName.length()"+fileName.length());
	        	
	        	if(fileName.length() > 0){
	        		System.out.println("filePath1 = " + fileName);
					filePathTo = filePathTo+fileName;
					filePathUpload = toPath +fileName;
					fileFromPath = fromPath+fileName;
					
					System.out.println("filePath = " + fileName);
					
		        	URL url = new URL(fileFromPath);
		            image = ImageIO.read(url);
		            ImageIO.write(image, "jpg",new File(filePathUpload));
		            
		            Path path = FileSystems.getDefault().getPath(delPath, fileName);
		            
		            try {
		                Files.delete(path);
		            } catch (IOException | SecurityException e) {
		                System.err.println(e);
		            }
		            
					Textstring="update User set code='"+userRegister.getUser().getCode()+"',name='"+userRegister.getUser().getName()
							+"', password='"+userRegister.getUser().getPassword()+"',picturePath='"+filePathTo
							+"', email='"+userRegister.getUser().getEmail()+"',role="+userRegister.getUser().getRole()
							+",activeStatus="+userRegister.getUser().getActiveStatus()+", isConfirm="+userRegister.getUser().getIsConfirm()
							+", lasteditorCode='"+userRegister.getUser().getLasteditorCode()+"', lasteditdateTime='"+dt.format(dateNow)+"'"
							+" where code="+userRegister.getUser().getCode() ;
					
					 try {
							isSuccess= excecute.execute(dbName,Textstring);
							resp.setProcess("EmployeeEdit");
						    resp.setIsSuccess(true);
						    resp.setProcessDesc("Update Employee");
						    
						} catch (Exception e) {
							isSuccess=false;
							resp.setIsSuccess(false);
							resp.setProcess("EmployeeEdit");
							resp.setProcessDesc(e.getMessage());
						}
	           
	        	}else{
	 
	        		System.out.println("filePath2 = " + fileName);
	        		
					Textstring="update User set code='"+userRegister.getUser().getCode()+"',name='"+userRegister.getUser().getName()
							+"', password='"+userRegister.getUser().getPassword()
							+"', email='"+userRegister.getUser().getEmail()+"',role="+userRegister.getUser().getRole()
							+",activeStatus="+userRegister.getUser().getActiveStatus()+", isConfirm="+userRegister.getUser().getIsConfirm()
							+", lasteditorCode='"+userRegister.getUser().getLasteditorCode()+"', lasteditdateTime='"+dt.format(dateNow)+"'"
							+" where code="+userRegister.getUser().getCode() ;
					 try {
							isSuccess= excecute.execute(dbName,Textstring);
							resp.setProcess("EmployeeEdit");
						    resp.setIsSuccess(true);
						    resp.setProcessDesc("Update Employee");
						    
						} catch (Exception e) {
							isSuccess=false;
							resp.setIsSuccess(false);
							resp.setProcess("EmployeeEdit");
							resp.setProcessDesc(e.getMessage());
						}
	        		
	        	}
	        	
								
				 System.out.println(Textstring);


	        } catch (Exception e) {
	        	e.printStackTrace();
	        	isSuccess=false;
	        	resp.setIsSuccess(false);
	        	resp.setProcess("EmployeeEdit");
				resp.setProcessDesc(e.getMessage());
	        }
	        
	        resp.setProcess("EmployeeEdit");
	        resp.setIsSuccess(true);
	        resp.setProcessDesc("Update Employee");
		    
			server.setServerName("192.168.0.7");
			server.setDbName("bcnp");

//        	vQryItem = "exec dbo.USP_NP_InsertAPILogs 'SmartQWs','user/edit','"+userRegister.getAccessToken()+":"+userRegister.getUser().getCode()+":"+userRegister.getUser().getName()+":"+userRegister.getUser().getImage_filename()+"','"+creatorCode+"'";
//         	System.out.println(vQryItem);
//        	try{
//        		isSuccess= npexec.executeSqlBranch(server,vQryItem);
//        	}catch(Exception e){
//        		isSuccess=false;
//        	}
			
			System.out.println("IsSuccess ="+response.getIsSuccess());
			System.out.println("getProcess ="+response.getProcess());
			System.out.println("getProcessDesc ="+response.getProcessDesc());
			
			apiResp.setResponse(resp);
		
		return apiResp;
		
	}
		
}