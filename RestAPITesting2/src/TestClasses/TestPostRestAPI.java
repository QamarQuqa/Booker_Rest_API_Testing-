package TestClasses;

import static org.junit.Assert.*;

import java.net.HttpURLConnection;

import org.json.simple.JSONObject;
import org.junit.Ignore;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;

public class TestPostRestAPI {

	// Authentication Testing
	
	@Ignore
	@Test
	public void test_valid_login()  throws Exception {
		
		// 1. Open Connection --- HttpURLConnection
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.Auth, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.TokenJSONFileV);
		// 3. Post Request
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		// 4. Reading Response
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		// 5. convert String to JSON
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		assertTrue("Invalid Login" ,!jsonObject.get("token").equals(""));
	}
	
	
	
	@Ignore
	@Test
	public void test_invalid_login() throws Exception {
		
		// 1. Open Connection --- HttpURLConnection
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.Auth, HTTPMethod.POST,HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.TokenJSONFileN);
		// 3. Post Request
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		// 4. Reading Response
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		// 5. convert String to JSON
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		assertTrue("Valid Login" ,jsonObject.get("reason").equals("Bad credentials"));
	}
	

	

	
	//Create
	@Ignore
	@Test
	public void testCreateValidBooking() throws Exception {
	
		// 1. Open Connection --- HttpURLConnection
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.NewBookingFullReqJSONFile);
		// 3. Post Request
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		// 4. Reading Response
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		// 5. convert String to JSON
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		// testing if all fields exist or not 
		jsonObject.remove("bookingid");
		JSONObject request_object = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		assertTrue("Invalid Creation", request_object.equals(((JSONObject) jsonObject.get("booking"))));
	}
	
	
	
	@Ignore
	@Test
	public void testCreateInValidBooking() throws Exception {
		
		 // 1. Open Connection --- HttpURLConnection
		 HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST, HTTPRequestsContentTypes.JSON);
	     // 2. Prepare Json Object
		 String resquestJSONObject= JSONUtils.readJSONObjectFromFile(FilesPaths.NewBookingMissReqJSONFile);
		 System.out.print(resquestJSONObject);
	     // 3. Post Request
		 RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		 // 4. validation connection response code 
		 assertTrue("unable to connect to webservice", connection.getResponseCode() == 500);
		}
		
	
	
	@Ignore
	@Test
	public void testCreateEmptyReq() throws Exception {
		
		 // 1. Open Connection --- HttpURLConnection
		 HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST, HTTPRequestsContentTypes.JSON);
	        // 2. Prepare Json Object
		 String resquestJSONOblect = JSONUtils.readJSONObjectFromFile(FilesPaths.NewBookingEmptyReqJSONFile);
		 System.out.print(resquestJSONOblect);
	     // 3. Post Request
		 RestClientHandler.sendPost(connection, resquestJSONOblect, HTTPRequestsContentTypes.JSON);
		 // 4. validation connection response code 
		 assertTrue("unable to connect to webservice", connection.getResponseCode() == 500);
	}
}


