package org.project.weatherdataproject.mapper;

import org.project.weatherdataproject.dto.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;

public class WeatherEntityDTOMapper {

    public static WeatherResponseDTO convertWeatherEntityToWeatherResponseDTO(Weather weather)
    {
        WeatherResponseDTO weatherResponseDTO=new WeatherResponseDTO();
        weatherResponseDTO.setCity(weather.getCity());
        weatherResponseDTO.setTemperature(weather.getTemperature());
        weatherResponseDTO.setHumidity(weather.getHumidity());
        weatherResponseDTO.setWind(weather.getWind());
        weatherResponseDTO.setDescription(weather.getDescription());
        return  weatherResponseDTO;
    }
    public static Weather convertWeatherResponseDTOTOWeatherEntity(WeatherResponseDTO weatherResponseDTO)
    {
        Weather weather=new Weather();
        weather.setCity(weatherResponseDTO.getCity());
        weather.setTemperature(weatherResponseDTO.getTemperature());
        weather.setHumidity(weatherResponseDTO.getHumidity());
        weather.setWind(weatherResponseDTO.getWind());
        weather.setDescription(weatherResponseDTO.getDescription());
        return  weather;
    }
}
