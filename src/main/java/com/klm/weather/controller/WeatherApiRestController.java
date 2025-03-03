package com.klm.weather.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klm.weather.model.Weather;
import com.klm.weather.service.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherApiRestController {
	
	@Autowired
	private WeatherService weatherService;
	
	@PostMapping
	public ResponseEntity<Weather> addWeather(@RequestBody Weather weather){
		
		
		 ResponseEntity<Weather> postResponseEntity = ResponseEntity.status(HttpStatus.CREATED).body(weatherService.saveWeather(weather));
		 return postResponseEntity;
	}
	
	@GetMapping
	public List<Weather> getWeather(@RequestParam(required = false) String date) throws ParseException{
		
		return weatherService.getWeather(date);
	}
}
