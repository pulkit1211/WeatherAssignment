package org.project.weatherdataproject.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Weather
{

    private String city;
    private Long temperature;
    private Long humidity;
    private Long wind;
    private String description;

}
