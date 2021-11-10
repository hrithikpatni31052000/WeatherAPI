package com.spring.microservices.controller;

import com.spring.microservices.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@PropertySource("classpath:message.properties")
public class WeatherController
{
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @GetMapping("/CityWeather")
    public ResponseEntity<Weather> getWeatherData(@RequestParam String city)
    {
        Map<String , String> uniVars = new HashMap<>();
        uniVars.put("q" , city);
        uniVars.put("apikey" , env.getProperty("api.key"));

        ResponseEntity<Weather> weather = restTemplate.getForEntity("https://api.openweathermap.org/data/2.5/weather?q={q}&appid={apikey}", Weather.class, uniVars);
        if(weather.getStatusCode().equals(HttpStatus.OK))
            return ResponseEntity.ok(weather.getBody());

        else
            return ResponseEntity.badRequest().body(weather.getBody());
    }
}
