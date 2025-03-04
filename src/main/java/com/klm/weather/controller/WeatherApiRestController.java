package com.klm.weather.controller;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	private final WeatherService weatherService;

	private final Logger logger = LogManager.getLogger(WeatherApiRestController.class);

	public WeatherApiRestController(WeatherService weatherService) {
		this.weatherService = weatherService;
	}


	@PostMapping
	public ResponseEntity<Weather> addWeather(@RequestBody Weather weather) {

		ResponseEntity<Weather> postResponseEntity = ResponseEntity.status(HttpStatus.CREATED)
				.body(weatherService.saveWeather(weather));
		return postResponseEntity;
	}

	@GetMapping
	public List<Weather> getWeather(@RequestParam(required = false) String date,
			@RequestParam(required = false) String city, @RequestParam(required = false) String sort)
			 {

		logger.info("Received request:date={},city={},sort={}",date,city,sort);
		return weatherService.getWeather(date, city, sort);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Weather> getWeatherbyId(@PathVariable Integer id) {

		Optional<Weather> weather = weatherService.getWeatherById(id);

		return weather.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
}
