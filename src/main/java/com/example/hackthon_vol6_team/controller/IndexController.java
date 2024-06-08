package com.example.hackthon_vol6_team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@Controller
@SessionAttributes("prompts")
public class IndexController {

    @ModelAttribute("prompts")
    public Map<String, String> prompts() {
        return new HashMap<>();
    }

    @PostMapping("/home")
    public String HomePost(@RequestParam(value = "location", required = false) String location, @ModelAttribute("prompts") Map<String, String> prompts, Model model) {
        if (location != null) {
            model.addAttribute("location", location);
        }
        prompts.clear();
        return "home";
    }

    @GetMapping("/home")
    public String HomeGet(@RequestParam(value = "location", required = false) String location, @ModelAttribute("prompts") Map<String, String> prompts, Model model) {
        if (location != null) {
            model.addAttribute("location", location);
        }
        prompts.clear();
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
