package org.project.weatherdataproject.service;

import org.project.weatherdataproject.dto.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.exceptions.InSufficientWeatherDataException;
import org.project.weatherdataproject.exceptions.WeatherCityDataNotFoundException;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WeatherService
{
   WeatherResponseDTO addWeatherData(Weather weather) ;
   WeatherResponseDTO findWeatherDataByCity(String city) throws WeatherCityDataNotFoundException;
   List<WeatherResponseDTO> getAllData();
   WeatherResponseDTO updateWeatherData(String city,Weather weather);
   boolean deleteWeatherData(String city);


}
