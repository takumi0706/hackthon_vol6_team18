package com.example.hackthon_vol6_team.controller;

import com.example.hackthon_vol6_team.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WeatherController {

    @Autowired
    private WeatherService weatherService;


    @PostMapping("/display/weather")
    public String weather(@RequestParam(name="location") String location, Model model) {
        if(location == null || location.trim().isEmpty()) {
            model.addAttribute("error", "場所名を入力してください");
            return "home";

        } else {
            String weatherData = weatherService.getWeather(location);
            model.addAttribute("weatherData", weatherData);
            return "weather";
        }
    }
}
