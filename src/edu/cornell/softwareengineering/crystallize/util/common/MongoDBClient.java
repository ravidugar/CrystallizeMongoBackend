package edu.cornell.softwareengineering.crystallize.util.common;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBClient {
	
	public static MongoClient getMongoClient() {
		if(mongoClient == null) {
			mongoClient = new MongoClient("localhost", 27017);
		}
		return mongoClient;
	}
	
	public static void closeMongoClient() {
		if(mongoClient != null) {
			mongoClient.close();
			mongoClient = null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static DB getDatabase() {
		if(database == null) {
			database = getMongoClient().getDB("Test");
		}
		return database;
	}
	
	public static DBCollection getCollection(String collection) {
		DB db = getDatabase();
		// Now connect to collection
		boolean collectionExists = db.collectionExists(collection);
		DBCollection coll;
		if (!collectionExists) {
		    coll = db.createCollection(collection, new BasicDBObject());
	    }
		else {
			coll = db.getCollection(collection);
		}
		
		return coll;
	}
	private static MongoClient mongoClient;
	private static DB database;
}
