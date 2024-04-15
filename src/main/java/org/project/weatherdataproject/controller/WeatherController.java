package org.project.weatherdataproject.controller;

import org.project.weatherdataproject.dto.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @PostMapping
    public ResponseEntity addWeather(@RequestBody Weather weather)
    {
        WeatherResponseDTO createdData=weatherService.addWeatherData(weather);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdData);
    }

    @GetMapping("/{city}")
    public ResponseEntity findByCity(@PathVariable String city)
    {
        WeatherResponseDTO weatherResponseDTO= weatherService.findWeatherDataByCity(city);
        return  ResponseEntity.ok(weatherResponseDTO);
    }

    @GetMapping
    public ResponseEntity getAllWeatherData()
    {
        List<WeatherResponseDTO> weatherResponseDTOLis=weatherService.getAllData();
        return ResponseEntity.ok(weatherResponseDTOLis);
    }

    @PutMapping("/{city}")
    public ResponseEntity updateTheWeatherData(@PathVariable String city,@RequestBody Weather weather)
    {
        WeatherResponseDTO weatherResponseDTO= weatherService.updateWeatherData(city,weather);
        return ResponseEntity.ok(weatherResponseDTO);
    }

    @DeleteMapping("/{city}")
    public  ResponseEntity deleteWeatherData(@PathVariable String city)
    {
        weatherService.deleteWeatherData(city);
        return ResponseEntity.noContent().build();
    }

}
