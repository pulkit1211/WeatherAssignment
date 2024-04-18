package org.project.weatherdataproject.exceptions;
public class CityDataAlreadyExists extends RuntimeException{
    public CityDataAlreadyExists(String message)
    {
        super(message);
    }
}
