package edu.cornell.softwareengineering.crystallize.util.staticdb;

import java.util.Iterator;
import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class Insert {
	public static String insert(String collectionName, Map<String, String[]> objectParameters) {
		MongoClient mongoClient = new MongoClient("localhost", 27017);
		
		// Now connect to your databases
		DB db = mongoClient.getDB( "testDb" );
		System.out.println("Connect to database successfully");

		// Now connect to collection
		boolean collectionExists = db.collectionExists(collectionName);
		if (collectionExists == false) {
		    return "No collection " + collectionName;
	    }
		DBCollection table = db.getCollection(collectionName);
		
    	// Inserts object
    	BasicDBObject newObject = getParameterObject(objectParameters);
    	table.insert(newObject);
    	
    	return "Successfully added object";
	}
	
	// Converts an HTTP parameter mapping to a DBObject
	private static BasicDBObject getParameterObject(Map<String, String[]> parameterMap) {
		BasicDBObject parameterObj = new BasicDBObject();
		Iterator parameterIter = parameterMap.entrySet().iterator();
		while(parameterIter.hasNext()) {
			Map.Entry<String, String[]> pair = (Map.Entry<String, String[]>)parameterIter.next();
			String key = pair.getKey();
			String[] values = pair.getValue();
			for(String value : values) {
				parameterObj.append(key, value);
			}
		}
		return parameterObj;
	}
}
