package org.project.weatherdataproject.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class WeatherResponseDTO
{
    private String id;
    private String city;
    private List<WeatherDataResponseDTO> weatherDataResponseDTOS=new ArrayList<>();
}
