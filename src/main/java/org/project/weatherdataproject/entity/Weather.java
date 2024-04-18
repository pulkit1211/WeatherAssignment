package org.project.weatherdataproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document
public class Weather
{


    private String city;
    private Long temperature;
    private Long humidity;
    private Long wind;
    private String description;

}
