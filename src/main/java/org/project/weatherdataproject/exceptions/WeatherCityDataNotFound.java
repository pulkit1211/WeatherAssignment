package org.project.weatherdataproject.exceptions;

public class WeatherCityDataNotFound extends RuntimeException{

    public WeatherCityDataNotFound(String message)
    {
        super(message);
    }
}
