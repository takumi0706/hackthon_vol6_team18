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
//            TODO: APIを渡してない時の処理を導入するべきかな？
            return "display";
        }

    }

    @PostMapping("/display/food")
    public String food(@RequestParam("location") String location, Model model) {

        String prompt = """
                あなたは食事プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
               
                - 目的地:
                - おすすめの食事:
                - おすすめのレストラン:
                """;
        String response = chatGptService.getChatGptResponse(prompt + location);
        model.addAttribute("location", location);
        model.addAttribute("response", response);

        return "food";
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
