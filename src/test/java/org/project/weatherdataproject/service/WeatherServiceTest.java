package org.project.weatherdataproject.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;
    @InjectMocks
    private WeatherServiceImpl weatherService;

    @Test
    public void testAddWeather()
    {
        //Arrange
        Weather weather=new Weather();
        weather.setId("1");
        weather.setCity("Bengaluru");
        when(weatherRepository.save(weather)).thenReturn(weather);

        //Act
        WeatherResponseDTO weatherResponseDTO=weatherService.saveWeather(weather);
        assertNotNull(weatherResponseDTO);
    }


}
