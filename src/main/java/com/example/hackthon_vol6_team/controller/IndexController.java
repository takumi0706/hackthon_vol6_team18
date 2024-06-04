package com.example.hackthon_vol6_team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String home() {

        return "home";
    }
    @PostMapping("/display")
    public String login() {

        return "display";
    }
}