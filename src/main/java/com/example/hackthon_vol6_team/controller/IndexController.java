package com.example.hackthon_vol6_team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hackthon_vol6_team.service.ChatGptService;

@Controller
public class IndexController {

    @Autowired
    private ChatGptService chatGptService;


    @PostMapping("/home")
    public String home(@RequestParam(value = "location", required = false) String location, Model model) {
        if (location != null) {
            model.addAttribute("location", location);
        }
        return "home";
    }

        @GetMapping("/about")
    public String about() {

        return "about";
    }

    @GetMapping("/contact")
    public String contact() {

        return "contact";
    }
}
