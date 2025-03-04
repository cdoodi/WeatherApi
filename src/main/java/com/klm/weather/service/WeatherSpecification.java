package com.klm.weather.service;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import com.klm.weather.model.Weather;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;



public class WeatherSpecification {


	public static Specification<Weather> hasCity(String city) {
		return (root, query, criteriaBuilder) ->  {
			return cityClaus(city, root, criteriaBuilder);
		
	};
	}

	public static Specification<Weather> hasDate(Date date) {
		return (root, query, criteriaBuilder) -> {
			return dateClause(date, root, criteriaBuilder);
		};
	}

	private static Predicate cityClaus(String city, Root<Weather> root, CriteriaBuilder criteriaBuilder) {
		if(city == null) {
			
			return criteriaBuilder.conjunction();
		}
		return criteriaBuilder.lower(root.get("city")).in(Arrays.asList(city.split(",")).stream().map(String::toLowerCase).collect(Collectors.toList()));
	}

	private static Predicate dateClause(Date date, Root<Weather> root, CriteriaBuilder criteriaBuilder) {
		if(date == null) {
			return criteriaBuilder.conjunction();
		}
		return criteriaBuilder.equal(root.get("date"), date);
	}
	
}
