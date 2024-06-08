package com.example.hackthon_vol6_team.controller;

import com.example.hackthon_vol6_team.service.GeocodingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class MapController {

    @Autowired
    private GeocodingService geocodingService;

    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    @GetMapping("/map")
    public String map(Model model) {
        model.addAttribute("googleMapsApiKey", googleMapsApiKey);
        return "map";
    }

    @GetMapping("/geocode")
    @ResponseBody
    public Map<String, String> geocode(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
        Map<String, String> response = new HashMap<>();
        try {
            String address = geocodingService.getGeocodedAddress(lat, lng);
            response.put("address", address);
        } catch (IOException e) {
            e.printStackTrace();
            response.put("address", "Error retrieving address");
        }
        return response;
    }
}
