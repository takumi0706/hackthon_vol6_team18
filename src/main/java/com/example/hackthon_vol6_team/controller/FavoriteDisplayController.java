package com.example.hackthon_vol6_team.controller;

import com.example.hackthon_vol6_team.form.LocationForm;
import com.example.hackthon_vol6_team.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.PropertyResolver;
import org.springframework.stereotype.Controller;
import com.example.hackthon_vol6_team.entity.UserFavorite;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hackthon_vol6_team.service.ChatGptService;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("prompts")
public class FavoriteDisplayController {

    @Autowired
    private FavoriteService service;

    @Autowired
    private ChatGptService chatGptService;

    @Autowired
    @Qualifier("propertyResolver")
    private PropertyResolver propertyResolver;

    @ModelAttribute("prompts")
    public Map<String, String> prompts() {
        return new HashMap<>();
    }

    @GetMapping("/favorite")
    public String favoriteGet(Model model, LocationForm form) {
        var userFavoriteOptional = service.searchUserFavoById(form.getLocation());

        String location = userFavoriteOptional.map(UserFavorite::getLocation).orElse("デフォルトの場所");
        model.addAttribute("location", location);

        return "favorite";
    }

    @PostMapping("/favorite")
    public String favoritePost(Model model, LocationForm form){

        String location = (form.getLocation());
        model.addAttribute("location", location);

        return "favorite";
    }

    @PostMapping("/favorite/food")
    public String food(@RequestParam(name = "food_budget", required = false) String food_budget,
                       Model model, @ModelAttribute("prompts") Map<String, String> prompts, LocationForm form) {

        var userFavoriteOptional = service.searchUserFavoById(form.getLocation());

        String location = userFavoriteOptional.map(UserFavorite::getLocation).orElse("error");
        String foodItem = userFavoriteOptional.map(UserFavorite::getFoodItem).orElse("error");
        String foodRestaurant = userFavoriteOptional.map(UserFavorite::getFoodRestaurant).orElse("error");

        if(food_budget == null || food_budget.trim().isEmpty()) {
            food_budget = "未定";
        }



        model.addAttribute("location", location);
        model.addAttribute("food_budget", food_budget);
        model.addAttribute("response1", foodItem);
        model.addAttribute("response2", foodRestaurant);

        return "food";
    }

    @PostMapping("/favorite/accommodation")
    public String accommodation(Model model, @ModelAttribute("prompts") Map<String, String> prompts, LocationForm form) {

        var userFavoriteOptional = service.searchUserFavoById(form.getLocation());

        String location = userFavoriteOptional.map(UserFavorite::getLocation).orElse("error");
        String accommodation = userFavoriteOptional.map(UserFavorite::getAccommodation).orElse("error");

        model.addAttribute("location", location);
        model.addAttribute("response", accommodation);

        return "accommodation";
    }

    @PostMapping("/favorite/sightseeing")
    public String sightseeing(@RequestParam(name = "another_budget", required = false) String another_budget,
                              Model model, @ModelAttribute("prompts") Map<String, String> prompts, LocationForm form) {

        if(another_budget == null || another_budget.trim().isEmpty()) {
            another_budget = "未定";
        }

        var userFavoriteOptional = service.searchUserFavoById(form.getLocation());

        String location = userFavoriteOptional.map(UserFavorite::getLocation).orElse("error");
        String sightseeingPlace = userFavoriteOptional.map(UserFavorite::getSightseeingPlace).orElse("error");
        String sightseeingBuild = userFavoriteOptional.map(UserFavorite::getSightseeingBuild).orElse("error");

        model.addAttribute("location", location);
        model.addAttribute("another_budget", another_budget);
        model.addAttribute("response1", sightseeingPlace);
        model.addAttribute("response2", sightseeingBuild);

        return "sightseeing";
    }

    @PostMapping("/favorite/schedule")
    public String schedule(Model model, @ModelAttribute("prompts") Map<String, String> prompts, LocationForm form) {

        var userFavoriteOptional = service.searchUserFavoById(form.getLocation());

        String location = userFavoriteOptional.map(UserFavorite::getLocation).orElse("error");
        String schedule = userFavoriteOptional.map(UserFavorite::getSchedule).orElse("error");

        model.addAttribute("location", location);
        model.addAttribute("response", schedule);

        return "schedule";
    }

    @PostMapping("/favorite/culture")
    public String culture(Model model, @ModelAttribute("prompts") Map<String, String> prompts, LocationForm form) {

        var userFavoriteOptional = service.searchUserFavoById(form.getLocation());

        String location = userFavoriteOptional.map(UserFavorite::getLocation).orElse("error");
        String culture = userFavoriteOptional.map(UserFavorite::getCulture).orElse("error");
        String care = userFavoriteOptional.map(UserFavorite::getCare).orElse("error");

        model.addAttribute("location", location);
        model.addAttribute("response1", culture);
        model.addAttribute("response2", care);

        return "culture";
    }

    @PostMapping("/favorite/weather")
    public String weather(Model model) {
        // TODO: 天気APIをここに追加する
        return "weather";
    }
}