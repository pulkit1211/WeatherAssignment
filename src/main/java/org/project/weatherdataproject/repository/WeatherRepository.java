package org.project.weatherdataproject.repository;

import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.entity.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface WeatherRepository extends MongoRepository<Weather,String> {

    Weather findWeatherByCity(String city);

}
