package org.project.weatherdataproject.service;

import org.project.weatherdataproject.Dtos.WeatherDataResponseDTO;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.entity.WeatherData;
import org.project.weatherdataproject.exceptions.CityDataAlreadyExists;
import org.project.weatherdataproject.exceptions.IDWeatherDataExists;
import org.project.weatherdataproject.exceptions.WeatherDataWithDateExists;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface WeatherService {
    List<WeatherResponseDTO> getAllWeather();

    WeatherResponseDTO getWeatherById(String id);

    WeatherResponseDTO saveWeather(Weather weather) throws CityDataAlreadyExists;

    void deleteWeather(String id);

    WeatherResponseDTO addWeatherData(String city, WeatherData weatherData) throws WeatherDataWithDateExists, IDWeatherDataExists;

    List<WeatherDataResponseDTO> findALlWeatherByCity(String city);

    List<WeatherDataResponseDTO> findWeatherWithinRange(String city, Date startDate, Date endDate);

    List<WeatherDataResponseDTO> filterSortTheData(String city, String sortBy, String order);

    List<WeatherDataResponseDTO> getWeatherDataOfLastKDays(String city,int days);

    String predictWeather(String city);
}
