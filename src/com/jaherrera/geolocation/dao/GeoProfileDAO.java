package com.jaherrera.geolocation.dao;

import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jaherrera.geolocation.exception.GeoProfileNotFoundException;
import com.jaherrera.geolocation.exception.GeoProfileServiceException;
import com.jaherrera.geolocation.vo.GeoProfileVo;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

/**
 * The Class GeoProfileDAO.
 */
public class GeoProfileDAO extends BasicDAO<GeoProfileVo, ObjectId>{
	
	/** The Constant log. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeoProfileDAO.class.getName());

	/**
	 * Instantiates a new geo profile dao.
	 *
	 * @param morphia the morphia
	 * @param mongoClient the mongo client
	 * @param db the db
	 */
	public GeoProfileDAO(Morphia morphia, MongoClient mongoClient, String db) {
		super(mongoClient, morphia, db);
	}
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the geo profile vo
	 */
	public GeoProfileVo findById (String id){
		LOGGER.info("[GeoProfileDAO - findById] - init");
		
		ObjectId oid = new ObjectId(id);
		return getDs().find(GeoProfileVo.class).field("id").equal(oid).disableValidation().get();
	}
	
	/**
	 * Find All.
	 * @return the geo profile vo List
	 */
	public List<GeoProfileVo> findAll (){
		LOGGER.info("[GeoProfileDAO - findAll] - init");

		return getDs().find(GeoProfileVo.class).asList();
	}
	
	/**
	 * Delete by id.
	 *
	 * @param id the id
	 * @throws GeoProfileNotFoundException the geo profile not found exception
	 */
	public void deleteById (String id) throws GeoProfileNotFoundException{
		LOGGER.info("[GeoProfileDAO - deleteById] - init");
		
		ObjectId oid = new ObjectId(id);
		Query<GeoProfileVo> deleteQuery = getDs().createQuery(GeoProfileVo.class).field("id").equal(oid);
		deleteQuery.disableValidation();
		WriteResult wr = getDs().delete(deleteQuery);
		if(wr.getN()==0){
			LOGGER.error("[GeoProfileDAO - deleteById] - GeoProfileNotFoundException");
			throw new GeoProfileNotFoundException();
		}
	}
	
	/**
	 * Save geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 */
	public void saveGeoProfile(GeoProfileVo geoProfileVo){
		LOGGER.info("[GeoProfileDAO - saveGeoProfile] - init");
		getDs().save(geoProfileVo);
	}
	
	/**
	 * Save list geo profile.
	 *
	 * @param listGeoProfileVo the list geo profile vo
	 */
	public void saveGeoProfiles(List<GeoProfileVo> listGeoProfileVo){
		LOGGER.info("[GeoProfileDAO - saveGeoProfiles] - init");
		getDs().save(listGeoProfileVo);
	}
	
	/**
	 * Update geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 * @param copyIfNull the copy if null
	 * @throws GeoProfileServiceException the geo profile service exception
	 */
	public void updateGeoProfile(GeoProfileVo geoProfileVo, Boolean copyIfNull)throws GeoProfileServiceException{
		LOGGER.info("[GeoProfileDAO - updateGeoProfile] - init");
		
		ObjectId oid = new ObjectId(geoProfileVo.getId());
		UpdateOperations<GeoProfileVo> ops = getOpsUpdate(geoProfileVo, copyIfNull);
		Query<GeoProfileVo> updateQuery = getDs().createQuery(GeoProfileVo.class).field("id").equal(oid);
		updateQuery.disableValidation();
		UpdateResults ur = getDs().update(updateQuery, ops);
		if(!ur.getUpdatedExisting()){
			LOGGER.error("[GeoProfileDAO - updateGeoProfile] - GeoProfileServiceException - geoProfile not updated");
			throw new GeoProfileServiceException();
		}
	}
	
	/**
	 * Gets the ops update.
	 *
	 * @param source the source
	 * @param copyIfNull the copy if null
	 * @return the ops update
	 */
	private UpdateOperations<GeoProfileVo> getOpsUpdate(GeoProfileVo source, Boolean copyIfNull){
		LOGGER.info("[GeoProfileDAO - getOpsUpdate] - init");
		
		UpdateOperations<GeoProfileVo> ops = getDs().createUpdateOperations(GeoProfileVo.class);
		
		if(copyIfNull || source.getEmail()!=null)
			ops.set("email", source.getEmail());
		if(copyIfNull || source.getLocation()!=null)
			ops.set("location", source.getLocation());
		if(copyIfNull || source.getDateCreation()!=null)
			ops.set("dateCreation",source.getDateCreation());
		if(copyIfNull || source.getDateModification()!=null)
			ops.set("dateModification",source.getDateModification());
		return ops;
	}
}
