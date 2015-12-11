package main.java.com.jose.geolocation.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.Morphia;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;

import main.java.com.jose.geolocation.dao.GeoProfileDAO;
import main.java.com.jose.geolocation.exception.GeoProfileNotFoundException;
import main.java.com.jose.geolocation.exception.GeoProfileServiceException;
import main.java.com.jose.geolocation.listener.MongoClientManagerFactory;
import main.java.com.jose.geolocation.vo.GeoProfileVo;


/**
 * The Class GeolocationService.
 */
public class GeolocationService implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2071937170723089158L;
	
	/** The Constant log. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeolocationService.class.getName());
	
	private MongoClient mongoClient;
	private Morphia morphia;
	private GeoProfileDAO geoProfileDAO;
	
	
	/**
	 * Instantiates a new geolocation service.
 	*/
	public GeolocationService(){
		super();
		LOGGER.info("[GeolocationService - Constructor] - init");
		
		mongoClient = MongoClientManagerFactory.getMongoClientManager();
		morphia = new Morphia();
		morphia.map(GeoProfileVo.class);
		geoProfileDAO = new GeoProfileDAO(morphia, mongoClient, MongoClientManagerFactory.getDatabase());
	}
	
	/**
	 * Load geo profile by id.
	 *
	 * @param id the id
	 * @return the geo profile vo
	 * @throws GeoProfileNotFoundException the geo profile not found exception
	 */
	public GeoProfileVo loadGeoProfileById(String id)  throws GeoProfileNotFoundException{
		LOGGER.info("[GeolocationService - loadGeoProfileById] - init");
		long currentSystemTime=System.currentTimeMillis();
		
		if(id == null){
			LOGGER.error("[GeolocationService - loadGeoProfileById] - id cannot be null");
			throw new IllegalArgumentException();
		}
		GeoProfileVo geoProfileVo = geoProfileDAO.findById(id);
		
		if(geoProfileVo == null){
			LOGGER.error("[GeolocationService - loadGeoProfileById] - geoProfileVo not found");
			throw new GeoProfileNotFoundException();
		}
		
		LOGGER.debug("[GeolocationService - loadGeoProfileById] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		return geoProfileVo;
	}
	
	/**
	 * Load all geo profile.
	 * @return the geo profile vo list
	 */
	public List<GeoProfileVo> loadAllGeoProfile() {
		LOGGER.info("[GeolocationService - loadAllGeoProfile] - init");
		long currentSystemTime=System.currentTimeMillis();

		List<GeoProfileVo> geoProfileVoList = geoProfileDAO.findAll();
		
		LOGGER.debug("[GeolocationService - loadAllGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		return geoProfileVoList;
	}
	
	/**
	 * Delete geo profile by id.
	 *
	 * @param id the id
	 * @throws GeoProfileNotFoundException the geo profile not found exception
	 */
	public void deleteGeoProfileById(String id) throws GeoProfileNotFoundException{
		LOGGER.info("[GeolocationService - deleteGeoProfileById] - init");
		long currentSystemTime=System.currentTimeMillis();
		
		if(id == null){
			LOGGER.error("[GeolocationService - deleteGeoProfileById] - id cannot be null");
			throw new IllegalArgumentException();
		}
		
		LOGGER.info("[GeolocationService - deleteGeoProfileById] - deleting geoProfileVo id: {}",id);
		geoProfileDAO.deleteById(id);

		LOGGER.debug("[GeolocationService - deleteGeoProfileById] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));

	}
	
	/**
	 * Creates the geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 */
	public void createGeoProfile(GeoProfileVo geoProfileVo){
		LOGGER.info("[GeolocationService - createGeoProfile] - init");
		long currentSystemTime=System.currentTimeMillis();
		
		if(geoProfileVo == null){
			LOGGER.error("[GeolocationService - createGeoProfile] - geoProfileVo cannot be null");
			throw new IllegalArgumentException();
		}
		
		geoProfileVo.setDateCreation(new Date(System.currentTimeMillis()));
		geoProfileDAO.saveGeoProfile(geoProfileVo);
		
		LOGGER.debug("[GeolocationService - createGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
	}
	
	
	/**
	 * Creates the list geo profile.
	 *
	 * @param listGeoProfileVo the list geo profile vo
	 */
	public void createListGeoProfile(List<GeoProfileVo> geoProfileVoList){
		LOGGER.info("[GeolocationService - createListGeoProfile] - init");
		long currentSystemTime=System.currentTimeMillis();
		
		if(geoProfileVoList == null || geoProfileVoList.isEmpty()){
			LOGGER.error("[GeolocationService - createListGeoProfile] - geoProfileVo cannot be null or empty");
			throw new IllegalArgumentException();
		}
		
		LOGGER.info("[GeolocationService - createListGeoProfile] - Creating listGeoProfileVo size:{}",geoProfileVoList.size());
		
		for(GeoProfileVo geo : geoProfileVoList){
			geo.setDateCreation(new Date(System.currentTimeMillis()));
		}		
		geoProfileDAO.saveGeoProfiles(geoProfileVoList);
		
		LOGGER.debug("[GeolocationService - createListGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
	}
	
	
	/**
	 * Update geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 * @param copyIfNull the copy if null
	 * @throws GeoProfileNotFoundException the geo profile not found exception
	 * @throws GeoProfileServiceException the geo profile service exception
	 */
	public void updateGeoProfile(GeoProfileVo geoProfileVo, Boolean copyIfNull) throws GeoProfileNotFoundException, GeoProfileServiceException{
		LOGGER.info("[GeolocationService - updateGeoProfile] - init");
		long currentSystemTime=System.currentTimeMillis();
		
		if(geoProfileVo == null){
			LOGGER.error("[GeolocationService - updateGeoProfile] - geoProfileVo cannot be null");
			throw new IllegalArgumentException();
		}
		if(geoProfileVo.getId() == null){
			LOGGER.error("[GeolocationService - updateGeoProfile] - objectId cannot be null");
			throw new IllegalArgumentException();
		}
		if(copyIfNull == null){
			LOGGER.error("[GeolocationService - updateGeoProfile] - copyIfNull cannot be null");
			throw new IllegalArgumentException();
		}
		
		GeoProfileVo geoProfileVoEntity = geoProfileDAO.findById(geoProfileVo.getId());
		if(geoProfileVoEntity==null){
			LOGGER.error("[GeolocationService - updateGeoProfile] - geoProfileVo not found in BD");
			throw new GeoProfileNotFoundException();
		}
		
		LOGGER.info("[GeolocationService - updateGeoProfile] - updating geoProfileVo id:{}",geoProfileVo.getId());
		geoProfileVo.setDateModification(new Date(System.currentTimeMillis()));
		geoProfileDAO.updateGeoProfile(geoProfileVo, copyIfNull);
		
		LOGGER.debug("[GeolocationService - updateGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		
	}	

}
