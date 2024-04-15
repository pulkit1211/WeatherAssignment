package org.project.weatherdataproject.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponseDTO
{
    private String city;
    private Long temperature;
    private Long humidity;
    private Long wind;
    private String description;
}
