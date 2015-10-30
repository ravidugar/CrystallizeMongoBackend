package edu.cornell.softwareengineering.crystallize.util.staticdb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.QueryBuilder;

public class Query {
	public static String queryTerms(String collectionName, Map<String, String[]> queryTerms) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		// Now connect to your databases
		DB db = mongoClient.getDB( "testDb" );
		System.out.println("Connect to database successfully");

		// Now connect to collection
		boolean collectionExists = db.collectionExists(collectionName);
		if (collectionExists == false){
		    return "No collection " + collectionName;
	    }
		DBCollection table = db.getCollection(collectionName);
		
		// Query
    	DBObject searchQuery = getParameterObject(queryTerms).get();
    	
    	DBCursor cursor = table.find(searchQuery);
    	String result;
    	if (cursor.hasNext()) {
    		result = cursor.toArray().toString();
    	}
    	else {
    		result = "No results";
    	}
		mongoClient.close();
		return result;
	}
	
	// For testing purposes
	public static String query(String collectionName) {
		Map<String, String[]> queryTerms = new HashMap<String, String[]>();
		String[] values = {"prateek"};
		queryTerms.put("name", values);
		
		return queryTerms(collectionName, queryTerms);
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
