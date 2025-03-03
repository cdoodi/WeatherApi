package com.klm.weather.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.klm.weather.model.Weather;
import com.klm.weather.repository.WeatherRepository;

@Service
public class WeatherService {
	
	private WeatherRepository weatherRepository;
	
	public WeatherService(WeatherRepository weatherRepository)
	{
		this.weatherRepository = weatherRepository;
	}
	
	public Weather saveWeather(Weather weather) {
		
		return weatherRepository.save(weather);
	
	}

}
