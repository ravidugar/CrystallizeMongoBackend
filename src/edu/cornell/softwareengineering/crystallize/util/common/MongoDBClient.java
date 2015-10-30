package edu.cornell.softwareengineering.crystallize.util.common;

import com.mongodb.DB;
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
	
	
	private static MongoClient mongoClient;
	private static DB database;
	
}
