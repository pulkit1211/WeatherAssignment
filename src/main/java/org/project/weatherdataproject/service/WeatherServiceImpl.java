package org.project.weatherdataproject.service;

import org.project.weatherdataproject.dto.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.exceptions.CityDataAlreadyExists;
import org.project.weatherdataproject.exceptions.InSufficientWeatherDataException;
import org.project.weatherdataproject.exceptions.WeatherCityDataNotFoundException;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class WeatherServiceImpl implements WeatherService{

    @Autowired
    private WeatherRepository weatherRepository;
    @Override
    public WeatherResponseDTO addWeatherData(Weather weather) throws RuntimeException {
        if(weather==null)
        {
            throw new WeatherCityDataNotFoundException("Input is invalid");
        }
        if(weather.getCity()==null )
        {
            throw new InSufficientWeatherDataException("Fill the information about city so that according to its we add city weather data on repository");
        }
        if(weatherRepository.findByCity(weather.getCity())!=null)
        {
            throw new CityDataAlreadyExists("City data already exists");
        }
        weatherRepository.save(weather);
        return WeatherEntityDTOMapper.convertWeatherEntityToWeatherResponseDTO(weather);
    }

    @Override
    public WeatherResponseDTO findWeatherDataByCity(String city) throws  WeatherCityDataNotFoundException{

        Weather weather=weatherRepository.findByCity(city);
        if(weather==null)
        {
            throw  new WeatherCityDataNotFoundException("This city data is not present in repository: "+city);
        }
        return WeatherEntityDTOMapper.convertWeatherEntityToWeatherResponseDTO(weather);
    }

    @Override
    public List<WeatherResponseDTO> getAllData() {
        List<Weather> weatherList=weatherRepository.findAll();
        if(weatherList==null)
        {
            throw new WeatherCityDataNotFoundException("Data not found");
        }
        List<WeatherResponseDTO> responseDTOList=new ArrayList<>();
        for(Weather weather:weatherList)
        {
            responseDTOList.add(WeatherEntityDTOMapper.convertWeatherEntityToWeatherResponseDTO(weather));
        }
        return responseDTOList;
    }

    @Override
    public WeatherResponseDTO updateWeatherData(String city, Weather weather) {


        Weather w1=weatherRepository.findByCity(city);
        if(w1==null)
        {
             throw new WeatherCityDataNotFoundException("City weather data not found so it cannot be updated");
        }
        w1.setCity(weather.getCity());
        w1.setTemperature(weather.getTemperature());
        w1.setHumidity(weather.getHumidity());
        w1.setWind(weather.getWind());
        w1.setDescription(weather.getDescription());

        return WeatherEntityDTOMapper.convertWeatherEntityToWeatherResponseDTO(weather);
    }

    @Override
    public boolean deleteWeatherData(String city) {
        if( weatherRepository.findByCity(city)==null)
        {
            throw new WeatherCityDataNotFoundException("City weather data not found in repository ");
        }
        weatherRepository.deleteByCity(city);
        return true;
    }
}
