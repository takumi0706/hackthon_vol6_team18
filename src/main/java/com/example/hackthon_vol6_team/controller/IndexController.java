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


//            TODO:日程調整も追加したい。

    @PostMapping("/display")
    public String display(@RequestParam("location") String location,
                          @RequestParam("food_budget") String food_budget,
                          @RequestParam("another_budget") String another_budget,
                          Model model) {
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
    public String food(@RequestParam("location") String location,
                       @RequestParam("food_budget") String food_budget,
                       Model model) {

        String prompt = """
                あなたは食事プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。(とりあえず何かしらの具体的な食事とレストランを予算に合わせて出力してください。レストランの場合は、そのレストランの名前と場所を出力してください。)
           
                おすすめの食事:
                
                おすすめのレストラン:
                """;

        String response = chatGptService.getChatGptResponse(prompt + location + food_budget);
        model.addAttribute("location", location);
//        TODO: 予算の値を表示するために追加したんだけどうまくHtml上で表示されない。ぴえん
        model.addAttribute("food_budget", food_budget);
        model.addAttribute("response", response);

        return "food";
    }

    @PostMapping("/display/accommodation")
    public String accommodation(@RequestParam("location") String location,
                                @RequestParam("another_budget") String another_budget,
                                Model model) {

        String prompt = """
                あなたは宿泊プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                それ以外のことは無視してください。
               
                おすすめの宿泊施設:
                おすすめのレストラン:
                """;
        String response = chatGptService.getChatGptResponse(prompt + location +another_budget);
        model.addAttribute("location", location);
        model.addAttribute("response", response);

        return "food";
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
