package org.project.weatherdataproject.controller;

import org.project.weatherdataproject.Dtos.WeatherDataResponseDTO;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.entity.WeatherData;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.service.WeatherService;
import org.project.weatherdataproject.service.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public ResponseEntity<List<WeatherResponseDTO>> getAllWeather()
    {
        List<WeatherResponseDTO>weatherResponseDTOList= weatherService.getAllWeather();
        return ResponseEntity.ok(weatherResponseDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WeatherResponseDTO> getWeatherById(@PathVariable String id) {
        WeatherResponseDTO weatherResponseDTO=weatherService.getWeatherById(id);
        return ResponseEntity.ok(weatherResponseDTO);
    }

    @PostMapping
    public ResponseEntity<WeatherResponseDTO> saveWeather(@RequestBody Weather weather)
    {

        WeatherResponseDTO weatherResponseDTO= weatherService.saveWeather(weather);
        return ResponseEntity.ok(weatherResponseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWeather(@PathVariable String id) {
        weatherService.deleteWeather(id);
        return ResponseEntity.ok("Weather is deleted with this id:-"+id);
    }

    @GetMapping("/{city}/weatherdata")
    public ResponseEntity<WeatherResponseDTO> addWeatherData(@PathVariable String city, @RequestBody WeatherData weatherData) {
        WeatherResponseDTO weatherResponseDTO= weatherService.addWeatherData(city, weatherData);
        return ResponseEntity.ok(weatherResponseDTO);
    }

    @GetMapping("/{city}/data")
    public ResponseEntity<List<WeatherDataResponseDTO>> getWeatherDataByCity(@PathVariable String city)
    {
        List<WeatherDataResponseDTO> weatherDataResponseDTOS= weatherService.findALlWeatherByCity(city);
        return ResponseEntity.ok(weatherDataResponseDTOS);
    }

    @GetMapping("/weatherWithinRange")
    public ResponseEntity<List<WeatherDataResponseDTO>> getWeatherDataWithinRange(@RequestParam String city, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate)
    {
        List<WeatherDataResponseDTO> weatherDataResponseDTOS=  weatherService.findWeatherWithinRange(city,startDate,endDate);
        return ResponseEntity.ok(weatherDataResponseDTOS);
    }

    @GetMapping("/{city}/sort")
    public ResponseEntity<List<WeatherDataResponseDTO>> getFilterAndSortedData(@PathVariable String city,
                                                    @RequestParam String sort_by,
                                                    @RequestParam(required = false, defaultValue = "asc") String order)
    {
        List<WeatherDataResponseDTO> dataResponseDTOS= weatherService.filterSortTheData(city,sort_by,order);
        return ResponseEntity.ok(dataResponseDTOS);
    }
}
