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
    public String home(@RequestParam(value = "location", required = false) String location, Model model) {
        if (location != null) {
            model.addAttribute("location", location);
        }
        return "home";
    }

//            TODO:日程調整も追加したい。

    @PostMapping("/display")
    public String display(@RequestParam(name="location") String location,
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
    public String food(@RequestParam(name="location") String location,
                       @RequestParam(name="food_budget", required = false) String food_budget,
                       Model model) {

        String prompt = """
                あなたは食事プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。(とりあえず何かしらの具体的な食事とレストランを予算に合わせて出力してください。レストランの場合は、そのレストランの名前と場所を出力してください。)
                それ以外のことは無視してください。
                
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
    public String accommodation(@RequestParam(name="location") String location,
                                @RequestParam(name="another_budget", required = false) String another_budget,
                                Model model) {

        String prompt = """
                あなたは宿泊プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                それ以外のことは無視してください。
               
                おすすめの宿泊施設:
                """;
        String response = chatGptService.getChatGptResponse(prompt + location +another_budget);
        model.addAttribute("location", location);
        model.addAttribute("response", response);

        return "accommodation";
    }

    @PostMapping("/display/sightseeing")
    public String sightseeing(@RequestParam(name="location") String location,
                              @RequestParam(name="another_budget", required = false) String another_budget,
                              Model model) {

        String prompt = """
                あなたは観光地プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                それ以外のことは無視してください。
               
                おすすめの観光地:
                おすすめの観光施設:
                """;
        String response = chatGptService.getChatGptResponse(prompt + location +another_budget);
        model.addAttribute("location", location);
        model.addAttribute("another_budget", another_budget);
        model.addAttribute("response", response);

        return "sightseeing";
    }

    @PostMapping("/display/schedule")
    public String schedule(@RequestParam(name="location") String location,
                           @RequestParam(name="longDays", required = false) String longDays,
                           Model model) {

        String prompt = """
                あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                それ以外のことは無視してください。
               
                旅行のスケジュール:
                """;
        String response = chatGptService.getChatGptResponse(prompt + location + longDays);
        model.addAttribute("location", location);
        model.addAttribute("response", response);

        return "schedule";
    }

    @PostMapping("/display/culture")
    public String culture(@RequestParam(name="location") String location,
                          @RequestParam(name="longDays",required = false) String longDays,
                          Model model) {

        String prompt = """
                あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                それ以外のことは無視してください。
               
                その地域での文化:
                気を付けたほうがいいこと:
                """;
        String response = chatGptService.getChatGptResponse(prompt + location +longDays);
        model.addAttribute("location", location);
        model.addAttribute("response", response);

        return "culture";
    }

    @PostMapping("/display/weather")
    public String weather(Model model) {
//TODO: ここに天気APIを追加するなのかな？
        return "weather";
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
