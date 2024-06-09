package com.example.hackthon_vol6_team.dto;

public class WeatherData {
    private String city;
    private String description;
    private String temperature;
    private String iconUrl;

    public WeatherData(String city, String description, String temperature, String iconUrl) {
        this.city = city;
        this.description = description;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
