package controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.PT_Reqs_MovePictureBean;
import bean.response.CT_Resp_ResponseBean;


public class PictureController {
	String vQryItem;
	String vQryUser;
	
	SQLExecController sqlExec = new SQLExecController();
	ExcecuteController mySqlExec = new ExcecuteController();
	CT_Resp_ResponseBean resp = new CT_Resp_ResponseBean();
	
    public CT_Resp_ResponseBean p(PT_Reqs_MovePictureBean req){
    	BufferedImage image = null;
        try {
          
            //URL url = new URL("http://qserver.nopadol.com/tmp/111.jpg");
        	URL url = new URL(req.getFromUrl());
            image = ImageIO.read(url);
            
            ImageIO.write(image, "jpg",new File(req.getToPath()));
            
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println("Done");
        
        return resp;
    }
    
    
    
    
    public CT_Resp_ResponseBean savePicUser(String dbName,PT_Reqs_MovePictureBean req){
    	BufferedImage image = null;
    	boolean isSuccess;
        try {
          
            //URL url = new URL("http://qserver.nopadol.com/tmp/111.jpg");
        	URL url = new URL(req.getFromUrl());
            image = ImageIO.read(url);
            
            ImageIO.write(image, "jpg",new File(req.getToPath()));
            
        	vQryUser = "call SaveUserPicture '"+req.getCode()+"','"+req.getToPath()+"'";
       		 try {
     			isSuccess= mySqlExec.execute(dbName,vQryUser);
     		} catch (Exception e) {
     			isSuccess=false;
     		}
            
            
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println("Done");
        
        return resp;
    }
    
    
    public CT_Resp_ResponseBean savePicItem(String dbName,PT_Reqs_MovePictureBean req){
    	BufferedImage image = null;
    	boolean isSuccess;
        try {
        	
        	
			   String toPath = "//var//www//html//pictures//item//";
			   String toPathUpload ;
			   
			      String SubStr1 = new String("tmp");
			      String Str = req.getFromUrl();
			      String pathUpdate;
	
	
			      System.out.println(Str.indexOf(SubStr1 ));
			      System.out.println(Str.length());
			      System.out.println(Str.substring(Str.indexOf( SubStr1)+4, Str.length()));
			      
				String fileName= Str.substring(Str.indexOf( SubStr1)+4, Str.length());
				
				pathUpdate = "http://qserver.nopadol.com/pictures/item/"+fileName;
				toPathUpload = toPath +fileName;
				//http://qserver.nopadol.com/drivethru/tmp/757f9ee3e185cc111d01273c1d61f16d.jpg
				//http://qserver.nopadol.com/drivethru/tmp/092fe369fd2fa0ffa2545b2807e11219.jpg
          
            //URL url = new URL("http://qserver.nopadol.com/tmp/111.jpg");
        	URL url = new URL(req.getFromUrl());
            image = ImageIO.read(url);
            
            ImageIO.write(image, "jpg",new File(toPathUpload));
            
        	vQryItem = "exec dbo.USP_NP_SaveItemPicture '"+req.getCode()+"','"+pathUpdate+"'";
        	System.out.println(vQryItem);
        	try{
        		isSuccess= sqlExec.executeSql(dbName,vQryItem);
        	}catch(Exception e){
        		isSuccess=false;
        	}

            
        } catch (IOException e) {
        	e.printStackTrace();
        }
        System.out.println("Done");
        
        return resp;
    }
    
	
//
//    public CT_Resp_ResponseBean TransferPic(PT_Reqs_MovePictureBean file){
//    	InputStream inStream = null;
//    	OutputStream outStream = null;
//    	
//    	String pathFrom="";
//    	String pathTo="";
//    	String fileFrom="";
//    	String fileTo="";
//    	
//    	pathFrom = "\\var\\192.168.0.250\\tmp\\";
//    	//pathTo = "\\var\\www\\html\\pictures\\employee\\";
//    	
//    	//pathFrom = "D:\\TestWSClient\\";
//    	pathTo = "D:\\Moo\\";
//    	
//    	try{
//    		
//    	    File afile =new File(pathFrom+file.getPictureName());
//    	    File bfile =new File(pathTo+file.getPictureName());
//    	    
//    	    System.out.println("From Pic:"+afile);
//    	    System.out.println("To Pic:"+bfile);
//    		
//    	    inStream = new FileInputStream(afile);
//    	    outStream = new FileOutputStream(bfile);
//        	
//    	    byte[] buffer = new byte[1024];
//    		
//    	    int length;
//    	    //copy the file content in bytes 
//    	    while ((length = inStream.read(buffer)) > 0){
//    	  
//    	    	outStream.write(buffer, 0, length);
//    	 
//    	    }
//    	    resp.setIsSuccess(true);
//    	    resp.setProcess("Move Picture");
//    	    resp.setProcessDesc("Move Successfully");
//    	    inStream.close();
//    	    outStream.close();
//    	    
//    	    //delete the original file
//    	    afile.delete();
//    	    
//    	    System.out.println("File is copied successful!");
//    	    
//    	}catch(Exception e){
//    		e.printStackTrace();
//    	    resp.setIsSuccess(false);
//    	    resp.setProcess("Move Picture");
//    	    resp.setProcessDesc("Move Fail");
//    	}
//    	
//    	return resp;
//    }
//    
//    
//    public CT_Resp_ResponseBean saveImage() throws IOException {
//		String imageUrl = "http://www.avajava.com/images/avajavalogo.jpg";
//		String destinationFile = "image.jpg";
//		
//		URL url = new URL(imageUrl);
//		InputStream is = url.openStream();
//		OutputStream os = new FileOutputStream(destinationFile);
//
//		byte[] b = new byte[2048];
//		int length;
//		
//	    resp.setIsSuccess(true);
//	    resp.setProcess("Move Picture");
//	    resp.setProcessDesc("Move Successfully");
//
//		while ((length = is.read(b)) != -1) {
//			os.write(b, 0, length);
//		}
//
//		is.close();
//		os.close();
//		return resp;
//	}
//
//    public CT_Resp_ResponseBean download(String address, String localFileName) {
//        OutputStream out = null;
//        URLConnection conn = null;
//        InputStream in = null;
//
//        try {
//            URL url = new URL(address);
//            out = new BufferedOutputStream(new FileOutputStream(localFileName));
//            conn = url.openConnection();
//            in = conn.getInputStream();
//            byte[] buffer = new byte[1024];
//
//            int numRead;
//            long numWritten = 0;
//
//            while ((numRead = in.read(buffer)) != -1) {
//                out.write(buffer, 0, numRead);
//                numWritten += numRead;
//            }
//
//            System.out.println("Test :"+localFileName + "\t" + numWritten);
//        } 
//        catch (Exception exception) { 
//            exception.printStackTrace();
//        } 
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//                if (out != null) {
//                    out.close();
//                }
//            } 
//            catch (IOException ioe) {
//            }
//        }
//        
//        
//        return resp;
//    }
//    
//    public static boolean copy(File source, File destination) {
//        BufferedInputStream fin = null;
//        BufferedOutputStream fout = null;
//        try {
//            int bufSize = 8 * 1024;
//            fin = new BufferedInputStream(new FileInputStream(source), bufSize);
//            fout = new BufferedOutputStream(new FileOutputStream(destination), bufSize);
//            copyPipe(fin, fout, bufSize);
//        }
//        catch (IOException ioex) {
//            return false;
//        }
//        catch (SecurityException sx) {
//            return false;
//        }
//        finally {
//            if (fin != null) {
//                try {
//                    fin.close();
//                }
//                catch (IOException cioex) {
//                }
//            }
//            if (fout != null) {
//                try {
//                    fout.close();
//                }
//                catch (IOException cioex) {
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Save URL contents to a file.
//     */
//    public static boolean copy(URL from, File to) {
//        BufferedInputStream urlin = null;
//        BufferedOutputStream fout = null;
//        try {
//            int bufSize = 8 * 1024;
//            urlin = new BufferedInputStream(
//                    from.openConnection().getInputStream(),
//                    bufSize);
//            fout = new BufferedOutputStream(new FileOutputStream(to), bufSize);
//            copyPipe(urlin, fout, bufSize);
//        }
//        catch (IOException ioex) {
//            return false;
//        }
//        catch (SecurityException sx) {
//            return false;
//        }
//        finally {
//            if (urlin != null) {
//                try {
//                    urlin.close();
//                }
//                catch (IOException cioex) {
//                }
//            }
//            if (fout != null) {
//                try {
//                    fout.close();
//                }
//                catch (IOException cioex) {
//                }
//            }
//        }
//        return true;
//    }
//
//    /**
//     * Reads data from the input and writes it to the output, until the end of the input
//     * stream.
//     * 
//     * @param in
//     * @param out
//     * @param bufSizeHint
//     * @throws IOException
//     */
//    public static void copyPipe(InputStream in, OutputStream out, int bufSizeHint)
//            throws IOException {
//        int read = -1;
//        byte[] buf = new byte[bufSizeHint];
//        while ((read = in.read(buf, 0, bufSizeHint)) >= 0) {
//            out.write(buf, 0, read);
//        }
//        out.flush();
//    }
//    
//    
//    public boolean downloadFileFromURL(String urlString, File destination) {   
//    	boolean move=true;
//        try {
//            URL website = new URL(urlString);
//            ReadableByteChannel rbc;
//            rbc = Channels.newChannel(website.openStream());
//            FileOutputStream fos = new FileOutputStream(destination);
//            fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
//            fos.close();
//            rbc.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return  move;
//    }
//    
//    
//    public void downloadPic(String address, String localFileName) {
//        OutputStream out = null;
//        URLConnection conn = null;
//        InputStream in = null;
//        
//        CT_Resp_ResponseBean send;
//
//        try {
//            URL url = new URL(address);
//            out = new BufferedOutputStream(new FileOutputStream(localFileName));
//            conn = url.openConnection();
//            in = conn.getInputStream();
//            byte[] buffer = new byte[1024];
//
//            int numRead;
//            long numWritten = 0;
//
//            while ((numRead = in.read(buffer)) != -1) {
//                out.write(buffer, 0, numRead);
//                numWritten += numRead;
//            }
//
//            System.out.println(localFileName + "\t" + numWritten);
//        } 
//        catch (Exception exception) { 
//            exception.printStackTrace();
//        } 
//        finally {
//            try {
//                if (in != null) {
//                    in.close();
//                }
//                if (out != null) {
//                    out.close();
//                }
//            } 
//            catch (IOException ioe) {
//            }
//        }
//    }
//
//    public void testPict()
//    {
//        BufferedImage image =null;
//        try{
// 
//            URL url =new URL("http://qserver.nopadol.com/tmp/111.jpg");
//            // read the url
//           image = ImageIO.read(url);
// 
//            //for png
//            ImageIO.write(image, "png",new File("/tmp/111.png"));
// 
//            // for jpg
//            ImageIO.write(image, "jpg",new File("/tmp/have_a_question.jpg"));
// 
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
//    
//    
//    public void dlp() {
//    	   
//        try{
//           String fileName = "111.jpg";
//           String website = "http://qserver.nopadol.com/tmp/"+fileName;
//           
//           System.out.println("Downloading File From: " + website);
//           
//           URL url = new URL(website);
//           InputStream inputStream = url.openStream();
//           OutputStream outputStream = new FileOutputStream(fileName);
//           byte[] buffer = new byte[2048];
//           
//           int length = 0;
//           
//           while ((length = inputStream.read(buffer)) != -1) {
//              System.out.println("Buffer Read of length: " + length);
//              outputStream.write(buffer, 0, length);
//           }
//           
//           inputStream.close();
//           outputStream.close();
//           
//        }catch(Exception e){
//           System.out.println("Exception: " + e.getMessage());
//        }
//     }
//    

}
