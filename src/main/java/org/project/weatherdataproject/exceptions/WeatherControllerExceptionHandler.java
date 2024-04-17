package org.project.weatherdataproject.exceptions;

import org.project.weatherdataproject.controller.WeatherController;
import org.project.weatherdataproject.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = WeatherController.class)
public class WeatherControllerExceptionHandler
{
    @ExceptionHandler(WeatherCityDataNotFoundException.class)
    public ResponseEntity<ExceptionResponseDto> handleWeatherCityDataNotFoundException(WeatherCityDataNotFoundException ex) {
        ExceptionResponseDto exceptionResponseDto = new ExceptionResponseDto(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionResponseDto);
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ExceptionResponseDto> handleInvalidInputException(InvalidInputException pe) {
        ExceptionResponseDto exceptionResponseDTO = new ExceptionResponseDto(
                pe.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InSufficientWeatherDataException.class)
    public ResponseEntity<ExceptionResponseDto> handleInSufficientDataException(InSufficientWeatherDataException pe) {
        ExceptionResponseDto exceptionResponseDTO = new ExceptionResponseDto(
                pe.getMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CityDataAlreadyExists.class)
    public ResponseEntity<ExceptionResponseDto> handleWeatherCityDataAlreadyExists(CityDataAlreadyExists ce)
    {
        ExceptionResponseDto exceptionResponseDto=new ExceptionResponseDto(ce.getMessage(),HttpStatus.NOT_ACCEPTABLE.value());
        return  new ResponseEntity<>(exceptionResponseDto,HttpStatus.NOT_ACCEPTABLE);
    }
}
