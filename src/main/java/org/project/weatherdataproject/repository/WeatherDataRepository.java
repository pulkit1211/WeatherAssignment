package org.project.weatherdataproject.repository;

import org.project.weatherdataproject.entity.WeatherData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface WeatherDataRepository extends MongoRepository<WeatherData,String> {
}
