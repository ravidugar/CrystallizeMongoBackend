package edu.cornell.softwareengineering.crystallize.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class TestQuery {
	final static String queryURL = "http://localhost:8080/CrystallizeBackend/Query";
	
	public static void main(String[] args) throws UnsupportedEncodingException, JSONException {
		JSONObject query = new JSONObject();
		query.put("name", "peter");
		
		String parameters = "query=" + URLEncoder.encode("peter", "UTF-8");
		//System.out.println(HTTPConnection.excutePost(queryURL, parameters));
	}
}
