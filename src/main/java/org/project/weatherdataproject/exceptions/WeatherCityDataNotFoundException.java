package org.project.weatherdataproject.exceptions;

public class WeatherCityDataNotFoundException extends RuntimeException
{
    public WeatherCityDataNotFoundException(String message)
    {
        super(message);
    }
}
