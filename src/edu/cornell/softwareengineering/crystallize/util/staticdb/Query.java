package edu.cornell.softwareengineering.crystallize.util.staticdb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.bson.BSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;
import com.mongodb.util.JSON;

import edu.cornell.softwareengineering.crystallize.util.common.MongoDBClient;

public class Query {
	public static String queryTerms(JSONObject parameters) {
		MongoClient mongoClient = MongoDBClient.getMongoClient();
		
		String collection;
		JSONObject query;
		JSONArray filters;
		
		try { 
			collection = parameters.getString("collection"); 
		} catch(JSONException e) {
			collection = "";
		}
		
		try { 
			query = parameters.getJSONObject("query"); 
		} catch(JSONException e) {
			query = new JSONObject();
		}
		
		try { 
			filters = parameters.getJSONArray("filters"); 
		} catch(JSONException e) {
			filters = new JSONArray();
		}
		
		// Now connect to your databases
		DB db = mongoClient.getDB( "testDb" );
		System.out.println("Connect to database successfully");

		// Now connect to collection
		boolean collectionExists = db.collectionExists(collection);
		if (collectionExists == false){
			mongoClient.close();
		    return "No collection " + collection;
	    }
		DBCollection table = db.getCollection(collection);
		
		// Query
    	DBObject queryObj = (DBObject) JSON.parse(query.toString());
    	DBObject filtersObj = getFilters(filters);
    	DBCursor cursor = table.find(queryObj, filtersObj);
    	String result;
    	if (cursor.length() > 0) {
    		result = cursor.toArray().toString();
    	}
    	else {
    		result = "No results";
    	}
		mongoClient.close();
		return result;
	}
	
//	// For testing purposes
//	public static String query(String collection) {
//		Map<String, String[]> queryTerms = new HashMap<String, String[]>();
//		String[] values = {"prateek"};
//		queryTerms.put("name", values);
//		
//		return queryTerms(collection, queryTerms);
//	}
	
	private static DBObject getFilters(JSONArray filters) {
		DBObject filterObj = new BasicDBObject();
		
		for(int i = 0; i < filters.length(); i++) {
			try {
				filterObj.put((String) filters.get(i), new Integer(1));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				return null;
			}
		}
		
		return filterObj;
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
