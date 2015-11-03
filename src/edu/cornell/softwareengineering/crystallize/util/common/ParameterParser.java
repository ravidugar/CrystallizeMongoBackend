package edu.cornell.softwareengineering.crystallize.util.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParameterParser {
	public static JSONObject getParameterObject(HttpServletRequest request) throws IOException, JSONException {
		StringBuffer jb = new StringBuffer();
		String line = null;
		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null)
		jb.append(line);

		JSONObject parameters = new JSONObject(jb.toString());
		return parameters;
	}
	
	public static JSONObject parseInsert(JSONObject parameters) throws Exception {
		JSONObject refined = new JSONObject();
		if(parameters.length() == 0) throw new Exception("No parameters found");
		
		// check collection parameter
		if(parameters.has("collection")) {
			JSONArray collectionArray = parameters.getJSONArray("collection");
			if(collectionArray.length() == 1) {
				refined.put("collection", collectionArray.get(0));
			}
			else if(collectionArray.length() > 1) { throw new Exception("Parameters 'collection' has more than one value"); }
			else { throw new Exception("Parameters 'collection' is empty"); }
		}
		else { throw new Exception("Parameter 'collection' missing"); }
		
		// check document parameter
		if(parameters.has("document")) {
			JSONArray documentArray = parameters.getJSONArray("document");
			if(documentArray.length() > 0) {
				refined.put("document", documentArray);
			}
			else { throw new Exception("Parameters 'document' is empty"); }		}
		else { throw new Exception("Parameter 'document' missing"); }
		
		return refined;
	}	
}
