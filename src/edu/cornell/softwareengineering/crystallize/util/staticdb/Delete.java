package edu.cornell.softwareengineering.crystallize.util.staticdb;

import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;

import edu.cornell.softwareengineering.crystallize.util.common.MongoDBClient;

public class Delete {
	public static String delete(JSONObject parameters) {
		MongoClient mongoClient = MongoDBClient.getMongoClient();
		
		String collection;
		JSONObject query;
		
		try { 
			collection = parameters.getString("collection"); 
		} catch(JSONException e) {
			collection = "";
		}
		
		try { 
			query = parameters.getJSONObject("query"); 
		} catch(JSONException e) {
			return "No deleting query given";
		}
		
		// Now connect to your databases
		DB db = mongoClient.getDB( "testDb" );
		System.out.println("Connect to database successfully");

		// Now connect to collection
		boolean collectionExists = db.collectionExists(collection);
		if (collectionExists == false) {
		    return "No collection " + collection;
	    }
		DBCollection table = db.getCollection(collection);
		
		
    	// Deletes objects based on query
    	DBObject deleteQuery = (DBObject) JSON.parse(query.toString());

    	table.remove(deleteQuery);
    	
    	return "Successfully deleted object";
	}
	
	// Converts an HTTP parameter mapping to a DBObject
	private static QueryBuilder getParameterObject(Map<String, String[]> parameterMap) {
		QueryBuilder parameterObj = new QueryBuilder();
		
		Iterator parameterIter = parameterMap.entrySet().iterator();
		while(parameterIter.hasNext()) {
			Map.Entry<String, String[]> pair = (Map.Entry<String, String[]>)parameterIter.next();
			String key = pair.getKey();
			String[] values = pair.getValue();
			parameterObj.put(key).in(values);
		}
		return parameterObj;
	}
}
