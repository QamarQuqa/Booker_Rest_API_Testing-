package TestClasses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.Ignore;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.HandleRequestReponse;
import requestHandling.RestClientHandler;

public class TestGetRestAPI {

	
	@Ignore
	@Test
	public void TestReqRes()  { //overview about users
		try
		{
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.ReqResAPI, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		//2. Send Post Request
		RestClientHandler.sendGet(connection, "", HTTPRequestsContentTypes.JSON);
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	
	
	
	@Ignore
	@Test
	public void TestRestClientHandler() throws IOException {
		// return all booking id 
		
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("Data is empty", !response.equals(""));
	}
	
	
	
	
	
		
	@Ignore
	@Test
	public void GetBookingIdExist() throws IOException {
		
		// get url with exist id 
		String url = URLs.BOOKING+"/1";
		System.out.println(url);

		
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);

		assertTrue("Not Found", !response.equals("Not Found"));
	}
		
	
	
	@Ignore
	@Test
	public void GetBookingIdNotExist() throws IOException {

		// get url with exist id 
		String url = URLs.BOOKING+"/100";
		System.out.println(url);
		
		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("connect to webservice successfully_ID exist", connection.getResponseCode() == 404);
	}
	
	
	
	@Ignore
	@Test
	public void FilterByNameExist() throws IOException , Exception{
		
		// get url with exist id 
		String url = URLs.BOOKING+"?firstname=Jim&lastname=Smith";
		System.out.println(url);

		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		
		JSONArray jsonArr = (JSONArray) JSONUtils.convertStringToJSON(response);
	
		assertTrue("Name Not Valid", !jsonArr.isEmpty());
	}
	
	
		
	//@Ignore
	@Test
	public void FilterByNameNotExist() throws IOException, Exception{
		
		// get url with exist id 
		String url = URLs.BOOKING+"?firstname=Mary&lastname=Wilmson";
		System.out.println(url);

		// 1. connect to server and open connection (get HttpURLConnection object)
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		// 2. validate if the connection is successfully openned
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("unable to connect to webservice", connection.getResponseCode() == 200);
		
		// 3. reading response using input stream
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		
		JSONArray jsonArr = (JSONArray) JSONUtils.convertStringToJSON(response);

		assertTrue("Name Exist", jsonArr.isEmpty() == true);
	}
	
}
