package org.project.weatherdataproject.repository;

import org.project.weatherdataproject.entity.Weather;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class WeatherRepository
{
    private static final Map<String, Weather> data=new HashMap<>();

    public Weather save(Weather weather)
    {
        if(!data.containsKey(weather.getCity())) {
            data.put(weather.getCity(), weather);
            System.out.println("Data added successfully");
        }
        else {
            System.out.println("Data exists with this city: " + weather.getCity());
            return  null;
        }
        return weather;
    }

    public  Weather findByCity(String city)
    {
        if(data.containsKey(city))
        {
            return data.get(city);
        }
        return  null;
    }

    public List<Weather> findAll()
    {
        if(data.isEmpty())
        {
            System.out.println("Data not present in repository");
            return  null;
        }
        List<Weather> weatherList=new ArrayList<>();
        for(String key:data.keySet())
        {
            weatherList.add(data.get(key));
        }
        return  weatherList;
    }

    public Weather update(Weather weather,String city)
    {
        if(!data.containsKey(city))
        {
            System.out.println("Weather not found with this city: "+city);
            return  null;
        }
        else
            data.put(city,weather);
        return weather;
    }

    public boolean delete(String city)
    {
        if(data.containsKey(city))
        {
            data.remove(city);
            return  true;
        }
        return  false;
    }


}
