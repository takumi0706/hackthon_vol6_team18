package com.example.hackthon_vol6_team.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.hackthon_vol6_team.service.ChatGptService;

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
            ChatGptService chatGptService = new ChatGptService();
            String prompt = """
            あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
            
            - 目的地:
            - 推奨アクティビティ:
            - 最適な旅行時期:
            - 注意事項:
            """;
            String response = chatGptService.getChatGptResponse(prompt + location);
            model.addAttribute("location", location);
            model.addAttribute("response", response);
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
