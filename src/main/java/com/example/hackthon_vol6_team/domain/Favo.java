package com.example.hackthon_vol6_team.domain;

import lombok.Data;

@Data
public class Favo {


    public Favo(String location, String date, String foodItem, String foodRestaurant, String accommodation,
                String sightseeingPlace, String sightseeingBuild, String schedule, String culture, String care, String weather) {
        this.location = location;
        this.date = date;
        this.foodItem = foodItem;
        this.foodRestaurant = foodRestaurant;
        this.accommodation = accommodation;
        this.sightseeingPlace = sightseeingPlace;
        this.sightseeingBuild = sightseeingBuild;
        this.schedule = schedule;
        this.culture = culture;
        this.care = care;
        this.weather = weather;
    }
        private String location;
        private String date;
        private String foodItem;
        private String foodRestaurant;
        private String accommodation;
        private String sightseeingPlace;
        private String sightseeingBuild;
        private String schedule;
        private String culture;
        private String care;
        private String weather;
}
