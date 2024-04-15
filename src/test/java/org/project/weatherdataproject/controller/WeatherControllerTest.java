package org.project.weatherdataproject.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.project.weatherdataproject.dto.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.service.WeatherService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherControllerTest
{
    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @Test
    public void testCreateWeatherData() {
        Weather testData = new Weather();
        testData.setCity("Jaipur");
        WeatherResponseDTO data=WeatherEntityDTOMapper.convertWeatherEntityToWeatherResponseDTO(testData);
        when(weatherService.addWeatherData(any())).thenReturn(data);

        ResponseEntity response = weatherController.addWeather(testData);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        assertEquals(data, response.getBody());
       // verify(weatherService, times(1)).addWeatherData(testData);
    }
    @Test
    public void testGetWeatherData() {
        String city = "Bengaluru";

        WeatherResponseDTO testData = new WeatherResponseDTO();
        testData.setCity(city);
        testData.setTemperature(20L);
        testData.setHumidity(50L);
        testData.setWind(55L);
        testData.setDescription("Cloudy");

        when(weatherService.findWeatherDataByCity(city)).thenReturn(testData);

        ResponseEntity response = weatherController.findByCity(city);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testData, response.getBody());
       // verify(weatherService, times(1)).findWeatherDataByCity(city);
    }

    @Test
    public void testUpdateWeatherData() {
        String city = "Bengaluru";
        WeatherResponseDTO testData = new WeatherResponseDTO();
        testData.setTemperature(25L);
        testData.setHumidity(60L);

        when(weatherService.updateWeatherData(eq(city), any())).thenReturn(testData);

        Weather data=WeatherEntityDTOMapper.convertWeatherResponseDTOTOWeatherEntity(testData);

        ResponseEntity response = weatherController.updateTheWeatherData(city, data);
//
       assertEquals(HttpStatus.OK, response.getStatusCode());
       assertEquals(testData, response.getBody());
       verify(weatherService, times(1)).updateWeatherData(city,data);
    }

    @Test
    public void testDeleteWeatherData() {
        String city = "Kolkata";

       ResponseEntity response = weatherController.deleteWeatherData(city);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
       verify(weatherService, times(1)).deleteWeatherData(city);
    }
}
