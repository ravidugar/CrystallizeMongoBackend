package edu.cornell.softwareengineering.crystallize.test;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestInsert {
	final static String insertURL = "http://localhost:8080/CrystallizeBackend/Insert";

	public static void main(String[] args) throws JSONException, IOException {		
		complexTest();
	}
	
	public static void basicTest() throws JSONException, IOException {
		JSONObject document = new JSONObject();
		document.put("name", "BIGTEST");
		document.put("grade", "F");
		System.out.println(document.toString());
		
		JSONObject parameters = new JSONObject();
		parameters.append("document", document.toString());
		parameters.append("collection", "TestInsert");
		System.out.println(parameters.toString());
		
//		//String parameters = "query=" + URLEncoder.encode("peter", "UTF-8") + 
//				"&grade=" + URLEncoder.encode("A-", "UTF-8");
//		System.out.println(HTTPConnection.excutePost(insertURL, "parameters=" + parameters.toString())); 	
		
		HTTPConnection.excutePost(insertURL, parameters.toString());
	}
	
	public static void complexTest() throws JSONException, IOException {
		JSONObject name = new JSONObject();
		name.put("firstname", "peter");
		name.put("lastname", "baker");
		
		JSONArray grades = new JSONArray();
		grades.put("A-");
		grades.put("C+");
		grades.put("B+");
		grades.put("B-");
		System.out.println(grades.toString());
		
		JSONObject document = new JSONObject();
		document.put("name", name);
		document.put("grades", grades);
		System.out.println(document.toString());
		
		JSONObject parameters = new JSONObject();
		parameters.append("document", document);
		parameters.append("collection", "TestInsert");
		System.out.println(parameters.toString());
		
		HTTPConnection.excutePost(insertURL, parameters.toString());
	}
	
	
}
