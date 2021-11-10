package com.spring.microservices.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather
{
    private Map<String , Double> main;

    public Map<String, Double> getMain() {
        return main;
    }

    public void setMain(Map<String, Double> main) {
        this.main = main;
    }

    public Weather(Map<String,Double> main)
    {
        this.main = main;
    }

    @Override
    public String toString()
    {
        return "Weather{" + "main = " + main + '}';
    }

    public Weather()
    {

    }
}
