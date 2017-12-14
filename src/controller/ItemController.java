package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

import javax.imageio.ImageIO;

import bean.*;
import bean.request.DT_User_LoginBranchBean;
import bean.request.ReqItemEditBean;
import bean.request.ReqItemSearchBean;
import bean.response.ApiItemEditBean;
import bean.response.ApiItemSearchBean;
import bean.response.ResItemSearchBean;
import bean.response.SO_Res_ListQueueStatusBean;
import connect.NPSQLConn;
import connect.QueueConnect;
import connect.SQLConn;

public class ItemController {
	private final QueueConnect ds = QueueConnect.INSTANCE;
	private final SQLConn ds1 = SQLConn.INSTANCE;
	private final NPSQLConn ds2 = NPSQLConn.INSTANCE;
	private String Textstring;
	private String Textstring1;
	
	
	// Bean Response ที่ใช้เป็น Standard
	ResponseBean response = new ResponseBean();
	
	// Bean for ItemSearch
	
	ResItemSearchBean item = new ResItemSearchBean();
	List<ResItemSearchBean> itemlist = new ArrayList<ResItemSearchBean>();
	ApiItemSearchBean apiResponse = new ApiItemSearchBean();

	// Bean for itemEdit
	ApiItemEditBean apiItemEdit_Response = new ApiItemEditBean();
	ReqItemEditBean reqItemEdit = new ReqItemEditBean();
	
	
	SQLExecController sqlexec = new SQLExecController();
	ExcecuteController excecute = new  ExcecuteController();
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
		
	private void ResponseInit(String keyword) {
		response.setIsSuccess(false);
	 	response.setProcessDesc("Not found :" +keyword);
	 		 		
		    item.setItem_barcode(null);
		    item.setItem_code(null);
		    item.setItem_name(null);
		    item.setItem_price(null);
		    item.setItem_unit_code(null);
		    item.setItem_short_code(null);
		    item.setItem_remark(null);
		    item.setItem_category(null);
		    item.setItem_file_path(null);
		   
		}
	
	//Item Search
	// dbName = ฐานข้อมูลที่ใช้งาน "SmartQ" 
	public ApiItemSearchBean itemSearch(String dbName,ReqItemSearchBean reqItemSearch) {
			java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
			dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
			@SuppressWarnings("unused")
			Date dateNow = new Date();
			
			
			System.out.println("Search ItemCode");
			
			try {
				// get connection
				Statement stmt = ds.getStatement(dbName);
			    
			 // Check Permission from Access_token ;
			    Textstring="select u.code,u.role from  SmartConfig.User as u ";
			    Textstring=Textstring+"inner join SmartConfig.UserAccess as ua on u.code=ua.userCode ";
			    Textstring=Textstring+"and ua.userUUID='"+reqItemSearch.getAccess_token()+"'";
			    Textstring=Textstring+" order by ua.dateTimeStamp DESC LIMIT 1" ;
			   	System.out.println(Textstring);
			    
			    ResultSet rs = stmt.executeQuery(Textstring);
			    
			    //template
			    response.setProcess("itemSearch Events");
			    ResponseInit(reqItemSearch.getKeyword());
			   
			    apiResponse.setSuccess(true);
			    apiResponse.setError(false);
			    apiResponse.setMessage("");
			    
			    itemlist.clear();
			    itemlist.add(item);
			    apiResponse.setItem(itemlist);

			    if ( rs.next() != false ) 
	        	{	
	        		if (rs.getInt("role")!=0) {
//	        			response.setIsSuccess(true);
//		        		response.setProcessDesc("successful");
					    apiResponse.setSuccess(true);
					    apiResponse.setError(false);
					    apiResponse.setMessage("");
	        			
	        		} else {
//	        			response.setIsSuccess(false);
//		        		response.setProcessDesc("User not allowed!");
					    apiResponse.setSuccess(false);
					    apiResponse.setError(true);
					    apiResponse.setMessage("User not allowed!");
	        			
	        		}
	        		
	        	} else 
	        	{
//	        		response.setIsSuccess(false);
//	        		response.setProcessDesc("Not found user assign!");
				    apiResponse.setSuccess(false);
				    apiResponse.setError(true);
				    apiResponse.setMessage("Not found user assign!");
	        		System.out.println(response.getProcessDesc());
	        	}
			        
			    rs.close();
			    stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
			    ds.close();
			}
			if (apiResponse.isSuccess()==true)  { // ตรวจสอบ security
				try {
					
					// get connection
					Statement stmt = ds1.getSqlStatement("bcnp");
				    
					
//					if (reqItemSearch.getType() == 0 ) // search by keyword
//					{
////						System.out.println("Search ตาม Keyword");
////					    Textstring="select distinct 	a.code as itemCode,	a.name1 as itemName,b.unitCode ,b.shortCode,";
////					    Textstring = Textstring + "'' as remark , '' as filePath, b.barcode,'' as itemCategory,c.price as price ";
////					    Textstring = Textstring + "from Item a inner  join itemBarcode b on a.code = b.code " ;
////					    Textstring = Textstring + "left  join Price c on a.code=c.itemCode and b.unitcode=c.unitCode";
////					    Textstring = Textstring + " where a.code like '%"+reqItemSearch.getKeyword()+"%' ";
////					    Textstring = Textstring + " or b.barcode like  '%"+reqItemSearch.getKeyword()+"%'";
////					 //   Textstring = Textstring + " where  b.barcode =  '"+reqItemSearch.getKeyword()+"' limit 5";
////					    Textstring = Textstring + " or a.name1 like '%"+reqItemSearch.getKeyword()+"%'";
////					   Textstring = Textstring + " or b.shortcode like  '%"+reqItemSearch.getKeyword()+"%' limit 5";
//
//					    Textstring=" set dateformat dmy select distinct top 20	a.itemcode,a.barcode,isnull(b.name1,'')  as itemname,isnull(a.unitcode,'') as UnitCode, "
//					    +"isnull(c.saleprice1 ,0) as price,isnull(b.shortname,'') as shortcode,isnull(b.picfilename1,'') as  filePath  "
//					    +"from 	dbo.bcbarcodemaster a "
//					    +"left join dbo.bcitem b on a.itemcode = b.code "
//					    +"left join dbo.bcpricelist c on saletype = 0 and transporttype = 0 and c.itemcode = b.code and c.unitcode = a.unitcode and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) >= cast(rtrim(day(startdate))+'/'+rtrim(month(startdate))+'/'+rtrim(year(startdate)) as datetime) and  cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= cast(rtrim(day(stopdate))+'/'+rtrim(month(stopdate))+'/'+rtrim(year(stopdate)) as datetime) "
//					    +"where  a.barcode like  '%"+reqItemSearch.getKeyword()+"%' or b.name1 like '%"+reqItemSearch.getKeyword() +"%'  or isnull(b.shortname,'') like '%"+reqItemSearch.getKeyword()+"%' order by a.itemcode,a.barcode ";
//						
//					} 
//					else  // search หา ตาม barcode อย่างเดียวก
//					{
//						//System.out.println("Scan หาตาม barcode อย่างเดียว");
//					    //Textstring="select distinct 	a.code as itemCode,	a.name1 as itemName,b.unitCode ,b.shortCode,";
//					    //Textstring = Textstring + "'' as remark , '' as filePath, b.barcode,'' as itemCategory,c.price as price ";
//					    //Textstring = Textstring + "from Item a inner  join itemBarcode b on a.code = b.code " ;
//					    //Textstring = Textstring + "left  join Price c on a.code=c.itemCode and b.unitcode=c.unitCode";
//						//Textstring = Textstring + " where  b.barcode =  '"+reqItemSearch.getKeyword()+"' limit 1";
//						
//					    Textstring=" set dateformat dmy select 	distinct a.itemcode as itemCode,a.barcode,isnull(b.name1,'')  as itemName,isnull(a.unitcode,'') as UnitCode, "
//					    +"isnull(c.saleprice1 ,0) as price,isnull(b.shortname,'') as shortcode,isnull(b.picfilename1,'') as  filePath "
//					    +"from 	dbo.bcbarcodemaster a "
//					    +"left join dbo.bcitem b on a.itemcode = b.code "
//					    +"left join dbo.bcpricelist c on saletype = 0 and transporttype = 0 and c.itemcode = b.code and c.unitcode = a.unitcode and cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) >= cast(rtrim(day(startdate))+'/'+rtrim(month(startdate))+'/'+rtrim(year(startdate)) as datetime) and  cast(rtrim(day(getdate()))+'/'+rtrim(month(getdate()))+'/'+rtrim(year(getdate())) as datetime) <= cast(rtrim(day(stopdate))+'/'+rtrim(month(stopdate))+'/'+rtrim(year(stopdate)) as datetime) "
//					    +"where  a.barcode =  '"+reqItemSearch.getKeyword()+"'";
//					
//					
//					} 
					
					Textstring1="exec dbo.USP_POS_SearchItem "+reqItemSearch.getType()+",'"+reqItemSearch.getKeyword()+"'";
					
				   	System.out.println("Search Item = "+Textstring1);
				    ResultSet rs = stmt.executeQuery(Textstring1);
				    response.setProcess("itemSearch");
				    int roworder=0;
				    itemlist.clear();
				   	while (rs.next()) {
				   		roworder++;
				   		item = new ResItemSearchBean();	
						response.setIsSuccess(true);
		        		response.setProcessDesc("successful");
		        		
		        		item.setItem_barcode(rs.getString("barcode"));
		        		item.setItem_code(rs.getString("itemCode"));
		        		item.setItem_name(rs.getString("itemName"));
		        		item.setItem_price(rs.getDouble("price"));
		        		item.setItem_unit_code(rs.getString("unitCode"));
		        		item.setItem_remark("");
		        		item.setItem_short_code(rs.getString("shortCode"));
		        		item.setItem_file_path(rs.getString("filePath"));
		    		    itemlist.add(item);
						
		    		    System.out.println("ที่อยู่รูปภาพ :" + item.getItem_file_path());
		    		    System.out.println(item.getItem_price());

					}
				   	if (roworder==0) { //ค้นหาไม่พบ
				   		ResponseInit(reqItemSearch.getKeyword());
				   		itemlist.clear();
				   		//itemlist.add(item);
				   	}  
				    apiResponse.setSuccess(true);
				    apiResponse.setError(false);
				    apiResponse.setMessage("");
				   	apiResponse.setItem(itemlist);
				   	
				    rs.close();
				    stmt.close();
				    
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
				    ds.close();
				}
			}
			
			
			
			return apiResponse;
			
		}
	
	public ApiItemEditBean itemEdit1(String dbName,ReqItemEditBean reqItemEdit)
	{
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		@SuppressWarnings("unused")
		Date dateNow = new Date();
		String vQuery1;
		String vQryItem;
		boolean isSuccess;
		
		//Security check by token
		try {
			// get connection
			Statement stmt = ds.getStatement(dbName);
		    
		 // Check Permission from Access_token ;
		    Textstring="select u.code,u.role from  SmartConfig.User as u ";
		    Textstring=Textstring+"inner join SmartConfig.UserAccess as ua on u.code=ua.userCode ";
		    Textstring=Textstring+"and ua.userUUID='"+reqItemEdit.getAccess_token()+"'";
		    Textstring=Textstring+" where u.role=1 order by ua.dateTimeStamp DESC LIMIT 1" ;
		   	System.out.println(Textstring);
		    
		    ResultSet rs = stmt.executeQuery(Textstring);
		    //template
		    response.setProcess("itemEdit Events");
		    //ResponseInit(reqItemSearch.getKeyword());
		    //apiResponse.setResponse(response);
		    apiResponse.setSuccess(true);
		    apiResponse.setError(false);
		    apiResponse.setMessage("");
		   // itemlist.clear();
		   // itemlist.add(item);
		    apiItemEdit_Response.setResponse(response);

		    if ( rs.next() != false ) 
        	{	
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
        		System.out.println(response.getProcessDesc());
        	}
		        
	       	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		    ds.close();
		}
		
		
		
		//  Edit item part 
		if (response.getIsSuccess()==true)  { // ตรวจสอบ security
			try {
				
				
				// get connection
				Statement stmt = ds.getStatement(dbName);
			    
				// Update Shortcode reference by barcode 
			   //Textstring="update SmartQ.itemBarcode set shortcode=  '"+reqItemEdit.getShortCode()+"'  ";
			   //Textstring=Textstring +" where barcode = '"+reqItemEdit.getBarCode()+"'";
			   
			   //vQuery1 = "exec dbo.USP_DT_UpdateItem '"+reqItemEdit.getBarCode()+"','"+reqItemEdit.getFilePath()+"','"+reqItemEdit.getShortCode()+"','"+reqItemEdit.getRemark()+"'";
			   //System.out.println(vQuery1);
			  // isSuccess = sqlexec.executeSql("POS", vQuery1);
			   
			   String fromPath="http://qserver.nopadol.com/drivethru/tmp/";
			   String toPath = "//var//www//html//pictures//item//";
			   String filePathTo ;
			   String filePathUpload;
			   String fileFromPath;
			   
			      
			   String fileName= reqItemEdit.getFilePath();
				
				filePathTo = "http://qserver.nopadol.com/pictures/item/"+fileName;
				filePathUpload = toPath +fileName;
				fileFromPath = fromPath+fileName;
				
				System.out.println("fileFromPath = "+fileFromPath);
			   
		    	BufferedImage image = null;
		        try {
		          
		        	URL url = new URL(fileFromPath);
		            image = ImageIO.read(url);
		            
		            ImageIO.write(image, "jpg",new File(fileFromPath));
		            
		            
		        	vQryItem = "exec dbo.USP_NP_SaveItemPicture '"+reqItemEdit.getBarCode()+"','"+filePathUpload+"'";
		        	System.out.println(vQryItem);
		        	try{
		        		isSuccess= sqlexec.executeSql("pos",vQryItem);
		        	}catch(Exception e){
		        		isSuccess=false;
		        	}
		        	
		            
		        } catch (IOException e) {
		        	e.printStackTrace();
		        }
				
			   	//System.out.println(Textstring);
			    //int rs = stmt.executeUpdate(Textstring);
			    
			    //update filename & Remark in Item Table
			    
//			    Textstring="update SmartQ.Item set filePath=  '"+pathUpdate+"',  ";
//				Textstring=Textstring +" remark = '"+reqItemEdit.getRemark()+"'";
//				Textstring=Textstring +" where code in (select code from SmartQ.itemBarcode where barcode='"+reqItemEdit.getBarCode()+"' )";
//				   		
//				
				//System.out.println(Textstring);
				//int rs1 = stmt.executeUpdate(Textstring);
			    response.setProcess("itemEdit");
			    response.setIsSuccess(true);
			    response.setProcessDesc(reqItemEdit.getFilePath());

			   
			} catch (SQLException e) {
				response.setProcess("itemEdit");
			    response.setIsSuccess(false);
			    response.setProcessDesc("Update Item");
				e.printStackTrace();
			} finally {
			    ds.close();
			}
		}
		
		apiItemEdit_Response.setResponse(response);
		return apiItemEdit_Response;
		
		
	}
	

	public ApiItemEditBean itemEdit(String dbName,ReqItemEditBean reqItemEdit)
	{
		java.text.SimpleDateFormat dt= new java.text.SimpleDateFormat();
		dt.applyPattern("yyyy-MM-dd HH:mm:ss.S");
		@SuppressWarnings("unused")
		Date dateNow = new Date();
		String vQuery1;
		String vQryItem;
		boolean isSuccess;
		String creatorCode="";
//		String vCheckCountItem;
//		String vQueryInsert;
//		int vCountItem=0;
		
		server.setServerName("192.168.0.7");
		server.setDbName("bcnp");
		
		
		System.out.println("Insert Picture");
			try {
				
				userCode = getData.searchUserAccessToken(reqItemEdit.getAccess_token());
				creatorCode = userCode.getEmployeeCode();
				
//				try{
//				Statement st_count = ds.getStatement(dbName);
//				vCheckCountItem = "select count(code) as vCount from Item where code = '"+reqItemEdit.getBarCode()+"'"; 
//				System.out.println("vCheckCountItem : "+vCheckCountItem);
//				ResultSet rs_count = st_count.executeQuery(vCheckCountItem);
//				while(rs_count.next()){
//					vCountItem =rs_count.getInt("vCount");	
//				}
//				rs_count.close();
//				st_count.close();
//				} catch(Exception e){
//					
//				}finally{
//					ds.close();
//				}
//				
//				if (vCountItem == 0) {
//					vQueryInsert = "insert into Item(code,name1,stkUnitcode) values('"+")";
//				}
				

			   String fromPath="http://qserver.nopadol.com/drivethru/tmp/";
			   String toPath = "//var//www//html//pictures//item//";
			   String filePathTo ;
			   String filePathUpload;
			   String fileFromPath;
			   filePathTo = "http://qserver.nopadol.com/pictures/item/";
			   String delPath = "//var//www//html//drivethru//tmp//";
			   
			  // http://qserver.nopadol.com/drivethru/tmp
			   
			      
			   String fileName= reqItemEdit.getImage_filename();
				
		    	BufferedImage image = null;
		        try {
		        	System.out.println("getFilePath = "+reqItemEdit.getFilePath());
		        	System.out.println("getImage_filename = "+reqItemEdit.getImage_filename());
		        	
		            if(fileName.length() > 0){
						filePathTo = filePathTo+fileName;
						filePathUpload = toPath +fileName;
						fileFromPath = fromPath+fileName;
						
		        	URL url = new URL(fileFromPath);
			            image = ImageIO.read(url);
			            ImageIO.write(image, "jpg",new File(filePathUpload));
			            
			            
			            Path path = FileSystems.getDefault().getPath(delPath, fileName);

			            //delete the  file
			            try {
			                Files.delete(path);
			            } catch (IOException | SecurityException e) {
			                System.err.println(e);
			            }
			            
			        	vQryItem = "exec dbo.USP_NP_SaveItemPicture '"+reqItemEdit.getBarCode()+"','"+filePathTo+"','"+reqItemEdit.getShortCode()+"','"+reqItemEdit.getRemark()+"','"+creatorCode+"'";
			        	System.out.println(vQryItem);
			        	try{
			        		isSuccess= sqlexec.executeSqlPos("pos",vQryItem);
			        	}catch(Exception e){
			        		isSuccess=false;
			        	}
		            }else{
		            	
			        	vQryItem = "exec dbo.USP_NP_SaveItemPicture '"+reqItemEdit.getBarCode()+"','"+filePathTo+"','"+reqItemEdit.getShortCode()+"','"+reqItemEdit.getRemark()+"','"+creatorCode+"'";
			        	System.out.println(vQryItem);
			        	try{
			        		isSuccess= sqlexec.executeSqlPos("pos",vQryItem);
			        	}catch(Exception e){
			        		isSuccess=false;
			        	}
		            }
		            
		        	vQryItem = "exec dbo.USP_NP_InsertAPILogs 'SmartQWs','item/edit','"+reqItemEdit.getBarCode()+":"+reqItemEdit.getFilePath()+":"+reqItemEdit.getRemark()+":"+reqItemEdit.getShortCode()+":"+reqItemEdit.getImage_filename()+"','"+creatorCode+"'";
		         	System.out.println(vQryItem);
		        	try{
		        		isSuccess= npexec.executeSqlBranch(server,vQryItem);
		        	}catch(Exception e){
		        		isSuccess=false;
		        	}
		            
		            
		        } catch (IOException e) {
		        	e.printStackTrace();
		        }
				

			    response.setProcess("itemEdit");
			    response.setIsSuccess(true);
			    response.setProcessDesc("Update Item");

			   
			} catch (Exception e) {
				response.setProcess("itemEdit");
			    response.setIsSuccess(false);
			    response.setProcessDesc("Update Item");
				e.printStackTrace();
			}
		
			apiItemEdit_Response.setResponse(response);
	
		return apiItemEdit_Response;
				
	}
}
