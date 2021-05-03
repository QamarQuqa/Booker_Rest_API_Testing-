package TestClasses;

import static org.junit.Assert.*;

import java.io.IOException;
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

public class TestUpdateRestAPI {

	
	public JSONObject testExistUser() throws Exception { 
		
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.Auth, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		
		// 2. Prepare Json Object
		String resquestJSONObject_AUTH = JSONUtils.readJSONObjectFromFile(FilesPaths.TokenJSONFileV);
		
		// 3. Post Request
		RestClientHandler.sendPost(connection, resquestJSONObject_AUTH, HTTPRequestsContentTypes.JSON);
		
		// 4. Reading Response
		String response_AUTH = RestClientHandler.readResponse(connection);
		
		// 5. convert String to JSON
		JSONObject jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response_AUTH);
		
		return jsonObject ; 
	}
	
	
	
	@Ignore
	@Test
	public void testValidUpdateUser() throws Exception { //valid return200
		
		// Open Connection --- HttpURLConnection
		String url = URLs.BOOKING+"/2";
				
		HttpURLConnection new_connection = RestClientHandler.connectServer(url, HTTPMethod.PUT,
				HTTPRequestsContentTypes.JSON);
		
		JSONObject jsonObject = testExistUser();
		
		// Prepare header
		new_connection.setRequestProperty("Cookie", "token="+ jsonObject.get("token"));

		// Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.UpdateUserJSONFile);
		System.out.println(resquestJSONObject);
		
		// Post Request
		RestClientHandler.sendPut(new_connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		
		// Reading Response
		System.out.println(new_connection.getResponseCode());
		String response = RestClientHandler.readResponse(new_connection);
		
		System.out.println(response);
		
		JSONObject respone_jsonObject = (JSONObject) JSONUtils.convertStringToJSON(response);
		JSONObject resquestJSONObject_ = (JSONObject) JSONUtils.convertStringToJSON(resquestJSONObject);
		
		assertTrue("Invalid Update",respone_jsonObject.equals(resquestJSONObject_));	
	}
	
	
	 
	@Ignore
	@Test
	public void testInvalidUpdateUser() throws Exception { 
	
		// 1. Open Connection --- HttpURLConnection
		String url = URLs.BOOKING+"/300";
		
		JSONObject jsonObject = testExistUser();
				
		HttpURLConnection new_connection = RestClientHandler.connectServer(url, HTTPMethod.PUT,
				HTTPRequestsContentTypes.JSON);
		
		// Prepare header
		new_connection.setRequestProperty("Cookie", "token="+ jsonObject.get("token"));

		// 2. Prepare Json Object
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.UpdateUserJSONFile);
		System.out.println(resquestJSONObject);
		
		// 3. Post Request
		RestClientHandler.sendPut(new_connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		
		// 4. Reading Response
		System.out.println(new_connection.getResponseCode());
	
		assertTrue("Valid Update", new_connection.getResponseCode() == 405);
		
	}
	
	
	
	
	

}
