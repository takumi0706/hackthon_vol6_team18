package com.example.hackthon_vol6_team.form;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class LocationForm {
    private String location;
    private String budget;
    private String foodBudget;
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
