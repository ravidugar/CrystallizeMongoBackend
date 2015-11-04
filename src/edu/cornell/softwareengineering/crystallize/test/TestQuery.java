package edu.cornell.softwareengineering.crystallize.test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestQuery {
	final static String queryURL = "http://localhost:8080/CrystallizeBackend/Query";
	
	public static void main(String[] args) throws JSONException, IOException {
		basicTest();
		testFilters();
	}
	
	public static void basicTest() throws JSONException, IOException {
		JSONObject query = new JSONObject();
		query.put("name.firstname", "peter");
		
		JSONObject parameters = new JSONObject();
		parameters.append("query", query.toString());
		parameters.append("collection", "TestInsert");
		parameters.append("filters", (new JSONArray()).toString());
		System.out.println(parameters.toString());
		
		HTTPConnection.excutePost(queryURL, parameters.toString());
	}
	
	public static void testFilters() throws JSONException, IOException {
		JSONObject query = new JSONObject();
		query.put("name.firstname", "peter");
		System.out.println(query.toString());
		
		JSONArray filters = new JSONArray();
		filters.put("grades");
		
		JSONObject parameters = new JSONObject();
		parameters.append("query", query.toString());
		parameters.append("collection", "TestInsert");
		parameters.append("filters", filters.toString());
		System.out.println(parameters.toString());
		
		HTTPConnection.excutePost(queryURL, parameters.toString());
	}
}
