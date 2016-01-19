package com.jaherrera.geolocation.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Embedded;


/**
 * Class that represents the location (latitude and longitude) of a place.
 */

@Embedded
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class LocationVo implements Serializable{
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1042439158555812500L;
	
	/** The location name. */
	private String locationName;
	
	/** The country name. */
	private String countryName;
	
	/** The code country. */
	private String codeCountry;
	
	/** The latitude. */
	private Double latitude;
	
	/** The longitude. */
	private Double longitude;
	
	/**
	 *  Constructors *.
	 *
	 * @param locationName the location name
	 * @param countryName the country name
	 * @param codeCountry the code country
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	
	public LocationVo(String locationName, String countryName, String codeCountry, Double latitude, Double longitude) {
		super();
		this.locationName = locationName;
		this.countryName = countryName;
		this.codeCountry = codeCountry;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Instantiates a new location vo.
	 */
	public LocationVo(){
		super();
	}

	/**
	 *  Getters and Setters *.
	 *
	 * @return the location name
	 */
	
	public String getLocationName() {
		return locationName;
	}

	/**
	 * Sets the location name.
	 *
	 * @param locationName the new location name
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * Gets the country name.
	 *
	 * @return the country name
	 */
	public String getCountryName() {
		return countryName;
	}

	/**
	 * Sets the country name.
	 *
	 * @param countryName the new country name
	 */
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	/**
	 * Gets the code country.
	 *
	 * @return the code country
	 */
	public String getCodeCountry() {
		return codeCountry;
	}

	/**
	 * Sets the code country.
	 *
	 * @param codeCountry the new code country
	 */
	public void setCodeCountry(String codeCountry) {
		this.codeCountry = codeCountry;
	}

	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationVo [locationName=" + locationName + ", countryName="
				+ countryName + ", codeCountry=" + codeCountry + ", latitude="
				+ latitude + ", longitude=" + longitude + "]";
	}
	
	
	
	
}
