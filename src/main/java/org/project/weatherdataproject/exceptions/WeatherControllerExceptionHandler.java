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
    public ResponseEntity handleInvalidInputException(InvalidInputException pe){
        ExceptionResponseDto exceptionResponseDTO = new ExceptionResponseDto(
                pe.getMessage(),
                400
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InSufficientWeatherDataException.class)
    public ResponseEntity handleInSufficientDataException(InvalidInputException pe){
        ExceptionResponseDto exceptionResponseDTO = new ExceptionResponseDto(
                pe.getMessage(),
                400
        );
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }
}