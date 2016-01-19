package com.jaherrera.geolocation.listener;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class Log4jConfig
 *
 */
@WebListener
public class Log4jConfig implements ServletContextListener {

	
	private final static String LO4G_FILENAME="/opt/share/geolocation/config/log4j.properties"; 
	private static final Logger LOGGER = LoggerFactory.getLogger(Log4jConfig.class);
		
    /**
     * Default constructor. 
     */
    public Log4jConfig()
    {

    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)
    { 

    	try{
    		File file = new File(LO4G_FILENAME);
    		if (file.exists())
    		{
    			PropertyConfigurator.configure(LO4G_FILENAME);
    			LOGGER.info("[Log4JInitServlet - contextInitialized] - Log4J configurado:"+LO4G_FILENAME);
    		}

    	}catch (Exception e) {
    		LOGGER.error("[Log4JInitServlet - contextInitialized] - Exception >>",e);
    	}

    }

}
