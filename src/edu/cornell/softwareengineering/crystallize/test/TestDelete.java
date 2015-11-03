package edu.cornell.softwareengineering.crystallize.test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class TestDelete {
	final static String deleteURL = "http://localhost:8080/CrystallizeBackend/Delete";
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		String parameters = "name=" + URLEncoder.encode("peter", "UTF-8");
		//System.out.println(HTTPConnection.excutePost(deleteURL, parameters));
	}
}
