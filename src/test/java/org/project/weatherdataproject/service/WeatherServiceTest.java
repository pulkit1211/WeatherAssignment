package org.project.weatherdataproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.matchers.NotNull;
import org.mockito.junit.jupiter.MockitoExtension;
import org.project.weatherdataproject.controller.WeatherController;
import org.project.weatherdataproject.dto.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.Scenario;
import org.springframework.util.Assert;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest

public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;
    @InjectMocks
    private WeatherServiceImpl weatherService;
    @Test
    public void testCreateWeather()
    {
        // Arrange
        Weather weatherToCreate = new Weather(); // Create a sample Weather object to be saved
        weatherToCreate.setCity("Jodhpur");
        when(weatherRepository.save(weatherToCreate)).thenReturn(weatherToCreate); // Stubbing the behavior of WeatherRepository save method

        // Act
        WeatherResponseDTO createdWeather = weatherService.addWeatherData(weatherToCreate); // Call the method under test

        // Assert
        assertNotNull(createdWeather); // Check that the created weather object is not null
        // You can add more assertions here to verify specific properties or conditions of the created weather object
        verify(weatherRepository, times(1)).save(weatherToCreate);
    }

    @Test
    public void testGetWeatherById() {
        // Arrange
        String city ="Mumbai";
        Weather expectedWeather = new Weather(); // Create a sample Weather object
        expectedWeather.setCity("Mumbai");
        when(weatherRepository.findByCity(city)).thenReturn(expectedWeather); // Stubbing the behavior of WeatherRepository

        // Act
        WeatherResponseDTO actualWeather = weatherService.findWeatherDataByCity(city); // Call the method under test

        // Assert
        assertNotNull(actualWeather); // Check that a non-null weather object is returned
       // assertEquals(expectedWeather, actualWeather); // Check that the returned weather object matches the expected weather
        verify(weatherRepository, times(1)).findByCity(city); // Verify that WeatherRepository's findById method is called once with the specified ID
    }
}
