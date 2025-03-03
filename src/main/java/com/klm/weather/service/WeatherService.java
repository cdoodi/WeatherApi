package com.klm.weather.service;

import java.util.List;

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
	
	public List<Weather> getWeather() {
		
		return weatherRepository.findAll();
	}

}
