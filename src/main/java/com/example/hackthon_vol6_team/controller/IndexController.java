package com.example.hackthon_vol6_team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {

        return "home";
    }

    @PostMapping("/display")
    public String display(@RequestParam("location") String location, Model model) {
        if (location == null || location.trim().isEmpty()) {
            model.addAttribute("error", "場所名を入力してください。");
            return "home";
        }else {
            model.addAttribute("location", location);
            return "display";
        }

    }
    @GetMapping("/about")
    public String login() {

        return "about";
    }

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }
}
