package com.example.hackthon_vol6_team.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Optional;

@Entity
@Table(name = "user_favorite")
@Data
public class UserFavorite {
    @Id
    private String location;
    private String budget;
    @Column(name= "food_budget")
    private String foodBudget;
    @Column(name = "food_item")
    private String foodItem;
    @Column(name = "food_restaurant")
    private String foodRestaurant;
    private String accommodation;
    @Column(name = "sightseeing_place")
    private String sightseeingPlace;
    @Column(name = "sightseeing_build")
    private String sightseeingBuild;
    private String schedule;
    private String culture;
    private String care;
    private String weather;
}
