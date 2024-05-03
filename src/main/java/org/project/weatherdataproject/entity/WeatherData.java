package org.project.weatherdataproject.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import java.util.Date;


@Getter
@Setter
@Document
public class WeatherData {

    @Id
    private String id;

    @NotEmpty(message = "Temperature should not be null")
    @Min(value = -100, message = "Temperature should not be less than -100°C")
    @Max(value = 100, message = "Temperature should not be more than 100°C")
    private Long temperature;
    private Long humidity;
    private Long wind;
    private String description;

    private Date date;
}