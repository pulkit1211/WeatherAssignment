package org.project.weatherdataproject.exceptions;

public class InSufficientWeatherDataException extends RuntimeException{
    public InSufficientWeatherDataException(String message)
    {
        super(message);
    }
}
