package org.project.weatherdataproject.repository;

import org.project.weatherdataproject.entity.Weather;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface WeatherRepository extends MongoRepository<Weather,String>
{
    Weather findByCity(String city);
    void deleteByCity(String city);


}
