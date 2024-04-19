package org.project.weatherdataproject.Dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class WeatherDataResponseDTO
{
    private String id;
    private Long temperature;
    private Long humidity;
    private Long wind;
    private String description;
    private Date date;
}
