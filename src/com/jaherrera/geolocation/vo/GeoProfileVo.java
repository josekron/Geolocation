package com.jaherrera.geolocation.vo;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

/**
 * The Class GeoProfileVo.
 */
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class GeoProfileVo implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4975628041316056892L;

	/** The id. */
	@Id private String id;
	
	/** The email. */
	private String email;
	
	/** The location. */
	private Double[] coordinates;
	
	
	/** The date creation. */
	private Date dateCreation;
	
	/** The date modification. */
	private Date dateModification;
	
	/**
	 *  Constructors *.
	 */
	
	public GeoProfileVo(){
		super();
	}

	/**
	 * Instantiates a new geo profile vo.
	 *
	 * @param id the id
	 * @param email the email
	 * @param location the location

	 * @param dateCreation the date creation
	 * @param dateModification the date modification
	 */
	public GeoProfileVo(String id, String email, Double[] coordinates,Date dateCreation, Date dateModification) {
		super();
		this.id = id;
		this.email = email;
		this.coordinates = coordinates;
		this.dateCreation = dateCreation;
		this.dateModification = dateModification;
	}

	/**
	 *  Getters and Setters *.
	 *
	 * @return the id
	 */
	
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}


	public Double[] getCoordinates() {
		return coordinates;
	}

	
	public void setCoordinates(Double[] coordinates) {
		this.coordinates = coordinates;
	}

	/**
	 * Gets the date creation.
	 *
	 * @return the date creation
	 */
	public Date getDateCreation() {
		return dateCreation;
	}

	/**
	 * Sets the date creation.
	 *
	 * @param dateCreation the new date creation
	 */
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

	/**
	 * Gets the date modification.
	 *
	 * @return the date modification
	 */
	public Date getDateModification() {
		return dateModification;
	}

	/**
	 * Sets the date modification.
	 *
	 * @param dateModification the new date modification
	 */
	public void setDateModification(Date dateModification) {
		this.dateModification = dateModification;
	}


	@Override
	public String toString() {
		return "GeoProfileVo [id=" + id + ", email=" + email + ", coordinates="
				+ coordinates + ", dateCreation=" + dateCreation
				+ ", dateModification=" + dateModification + "]";
	}

	

}
