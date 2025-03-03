package com.klm.weather.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.klm.weather.model.Weather;
import com.klm.weather.repository.WeatherRepository;

@Service
public class WeatherService {

	Logger logger = LogManager.getLogger(WeatherService.class);
	private WeatherRepository weatherRepository;
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public WeatherService(WeatherRepository weatherRepository) {
		this.weatherRepository = weatherRepository;
	}

	public Weather saveWeather(Weather weather) {

		return weatherRepository.save(weather);

	}

	
	public Optional<Weather> getWeatherById(Integer id) {
		return weatherRepository.findById(id);
	}

	public List<Weather> getWeather(String date, String city, String sort) throws ParseException {
		if (date != null) {
			return weatherRepository.findByDate(simpleDateFormat.parse(date));
		}
		if (city != null) {

			List<String> cityList = Arrays.asList(city.split(","));

			return weatherRepository.findByCityInIgnoreCase(cityList);
		}
		if ("date".equalsIgnoreCase(sort)) {
			return weatherRepository.findAllByOrderByDateAsc();
		} else if ("-date".equalsIgnoreCase(sort)) {
			return weatherRepository.findAllByOrderByDateDesc();
		}

		return weatherRepository.findAll();
	}

}
