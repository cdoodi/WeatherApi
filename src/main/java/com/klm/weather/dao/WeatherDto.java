package com.klm.weather.dao;

import java.util.Date;
import java.util.List;

public class WeatherDto {

	private Integer id;

	private Date date;

	private Float lat;

	private Float lon;

	private String city;

	private String state;

	private List<Double> temperatures;

	/**
	 * @param date
	 * @param lat
	 * @param lon
	 * @param city
	 * @param state
	 * @param temperatures
	 */
	public WeatherDto(Date date, Float lat, Float lon, String city, String state, List<Double> temperatures) {
		this.date = date;
		this.lat = lat;
		this.lon = lon;
		this.city = city;
		this.state = state;
		this.temperatures = temperatures;
	}

	public WeatherDto() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Double> getTemperatures() {
		return temperatures;
	}

	public void setTemperatures(List<Double> temperatures) {
		this.temperatures = temperatures;
	}

}
