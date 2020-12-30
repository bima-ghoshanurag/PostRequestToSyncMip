package com.mip.app.main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.json.JSONObject;

import com.mip.app.constants.ApplicationKeys;
import com.mip.framework.logger.LoggerFactory;
import com.mip.framework.logger.MIPLogger;

public class PostRequest {
	
	private static MIPLogger logger = LoggerFactory.getInstance().getLogger(PostRequest.class);

	//private static HttpURLConnection con;	

	public static void main(String[] args) {
		try{
			File file = new File(ApplicationKeys.CSV_FILE_NAME);
      
			String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJSQTEzMjI4IiwiYXV0aG9yaXRpZXMiOiJHU0FfQVBQIiwiZXhwIjoxNjA3OTU5NjE5LCJpc3MiOiJpbi5taXAuand0In0.es35D3DxkYsRSMkrh9FjC6KmqKvtwatXR20gk7ylUFyiNwe2lHoug_h_Z2xnyquYW5VSTCpfu9utAxcPy4C28A";
			//String jsonInputString="";
			//String jsonInputString="{\"customer\" :{\"customerDetails\" : {\"customerName\" : \"AnuNewWow\", \"customerAge\" : 35,\"customerGender\" : \"M\",\"customerMsisdn\" : \"01812347181\",\"createdBy\" : 13223,\"createdDate\" : \"2020-03-28 14:53:01\"},  \"customerSubscriptions\" : [ {\"offerId\" : 5,\"offerCoverId\" : 11,\"healthTipsSmsFrequency\" : 5,\"healthTipsSmsLang\" : \"B\",\"documentTypeId\" : 3,\"documentValue\" : \"Ders2hu7geu\",\"paymentConfigurationId\" : 5,\"registeredChannelId\" : 5,\"paymentChannelId\" : 3,\"nomineeDetails\" : [ {\"name\" : \"Jacob\",\"age\" : 50,\"msisdn\" : \"01898745451\",\"relationship\" : \"Father\",\"gender\" : \"M\"}]}]}}";
			//JSONObject jobs=new JSONObject();
    	
			BufferedReader br = new BufferedReader(new FileReader(file));		  
			String fileJsonRecord; 
			//int index=1;
			//int index=501;
			//int index=1001;
			//int index=2001;
			//int index=3501;
			//int index=5001;
			int index=6501;
			while ((fileJsonRecord = br.readLine()) != null){	
				//System.out.println("# "+index+" customer data");
				//System.out.println("JSON : "+fileJsonRecord);
				logger.info("# "+index+" customer data");
				logger.info("JSON : "+fileJsonRecord);
				//JSONObject jsonObj = new JSONObject(fileJsonRecord);
				//String msisdn=jsonObj.getString("customerMsisdn");
				PostRequest pr=new PostRequest();
				pr.invokeMipSyncApi(fileJsonRecord,token);
				index++;
			}
			br.close();
    	
	    //logger.info("Response: "+response);
		}catch(FileNotFoundException fe){
			//System.out.println("Exception occurred: "+fe);
			logger.error("Exception occurred: "+fe);
		}catch(IOException ie){
			//System.out.println("Exception occurred: "+ie);
			logger.error("Exception occurred: "+ie);
		}catch(Exception e){
			//System.out.println("Exception occurred: "+e);
			logger.error("Exception occurred: "+e);
		}
      
	}
	
	public void invokeMipSyncApi(String inputJsonStr, String AuthToken){
			//Base URL UAT: http://uat-ap-app-01.sg.bimamobile.internal:8080/mip-sync
			//Base URL LIVE : http://bd-app-01.sg.bimamobile.internal:8080/mip-sync/v1/register-customer
		
		  	try{
			  	URL url = new URL ("http://localhost:8080/mip-sync/v1/register-customer");
		    	//URL url = new URL ("http://uat-ap-app-01.sg.bimamobile.internal:8080/mip-sync/v1/register-customer");
			    HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("POST");
				//con.setRequestProperty("Authorization",token);
				con.setRequestProperty("X-AUTH-TOKEN",AuthToken);
				con.setRequestProperty("Content-Type", "application/json; utf-8");
				con.setRequestProperty("Accept", "application/json");
				con.setDoOutput(true);
				
				OutputStream os = con.getOutputStream();
				byte[] input = inputJsonStr.getBytes("utf-8");
				os.write(input, 0, input.length);
				
				String line;
			    StringBuilder response=new StringBuilder();
			    BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
			    while((line=reader.readLine())!=null){
			    	 response.append(line);
			    }
			    //System.out.println("response: "+response);
			    logger.info("response: "+response);
			    //JSONObject obj = new JSONObject(response.toString());
			    //String statusCode=obj.getString("statusCode");
			    //System.out.println("Msisdn: "+Msisdn+" statusCode: "+statusCode);
			
			  }catch (MalformedURLException me) {
		    	  System.out.println("Exception Occurred: "+me);		
		      }catch (IOException ioe) {
		    	  System.out.println("Exception Occurred: "+ioe);		
		      }catch(Exception e){
		    	  System.out.println("Exception Occurred: "+e);		
		      }
		
	}
}
