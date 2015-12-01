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
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

import main.java.com.jose.geolocation.exception.GeoProfileNotFoundException;
import main.java.com.jose.geolocation.service.GeolocationService;
import main.java.com.jose.geolocation.vo.GeoProfileVo;


/**
 * The Class GeolocationWs.
 */
@Path("/Geolocation")
public class GeolocationWs {

	/** The Constant log. */
	private static final Logger LOGGER = LoggerFactory.getLogger(GeolocationWs.class.getName());
	
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
	@Path("/{id}")
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
			
			json = gson.toJson(geoProfileVo);
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			return Response.serverError().status(Status.BAD_REQUEST).build();
			
		}catch (GeoProfileNotFoundException ex) {
			LOGGER.error("[GeolocationWs - loadGeoProfileById] - Error: "+ex);
			return Response.serverError().status(Status.NOT_FOUND).build();
			
		}catch(Exception ex){
			LOGGER.error("[GeolocationWs - loadGeoProfileById] - Error: "+ex);
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).build();
			
		}finally{
			LOGGER.info("[GeolocationWs - loadGeoProfileById] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}
	}
	
	/**
	 * Delete geo profile by id.
	 *
	 * @param id the id
	 * @return the response
	 */
	@GET
	@Path("/{id}/delete")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteGeoProfileById(@PathParam("id")String id){
		
		LOGGER.info("[GeolocationWs - deleteGeoProfileById] - init");
		long currentSystemTime = System.currentTimeMillis();

		try {
			if(id == null)
				throw new IllegalArgumentException("id cannot be null");
			
			geolocationService.deleteGeoProfileById(id);
			return Response.ok().build();
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			return Response.serverError().status(Status.BAD_REQUEST).build();
			
		}catch (GeoProfileNotFoundException ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			return Response.serverError().status(Status.NOT_FOUND).build();
			
		}catch (Exception ex) {
			LOGGER.error("[GeolocationWs - deleteGeoProfileById] - Error: "+ex);
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).build();
			
		}finally{
			LOGGER.info("[GeolocationWs - deleteGeoProfileById] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}
	}
	
	/**
	 * Creates the geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 * @return the response
	 */
	@POST
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGeoProfile(GeoProfileVo geoProfileVo){
		
		LOGGER.info("[GeolocationWs - createGeolocation] - init");
		long currentSystemTime = System.currentTimeMillis();

		try{
			if(geoProfileVo == null)
				throw new IllegalArgumentException("geoProfileVo cannot be null");
			
			geolocationService.createGeoProfile(geoProfileVo);
			return Response.ok().build();
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - createGeolocation] - Error: "+ex);
			return Response.serverError().status(Status.BAD_REQUEST).build();
			
		}catch (Exception ex) {
			LOGGER.error("[GeolocationWs - createGeolocation] - Error: "+ex);
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).build();
			
		}finally{
			LOGGER.info("[GeolocationWs - createGeolocation] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}

	}
	
	/**
	 * Creates the list geo profile.
	 *
	 * @param listGeoProfileVo the list geo profile vo
	 * @return the response
	 */
	@POST
	@Path("/createList")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createListGeoProfile(List<GeoProfileVo> listGeoProfileVo){
		
		LOGGER.info("[GeolocationWs - createListGeoProfile] - init");
		long currentSystemTime = System.currentTimeMillis();

		try{
			if(listGeoProfileVo == null || listGeoProfileVo.isEmpty()){
				LOGGER.error("[GeolocationService - createListGeoProfile] - geoProfileVo cannot be null or empty");
				throw new IllegalArgumentException();
			}
			
			geolocationService.createListGeoProfile(listGeoProfileVo);
			return Response.ok().build();
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - createListGeoProfile] - Error: "+ex);
			return Response.serverError().status(Status.BAD_REQUEST).build();
			
		}catch (Exception ex) {
			LOGGER.error("[GeolocationWs - createListGeoProfile] - Error: "+ex);
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).build();
			
		}finally{
			LOGGER.info("[GeolocationWs - createListGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}

	}
	
	/**
	 * Update geo profile.
	 *
	 * @param geoProfileVo the geo profile vo
	 * @return the response
	 */
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateGeoProfile(GeoProfileVo geoProfileVo){
		
		LOGGER.info("[GeolocationWs - updateGeoProfile] - init");
		long currentSystemTime = System.currentTimeMillis();
		
		try {
			if(geoProfileVo == null)
				throw new IllegalArgumentException("geoProfileVo cannot be null");
			
			geolocationService.updateGeoProfile(geoProfileVo,false);
			return Response.ok().build();
			
		}catch (IllegalArgumentException ex) {
			LOGGER.error("[GeolocationWs - updateGeoProfile] - Error: "+ex);
			return Response.serverError().status(Status.BAD_REQUEST).build();
			
		}catch (GeoProfileNotFoundException ex) {
			LOGGER.error("[GeolocationWs - updateGeoProfile] - Error: "+ex);
			return Response.serverError().status(Status.NOT_FOUND).build();
			
		}catch (Exception ex) {
			LOGGER.error("[GeolocationWs - updateGeoProfile] - Error: "+ex);
			return Response.serverError().status(Status.INTERNAL_SERVER_ERROR).build();
			
		}finally{
			LOGGER.info("[GeolocationWs - updateGeoProfile] - Finish Timing:"+(System.currentTimeMillis()-currentSystemTime));
		}

	}
}
