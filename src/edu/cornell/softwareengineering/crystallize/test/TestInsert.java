package edu.cornell.softwareengineering.crystallize.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestInsert {
	final static String insertURL = "http://localhost:8080/CrystallizeBackend/Insert";

	public static void main(String[] args) throws UnsupportedEncodingException {
		String parameters = "name=" + URLEncoder.encode("ravi", "UTF-8") + 
				"&grade=" + URLEncoder.encode("A+", "UTF-8");
		System.out.println(HTTPConnection.excutePost(insertURL, parameters));
	}
}
