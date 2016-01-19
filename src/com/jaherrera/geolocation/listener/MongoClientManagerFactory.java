package com.jaherrera.geolocation.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.jaherrera.geolocation.maintenance.vo.MongoClientConfigVo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;

/**
 * A factory for creating MongoClientManager objects.
 */
@WebListener
public class MongoClientManagerFactory {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(MongoClientManagerFactory.class.getName());
	
	/** The mongo client. */
	private static MongoClient mongoClient;
	
	/** The database. */
	private static String database;

	/**
	 * Instantiates a new mongo client manager factory.
	 */
	public MongoClientManagerFactory(){
		LOGGER.debug("[MongoClientManagerFactory] >> Constructor");
	}
	
	/**
	 * Context destroyed.
	 *
	 * @param arg0 the arg0
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		
		mongoClient.close();
		LOGGER.debug("[MongoClientManagerFactory] >> contextDestroyed. Close createMongoClientManager");	
	}
	
	/**
	 * Context initialized.
	 *
	 * @param arg0 the arg0
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		LOGGER.debug("[GeolocationDaoManagerFactory.contextInitialized] >> Init");
    	long currentSystemTime=System.currentTimeMillis();

    	try{
    		
    		mongoClient = getMongoClientManager();
    		
    		LOGGER.debug("[MongoClientManagerFactory.contextInitialized] >> Initializes getMongoClientManager");

    	}catch(Exception e){
    		LOGGER.error("[MongoClientManagerFactory.contextInitialized] >> Exception",e);

    	}finally{
    		LOGGER.debug("[MongoClientManagerFactory.contextInitialized] >> Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
    	}
		
	}
	
	/**
	 * Gets the mongo client manager.
	 *
	 * @return the mongo client manager
	 */
	public static MongoClient getMongoClientManager()
    {  
    	long currentSystemTime=System.currentTimeMillis();
    	
    	if(mongoClient==null){
    		try{
        		//mongoClient = new MongoClient("localhost",27017);
        		//MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
    			MongoClientConfigVo mongoClientConfigVo = new MongoClientConfigVo();
    			
    			mongoClientConfigVo.setHost("localhost");
    			mongoClientConfigVo.setPort("27017");
    			mongoClientConfigVo.setConnectionsPerHost(200);
    			mongoClientConfigVo.setDatabase("mydb");
    			
    			database = mongoClientConfigVo.getDatabase();
    			
    			MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(mongoClientConfigVo.getConnectionsPerHost()).build();
    			
    			String mongoClientURI = mongoClientConfigVo.getHost()+":"+mongoClientConfigVo.getPort();
        		mongoClient = new MongoClient(mongoClientURI, options);
        		
        		LOGGER.debug("[MongoClientManagerFactory.contextInitialized] >> Initializes getMongoClientManager");

        	}catch(Exception e){
        		LOGGER.error("[MongoClientManagerFactory.contextInitialized] >> Exception",e);

        	}finally{
        		LOGGER.debug("[MongoClientManagerFactory.contextInitialized] >> Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
        	}
    	}
    	
    	if (mongoClient == null)  
    		throw new IllegalStateException("Context is not initialized yet.");

    	LOGGER.debug("[MongoClientManagerFactory.createMongoClientManager] >> Creating Mongo Client Manager. Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
    	return mongoClient;
    }  
	
	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	public static String getDatabase(){
		return database;
	}
	
}
