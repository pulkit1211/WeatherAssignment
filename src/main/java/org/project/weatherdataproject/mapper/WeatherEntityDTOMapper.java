package org.project.weatherdataproject.mapper;

import org.project.weatherdataproject.Dtos.WeatherDataResponseDTO;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.entity.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class WeatherEntityDTOMapper {

    public static WeatherResponseDTO convertWeatherToWeatherResponseDTO(Weather weather)
    {
        WeatherResponseDTO weatherResponseDTO=new WeatherResponseDTO();
        weatherResponseDTO.setId(weather.getId());
        weatherResponseDTO.setCity(weather.getCity());
        List<WeatherDataResponseDTO> responseDTOS=new ArrayList<>();
        for(WeatherData weatherData:weather.getWeatherData())
        {
            responseDTOS.add(convertWeatherDataToWeatherDataResponseDTO(weatherData));
        }
        weatherResponseDTO.setWeatherDataResponseDTOS(responseDTOS);
        return  weatherResponseDTO;
    }

    public static WeatherDataResponseDTO convertWeatherDataToWeatherDataResponseDTO(WeatherData weatherData)
    {
        WeatherDataResponseDTO weatherDataResponseDTO=new WeatherDataResponseDTO();
        weatherDataResponseDTO.setId(weatherData.getId());
        weatherDataResponseDTO.setTemperature(weatherData.getTemperature());
        weatherDataResponseDTO.setHumidity(weatherData.getHumidity());
        weatherDataResponseDTO.setWind(weatherData.getWind());
        weatherDataResponseDTO.setDate(weatherData.getDate());
        weatherDataResponseDTO.setDescription(weatherData.getDescription());
        return weatherDataResponseDTO;
    }

    public static Weather convertWeatherResponseDTOToWeatherEntity(WeatherResponseDTO weatherResponseDTO)
    {
        Weather weather=new Weather();
        weather.setId(weatherResponseDTO.getId());
        weather.setCity(weatherResponseDTO.getCity());
        List<WeatherData> weatherDataList=new ArrayList<>();
        for(WeatherDataResponseDTO weatherDataResponseDTO:weatherResponseDTO.getWeatherDataResponseDTOS())
        {
            weatherDataList.add(convertWeatherDataResponseToWeatherDataEntity(weatherDataResponseDTO));
        }
        weather.setWeatherData(weatherDataList);
        return weather;
    }
    public static WeatherData convertWeatherDataResponseToWeatherDataEntity(WeatherDataResponseDTO weatherDataResponseDTO)
    {
        WeatherData weatherData=new WeatherData();
        weatherData.setId(weatherDataResponseDTO.getId());
        weatherData.setTemperature(weatherDataResponseDTO.getTemperature());
        weatherData.setHumidity(weatherDataResponseDTO.getHumidity());
        weatherData.setWind(weatherDataResponseDTO.getWind());
        weatherData.setDescription(weatherDataResponseDTO.getDescription());
        weatherData.setDate(weatherDataResponseDTO.getDate());
        return weatherData;

    }
}
