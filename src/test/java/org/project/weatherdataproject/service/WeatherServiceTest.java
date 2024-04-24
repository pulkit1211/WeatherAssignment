package org.project.weatherdataproject.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.project.weatherdataproject.Dtos.WeatherResponseDTO;
import org.project.weatherdataproject.entity.Weather;
import org.project.weatherdataproject.entity.WeatherData;
import org.project.weatherdataproject.exceptions.CityDataAlreadyExists;
import org.project.weatherdataproject.mapper.WeatherEntityDTOMapper;
import org.project.weatherdataproject.repository.WeatherRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.test.Scenario;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;
    @InjectMocks
    private WeatherServiceImpl weatherService;

    private Weather testWeather;
    private WeatherResponseDTO testWeatherResponseDTO;

    @BeforeEach
    void setUp() {
        testWeather = new Weather();
        testWeather.setId("1");
        testWeather.setCity("Bengaluru");
        testWeatherResponseDTO = new WeatherResponseDTO();
        testWeatherResponseDTO.setCity("Bengaluru");
        testWeatherResponseDTO.setId("1");
    }

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

    @Test
    void getAllWeather_ReturnsListOfWeatherResponseDTOs() {
        when(weatherRepository.findAll()).thenReturn(Collections.singletonList(testWeather));

        List<WeatherResponseDTO> result = weatherService.getAllWeather();

        assertEquals(1, result.size());
        assertEquals(testWeatherResponseDTO.getId(), result.get(0).getId());
        assertEquals(testWeatherResponseDTO.getCity(), result.get(0).getCity());
        verify(weatherRepository).findAll();
    }
    @Test
    void saveWeather_WithExistingCity_ThrowsException() {
        when(weatherRepository.findWeatherByCity(testWeather.getCity())).thenReturn(testWeather);

        assertThrows(CityDataAlreadyExists.class, () -> weatherService.saveWeather(testWeather));
    }

    @Test
    public  void testAddWeatherData()
    {
        Weather weather=new Weather();
        weather.setId("1");
        weather.setCity("Bengaluru");
        WeatherData weatherData=new WeatherData();
        weatherData.setId("1");
        weatherData.setHumidity(20L);
//        weather.setWeatherData(List.of(weatherData));

        when(weatherRepository.save(weather)).thenReturn(weather);

        WeatherResponseDTO weatherResponseDTO=weatherService.addWeatherData(weather.getCity(),weatherData);
        assertNotNull(weatherResponseDTO);

    }






}
