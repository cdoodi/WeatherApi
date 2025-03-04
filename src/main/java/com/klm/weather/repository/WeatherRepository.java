package com.klm.weather.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.klm.weather.model.Weather;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer>,JpaSpecificationExecutor<Weather>{
	
  
}
