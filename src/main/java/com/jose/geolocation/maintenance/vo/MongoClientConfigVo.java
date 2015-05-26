package main.java.com.jose.geolocation.maintenance.vo;

/**
 * The Class MongoClientConfigVo.
 */
public class MongoClientConfigVo {
	
	/** The host. */
	private String host;
	
	/** The port. */
	private String port;
	
	/** The connections per host. */
	private Integer connectionsPerHost;
	
	/** The database. */
	private String database;
	
	/**
	 *  Constructors *.
	 */
	
	public MongoClientConfigVo(){
		super();
	}

	/**
	 * Instantiates a new mongo client config vo.
	 *
	 * @param host the host
	 * @param port the port
	 * @param connectionsPerHost the connections per host
	 * @param database the database
	 */
	public MongoClientConfigVo(String host, String port,
			Integer connectionsPerHost, String database) {
		super();
		this.host = host;
		this.port = port;
		this.connectionsPerHost = connectionsPerHost;
		this.database = database;
	}
	
	/**
	 *  Getters and Setters *.
	 *
	 * @return the host
	 */

	public String getHost() {
		return host;
	}

	/**
	 * Sets the host.
	 *
	 * @param host the new host
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * Gets the connections per host.
	 *
	 * @return the connections per host
	 */
	public Integer getConnectionsPerHost() {
		return connectionsPerHost;
	}

	/**
	 * Sets the connections per host.
	 *
	 * @param connectionsPerHost the new connections per host
	 */
	public void setConnectionsPerHost(Integer connectionsPerHost) {
		this.connectionsPerHost = connectionsPerHost;
	}

	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	public String getDatabase() {
		return database;
	}

	/**
	 * Sets the database.
	 *
	 * @param database the new database
	 */
	public void setDatabase(String database) {
		this.database = database;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MongoClientConfigVo [host=" + host + ", port=" + port
				+ ", connectionsPerHost=" + connectionsPerHost + ", database="
				+ database + "]";
	}

}
