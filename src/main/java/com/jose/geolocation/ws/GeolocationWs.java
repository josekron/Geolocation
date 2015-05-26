package main.java.com.jose.geolocation.ws;


import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import main.java.com.jose.geolocation.exception.GeoProfileNotFoundException;
import main.java.com.jose.geolocation.exception.GeoProfileServiceException;
import main.java.com.jose.geolocation.service.GeolocationService;
import main.java.com.jose.geolocation.vo.GeoProfileVo;
import main.java.com.jose.geolocation.vo.ResponseVo;


/**
 * The Class GeolocationWs.
 */
@Path("/GeolocationWs")
public class GeolocationWs {
	
	/** The Constant OK. */
	private static final int OK=200;

	/** The Constant log. */
	private static final Logger LOGGER = Logger.getLogger(GeolocationWs.class.getName());
	
	/** Response Codes **/
	private static final Integer codeOK = 200;
	private static final Integer codeError = 500;
	
	/** The geolocation service. */
	@Inject
	GeolocationService geolocationService;

	
	/**
	 * Load geo profile by id.
	 *
	 * @param id the id
	 * @return the response
	 */
	@GET
	@Path("/loadGeoProfileById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response loadGeoProfileById(@PathParam("id")String id){
		
		LOGGER.info("[GeolocationWs - loadGeoProfileById] - init");
		long currentSystemTime = System.currentTimeMillis();
		
		String json = null;
		Gson gson = new Gson();
		GeoProfileVo geoProfileVo = new GeoProfileVo();

		try {
			if(id == null)
				throw new IllegalArgumentException("id cannot be null");
			
			geoProfileVo = geolocationService.loadGeoProfileById(id);
			geoProfileVo.setErrorCode(codeOK);
			json = gson.toJson(geoProfileVo);
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			geoProfileVo.setErrorCode(codeError);
			geoProfileVo.setErrorMessage(ex.toString());
			json = gson.toJson(geoProfileVo);
			
		}catch (GeoProfileNotFoundException ex) {
			LOGGER.error("[GeolocationWs - loadGeoProfileById] - Error: "+ex);
			geoProfileVo.setErrorCode(codeError);
			geoProfileVo.setErrorMessage(ex.toString());
			json = gson.toJson(geoProfileVo);
		}finally{
			LOGGER.info("[GeolocationWs - loadGeoProfileById] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}
		
		if(geoProfileVo.getErrorCode()==OK)
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		else
			return Response.serverError().status(geoProfileVo.getErrorCode()).entity(json).build();
	}
	
	/**
	 * Delete geo profile by id.
	 *
	 * @param id the id
	 * @return the response
	 */
	@GET
	@Path("/deleteGeoProfileById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteGeoProfileById(@PathParam("id")String id){
		
		LOGGER.info("[GeolocationWs - deleteGeoProfileById] - init");
		long currentSystemTime = System.currentTimeMillis();
		
		String json = null;
		Gson gson = new Gson();
		ResponseVo responseVo = new ResponseVo();
		
		try {
			if(id == null)
				throw new IllegalArgumentException("id cannot be null");
			
			geolocationService.deleteGeoProfileById(id);
			responseVo.setErrorCode(codeOK);
			json = gson.toJson(responseVo);
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
			
		}catch (GeoProfileNotFoundException ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
		}finally{
			LOGGER.info("[GeolocationWs - deleteGeoProfileById] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}
		

		if(responseVo.getErrorCode()==OK)
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		else
			return Response.serverError().status(responseVo.getErrorCode()).entity(json).build();
	}
	
	/**
	 * Creates the geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 * @return the response
	 */
	@POST
	@Path("/createGeoProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGeoProfile(GeoProfileVo geoProfileVo){
		
		LOGGER.info("[GeolocationWs - createGeolocation] - init");
		long currentSystemTime = System.currentTimeMillis();
		
		String json = null;
		Gson gson = new Gson();
		ResponseVo responseVo = new ResponseVo();
		try{
			if(geoProfileVo == null)
				throw new IllegalArgumentException("geoProfileVo cannot be null");
			
			geolocationService.createGeoProfile(geoProfileVo);
			responseVo.setErrorCode(codeOK);
			json = gson.toJson(responseVo);
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - createGeolocation] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
			
		}finally{
			LOGGER.info("[GeolocationWs - createGeolocation] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}
		
		if(responseVo.getErrorCode()==OK)
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		else
			return Response.serverError().status(responseVo.getErrorCode()).entity(json).build();

	}
	
	/**
	 * Creates the list geo profile.
	 *
	 * @param listGeoProfileVo the list geo profile vo
	 * @return the response
	 */
	@POST
	@Path("/createListGeoProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createListGeoProfile(List<GeoProfileVo> listGeoProfileVo){
		
		LOGGER.info("[GeolocationWs - createListGeoProfile] - init");
		long currentSystemTime = System.currentTimeMillis();
		
		String json = null;
		Gson gson = new Gson();
		ResponseVo responseVo = new ResponseVo();
		try{
			if(listGeoProfileVo == null || listGeoProfileVo.isEmpty()){
				LOGGER.error("[GeolocationService - createListGeoProfile] - geoProfileVo cannot be null or empty");
				throw new IllegalArgumentException();
			}
			
			geolocationService.createListGeoProfile(listGeoProfileVo);
			responseVo.setErrorCode(codeOK);
			json = gson.toJson(responseVo);
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - createListGeoProfile] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
			
		}finally{
			LOGGER.info("[GeolocationWs - createListGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}

		if(responseVo.getErrorCode()==OK)
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		else
			return Response.serverError().status(responseVo.getErrorCode()).entity(json).build();

	}
	
	/**
	 * Update geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 * @return the response
	 */
	@POST
	@Path("/updateGeoProfile")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateGeoProfile(GeoProfileVo geoProfileVo){
		
		LOGGER.info("[GeolocationWs - updateGeoProfile] - init");
		long currentSystemTime = System.currentTimeMillis();
		
		String json = null;
		Gson gson = new Gson();
		ResponseVo responseVo = new ResponseVo();
		
		try {
			if(geoProfileVo == null)
				throw new IllegalArgumentException("geoProfileVo cannot be null");
			
			geolocationService.updateGeoProfile(geoProfileVo,false);
			responseVo.setErrorCode(codeOK);
			json = gson.toJson(responseVo);
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - updateGeoProfile] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
			
		}catch (GeoProfileNotFoundException ex) {
			LOGGER.error("[GeolocationWs - updateGeoProfile] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
		}catch (GeoProfileServiceException ex) {
			LOGGER.error("[GeolocationWs - updateGeoProfile] - Error: "+ex);
			responseVo.setErrorCode(codeError);
			responseVo.setErrorMessage(ex.toString());
			json = gson.toJson(responseVo);
		}finally{
			LOGGER.info("[GeolocationWs - updateGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}
		
		if(responseVo.getErrorCode()==OK)
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		else
			return Response.serverError().status(responseVo.getErrorCode()).entity(json).build();

	}
}
