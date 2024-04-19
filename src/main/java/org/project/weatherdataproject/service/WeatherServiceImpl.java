package org.project.weatherdataproject.service;

import org.project.weatherdataproject.Dtos.WeatherDataResponseDTO;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.entity.WeatherData;
import org.project.weatherdataproject.exceptions.*;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeatherServiceImpl implements WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public List<WeatherResponseDTO> getAllWeather()
    {
       List<Weather> weather=weatherRepository.findAll();
       List<WeatherResponseDTO> weatherResponseDTOList=new ArrayList<>();
       for(Weather weather1:weather)
       {
           weatherResponseDTOList.add(WeatherEntityDTOMapper.convertWeatherToWeatherResponseDTO(weather1));
       }
       return weatherResponseDTOList;
    }

    @Override
    public WeatherResponseDTO getWeatherById(String id)
    {
        Optional<Weather> optionalWeather= weatherRepository.findById(id);
        if(optionalWeather.isEmpty())
        {
            throw new WeatherCityDataNotPresent("Weather data is not present with this id:- "+id);
        }
        return WeatherEntityDTOMapper.convertWeatherToWeatherResponseDTO(optionalWeather.get());

    }

    @Override
    public WeatherResponseDTO saveWeather(Weather weather) throws CityDataAlreadyExists
    {
        if(weather.getId()==null)
        {
            throw new IdWeatherNotExists("Id not be null so put Id pls");
        }
        if(weather.getCity()==null)
        {
            throw new WeatherCityDataNotPresent("City cannot be null");
        }
        Weather weather1=weatherRepository.findWeatherByCity(weather.getCity());
        if(weather1!=null)
        {
            throw  new CityDataAlreadyExists("City data already exists");
        }
        Weather w2=weatherRepository.save(weather);
        return WeatherEntityDTOMapper.convertWeatherToWeatherResponseDTO(w2);
    }

    @Override
    public void deleteWeather(String id) {
        Optional<Weather> weather=weatherRepository.findById(id);
        if(weather.isEmpty())
        {
            throw new IdWeatherNotExists("Weather is not exists with this ID so it can be deleted");
        }
        weatherRepository.deleteById(id);
    }

    @Override
    public WeatherResponseDTO addWeatherData(String city, WeatherData weatherData) throws IDWeatherDataExists,WeatherCityDataNotFound,WeatherDataWithDateExists{
        Weather weather = weatherRepository.findWeatherByCity(city);
        if(weather==null)
        {
            throw new WeatherCityDataNotPresent("Data of city not exists in DB");
        }
         List<WeatherData> weatherDataList=weather.getWeatherData();
         for(WeatherData weatherData1:weatherDataList)
         {
             if(weatherData1.getId().equals(weatherData.getId()))
             {
                 throw new IDWeatherDataExists("Id should be unique");
             }
             if(weatherData1.getDate().equals(weatherData.getDate()))
             {
                 throw new WeatherDataWithDateExists("Weather data with this date already exists");
             }
         }
            weather.getWeatherData().add(weatherData);

            Weather w1= weatherRepository.save(weather);
            return WeatherEntityDTOMapper.convertWeatherToWeatherResponseDTO(w1);

    }

    @Override
    public List<WeatherDataResponseDTO> findALlWeatherByCity(String city)
    {
        Weather weather=weatherRepository.findWeatherByCity(city);
        if(weather==null)
        {
            throw new WeatherCityDataNotFound("No data found for this city"+city);
        }
        List<WeatherDataResponseDTO> weatherDataResponseDTOS=new ArrayList<>();
        for(WeatherData data:weather.getWeatherData())
        {
            weatherDataResponseDTOS.add(WeatherEntityDTOMapper.convertWeatherDataToWeatherDataResponseDTO(data));
        }
        return weatherDataResponseDTOS;
    }

    @Override
    public List<WeatherDataResponseDTO> findWeatherWithinRange(String city, Date startDate, Date endDate)
    {
        Weather weather=weatherRepository.findWeatherByCity(city);
        if(weather==null)
        {
            throw new RuntimeException("No data found from this city");
        }
        List<WeatherData> weatherDataList=new ArrayList<>();
        for(WeatherData weatherData:weather.getWeatherData())
        {
            Date dataDate = weatherData.getDate();
            if (dataDate.after(startDate) && dataDate.before(endDate)) {
                weatherDataList.add(weatherData);
            }
        }
        List<WeatherDataResponseDTO> weatherDataResponseDTOList=new ArrayList<>();
        for(WeatherData weatherData:weatherDataList)
        {
            weatherDataResponseDTOList.add(WeatherEntityDTOMapper.convertWeatherDataToWeatherDataResponseDTO(weatherData));
        }
        return  weatherDataResponseDTOList;
    }

    @Override
    public List<WeatherDataResponseDTO> filterSortTheData(String city, String sortBy, String order)
    {
        Weather weather=weatherRepository.findWeatherByCity(city);
        if(weather==null)
        {
            throw  new RuntimeException("Data not found ");
        }
        List<WeatherData> data=weather.getWeatherData();
        if(data==null)
        {
            throw  new RuntimeException("Weather data is empty");
        }
        if(sortBy!=null)
        {
            switch (sortBy)
            {
                case "temperature":
                    Collections.sort(data,(a,b)->Long.compare(a.getTemperature(),b.getTemperature()));
                    break;

                case "humidity":
                    Collections.sort(data,(a,b)->Long.compare(a.getHumidity(), b.getHumidity()));
                    break;

                case "wind":
                    Collections.sort(data,(a,b)->Long.compare(a.getWind(), b.getWind()));

                default:
                    throw new RuntimeException("Required params is not found");

            }
        }
        if(order.equalsIgnoreCase("DESC"))
        {
            Collections.reverse(data);
        }
        List<WeatherDataResponseDTO> weatherDataResponseDTOS=new ArrayList<>();
        for(WeatherData weatherData:data)
        {
            weatherDataResponseDTOS.add(WeatherEntityDTOMapper.convertWeatherDataToWeatherDataResponseDTO(weatherData));
        }
        return  weatherDataResponseDTOS;
    }
}
