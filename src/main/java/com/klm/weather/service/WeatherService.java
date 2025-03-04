package com.klm.weather.service;

import static com.klm.weather.service.WeatherSpecification.hasCity;
import static com.klm.weather.service.WeatherSpecification.hasDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.klm.weather.exception.InvalidInputException;
import com.klm.weather.model.Weather;
import com.klm.weather.repository.WeatherRepository;

import jakarta.transaction.Transactional;

@Service
public class WeatherService {

	private final Logger logger = LogManager.getLogger(WeatherService.class);

	private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");


	private final WeatherRepository weatherRepository;

	public WeatherService(WeatherRepository weatherRepository) {
		this.weatherRepository = weatherRepository;
	}

	@Transactional
	public Weather saveWeather(Weather weather) {

		return weatherRepository.save(weather);

	}

	public Optional<Weather> getWeatherById(Integer id) {
		return weatherRepository.findById(id);
	}

	public List<Weather> getWeather(String date, String city, String sort) {
		Date convertedDate = null;
		Specification<Weather> weatherSpec = Specification.where(null);
		if (date != null) {
			try {
				convertedDate = simpleDateFormat.parse(date);
			} catch (ParseException e) {
				throw new InvalidInputException("Invalid date->" + date);
			}
		}

		weatherSpec = hasCity(city).and(hasDate(convertedDate));

		Sort sortOrder = null;
		if ("date".equalsIgnoreCase(sort)) {
			sortOrder = Sort.by("date").ascending();
		} else if ("-date".equalsIgnoreCase(sort)) {
			sortOrder = Sort.by("date").descending();
		} else {
			sortOrder = Sort.unsorted();
		}
		logger.info("Final sort order is:{}",sortOrder.toString());
		return weatherRepository.findAll(weatherSpec, sortOrder);
	}

}
