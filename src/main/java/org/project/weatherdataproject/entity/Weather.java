package org.project.weatherdataproject.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
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

    @NotEmpty(message = "City cannot be null")
    @Size(min =3,max = 20,message = "City size should not be less than 3")
    private String city;

    private List<WeatherData> weatherData=new ArrayList<>() ;
}
