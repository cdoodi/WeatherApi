package com.klm.weather.service;

import static com.klm.weather.service.WeatherSpecification.hasCity;
import static com.klm.weather.service.WeatherSpecification.hasDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.klm.weather.dao.WeatherDto;
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
	public WeatherDto saveWeather(WeatherDto weatherDto) {

		Weather weather = convertToWeather(weatherDto);

		return convertToWeatherDto(weatherRepository.save(weather));

	}

	private static Weather convertToWeather(WeatherDto weatherDto) {
		Weather weather = new Weather(weatherDto.getDate(), weatherDto.getLat(), weatherDto.getLon(),
				weatherDto.getCity(), weatherDto.getState(), weatherDto.getTemperatures());

		return weather;
	}

	public Optional<WeatherDto> getWeatherById(Integer id) {

		Optional<Weather> weather = weatherRepository.findById(id);

		return weather.isPresent() ? Optional.of(convertToWeatherDto(weather.get())) : Optional.empty();

	}

	public List<WeatherDto> getWeather(String date, String city, String sort) {
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
		logger.info("Final sort order is:{}", sortOrder.toString());
		List<Weather> weatherList = weatherRepository.findAll(weatherSpec, sortOrder);
		return weatherList.stream().map(WeatherService::convertToWeatherDto).collect(Collectors.toList());
	}

	private static WeatherDto convertToWeatherDto(Weather weather) {

		WeatherDto weatherDto = new WeatherDto(weather.getDate(), weather.getLat(), weather.getLon(), weather.getCity(),
				weather.getState(), weather.getTemperatures());
		weatherDto.setId(weather.getId());
		return weatherDto;
	}

}
