package com.klm.weather.repository;

import com.klm.weather.model.Weather;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {
	
	public List<Weather> findByDate(Date date);
	public List<Weather> findByCityInIgnoreCase(List<String> city);
  
}
