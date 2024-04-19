package org.project.weatherdataproject.exceptions;

public class WeatherDataWithDateExists extends RuntimeException{

    public WeatherDataWithDateExists(String message)
    {
        super(message);
    }
}
