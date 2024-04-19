package org.project.weatherdataproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Setter
@Document
public class WeatherData {

    @Id
    private String id;
    private Long temperature;
    private Long humidity;
    private Long wind;
    private String description;
    private Date date;
}