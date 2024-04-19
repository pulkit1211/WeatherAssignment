package org.project.weatherdataproject.exceptions;

public class WeatherCityDataNotPresent extends RuntimeException
{
    public WeatherCityDataNotPresent(String message)
    {
        super(message);
    }
}
