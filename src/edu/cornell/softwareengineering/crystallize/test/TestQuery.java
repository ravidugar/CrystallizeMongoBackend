package edu.cornell.softwareengineering.crystallize.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestQuery {
	final static String queryURL = "http://localhost:8080/CrystallizeBackend/Query";
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String parameters = "name=" + URLEncoder.encode("ravi", "UTF-8");
		System.out.println(HTTPConnection.excutePost(queryURL, parameters));
	}
}
