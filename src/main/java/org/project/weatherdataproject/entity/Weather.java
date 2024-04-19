package org.project.weatherdataproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Getter
@Setter
public class Weather {

    @Id
    private String id;
    private String city;

    private List<WeatherData> weatherData=new ArrayList<>() ;
}