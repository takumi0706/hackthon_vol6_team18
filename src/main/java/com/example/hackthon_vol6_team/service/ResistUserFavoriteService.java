package com.example.hackthon_vol6_team.service;

import com.example.hackthon_vol6_team.controller.DisplayController;
import com.example.hackthon_vol6_team.entity.UserFavorite;
import com.example.hackthon_vol6_team.form.FavoriteResistForm;
import com.example.hackthon_vol6_team.repositry.UserFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;


@Service
@RequiredArgsConstructor
public class ResistUserFavoriteService {

    private final UserFavoriteRepository userFavoriteRepository;
    private final UserFavoriteRepository repository;

    public UserFavorite resistUserFavorite(@RequestParam(name = "location") String location,
                                           @RequestParam(name = "another_budget", required = false) String another_budget,
                                           @RequestParam(name = "food_budget", required = false) String food_budget,
                                           Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
        var userFavorite = new UserFavorite();

        userFavorite.setLocation(location);
        userFavorite.setBudget(another_budget);
        userFavorite.setFoodBudget(prompts.get("foodBudget"));
        userFavorite.setFoodItem(prompts.get("foodItem"));
        userFavorite.setFoodRestaurant(prompts.get("foodRestaurant"));
        userFavorite.setAccommodation(prompts.get("accommodation"));
        userFavorite.setSightseeingPlace(prompts.get("sightseeingPlace"));
        userFavorite.setSightseeingBuild(prompts.get("sightseeingBuild"));
        userFavorite.setSchedule(prompts.get("schedule"));
        userFavorite.setCulture(prompts.get("culture"));
        userFavorite.setCare(prompts.get("care"));
        userFavorite.setWeather(prompts.get("weather"));

        return repository.save(userFavorite);
    }

}
