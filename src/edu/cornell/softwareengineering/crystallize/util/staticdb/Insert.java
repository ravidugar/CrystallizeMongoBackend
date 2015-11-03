package edu.cornell.softwareengineering.crystallize.util.staticdb;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import edu.cornell.softwareengineering.crystallize.util.common.MongoDBClient;

public class Insert {
	public static String insert(JSONObject parameters) throws Exception {
		String collection;
		JSONArray documents;
		try {
			collection = parameters.getString("collection");
			documents = parameters.getJSONArray("document");
		} catch (JSONException e) {
			throw new Exception("Parameter error inside Insert class");
		}
		
		DBCollection coll = MongoDBClient.getCollection(collection);
		
    	// Inserts object
		DBObject[] documentObjs = new DBObject[documents.length()];
		for(int i = 0; i < documents.length(); i++) { 
			documentObjs[i] = (DBObject) JSON.parse(documents.getJSONObject(i).toString()); 
		}
		
    	coll.insert(documentObjs);
    	
    	return "Successfully added object(s)";
	}
}