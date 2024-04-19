package org.project.weatherdataproject.exceptions;

import org.project.weatherdataproject.Dtos.ExceptionResponseDTO;
import org.project.weatherdataproject.controller.WeatherController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = org.project.weatherdataproject.controller.WeatherController.class)
public class WeatherControllerExceptionHandler {

    @ExceptionHandler(IDWeatherDataExists.class)
    public ResponseEntity<ExceptionResponseDTO> handleIDWeatherDataExists(IDWeatherDataExists ex) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IdWeatherNotExists.class)
    public ResponseEntity<ExceptionResponseDTO> handleIDWeatherNotExists(IdWeatherNotExists ex) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({WeatherCityDataNotFound.class,WeatherCityDataNotPresent.class})
    public ResponseEntity<ExceptionResponseDTO> handleWeatherCityDataNotFound(RuntimeException ex) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WeatherDataWithDateExists.class)
    public ResponseEntity<ExceptionResponseDTO> handleWeatherDataWithDateExists(WeatherDataWithDateExists ex) {
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CityDataAlreadyExists.class)
    public ResponseEntity<ExceptionResponseDTO> handleWeatherCityDataAlreadyExists(CityDataAlreadyExists ce)
    {
        ExceptionResponseDTO exceptionResponseDto=new ExceptionResponseDTO(ce.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
        return  new ResponseEntity<>(exceptionResponseDto,HttpStatus.NOT_ACCEPTABLE);
    }
}
