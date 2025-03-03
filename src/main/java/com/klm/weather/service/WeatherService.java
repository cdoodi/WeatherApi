package com.klm.weather.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.stereotype.Service;

import com.klm.weather.model.Weather;
import com.klm.weather.repository.WeatherRepository;

@Service
public class WeatherService {

	private WeatherRepository weatherRepository;
	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public WeatherService(WeatherRepository weatherRepository) {
		this.weatherRepository = weatherRepository;
	}

	public Weather saveWeather(Weather weather) {

		return weatherRepository.save(weather);

	}

	public List<Weather> getWeather(String date) throws ParseException {
		if (date != null) {
			return weatherRepository.findByDate(simpleDateFormat.parse(date));
		}

		return weatherRepository.findAll();
	}

}
