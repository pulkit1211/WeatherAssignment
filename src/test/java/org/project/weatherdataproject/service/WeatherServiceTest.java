package org.project.weatherdataproject.service;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Test
    public void testWeatherById()
    {
        //Arrange
        String id="1";
        Weather weather=new Weather();
        weather.setId("1");
        weather.setCity("Jaipur");
        when(weatherRepository.findById(id)).thenReturn(Optional.of(weather));

        //Act
        WeatherResponseDTO weatherResponseDTO=weatherService.getWeatherById(id);
       // Weather weather1=WeatherEntityDTOMapper.convertWeatherResponseDTOToWeatherEntity(weatherService.getWeatherById(id));
        assertNotNull(weatherResponseDTO);

        //assertEquals(weather,weatherResponseDTO);
    }


}
