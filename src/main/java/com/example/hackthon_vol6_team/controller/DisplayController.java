package com.example.hackthon_vol6_team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.hackthon_vol6_team.service.ChatGptService;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("prompts")
public class DisplayController {

    @Autowired
    private ChatGptService chatGptService;

    @ModelAttribute("prompts")
    public Map<String, String> prompts() {
        return new HashMap<>();
    }

    @GetMapping("/display")
    public String displayGet(@RequestParam(name = "location", required = false) String location, Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
        if (location == null || location.trim().isEmpty()) {
            model.addAttribute("error", "場所名を入力してください。");
            return "home";
        } else {model.addAttribute("location", location);
            return "display";
        }
    }

    @PostMapping("/display")
    public String displayPost(@RequestParam(name = "location") String location, Model model,
                              @RequestParam(name = "food_budget", required = false) String food_budget,
                              @RequestParam(name = "another_budget", required = false) String another_budget,
                              @RequestParam(name = "schedule", required = false) String schedule,
                              @ModelAttribute("prompts") Map<String, String> prompts) {
        if (location == null || location.trim().isEmpty()) {
            model.addAttribute("error", "場所名を入力してください。");
            return "home";
        } else {
            if(prompts.isEmpty()) {
                String foodPrompt = """
                        あなたは食事プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                        おすすめの食事:
                        おすすめのレストラン:
                        """ + location + "の付近について。" + food_budget;

                String accommodationPrompt = """
                        あなたは宿泊プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                        おすすめの宿泊施設:
                        """ + location + "の付近について。" + another_budget;

                String sightseeingPrompt = """
                        あなたは観光地プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                        おすすめの観光地:
                        おすすめの観光施設:
                        """ + location + "の付近について。";

                String schedulePrompt = """
                        あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                        旅行のスケジュール:
                        """ + location + "の付近について。" + schedule;

                String culturePrompt = """
                        あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
                        それ以外のことには答えないでください。
                        その地域での文化:
                        気を付けたほうがいいこと:
                        """ + location + "の付近について。";

                prompts.put("food", chatGptService.getChatGptResponse(foodPrompt));
                prompts.put("accommodation", chatGptService.getChatGptResponse(accommodationPrompt));
                prompts.put("sightseeing", chatGptService.getChatGptResponse(sightseeingPrompt));
                prompts.put("schedule", chatGptService.getChatGptResponse(schedulePrompt));
                prompts.put("culture", chatGptService.getChatGptResponse(culturePrompt));
            }
            model.addAttribute("location", location);
            return "display";
        }
    }

    @PostMapping("/display/food")
    public String food(@RequestParam(name = "location") String location,
                       @RequestParam(name = "food_budget", required = false) String food_budget,
                       Model model, @ModelAttribute("prompts") Map<String, String> prompts) {

        model.addAttribute("location", location);
        model.addAttribute("food_budget", food_budget);
        model.addAttribute("response", prompts.get("food"));

        return "food";
    }

    @PostMapping("/display/accommodation")
    public String accommodation(@RequestParam(name = "location") String location,
                                Model model, @ModelAttribute("prompts") Map<String, String> prompts) {

        model.addAttribute("location", location);
        model.addAttribute("response", prompts.get("accommodation"));

        return "accommodation";
    }

    @PostMapping("/display/sightseeing")
    public String sightseeing(@RequestParam(name = "location") String location,
                              @RequestParam(name = "another_budget", required = false) String another_budget,
                              Model model, @ModelAttribute("prompts") Map<String, String> prompts) {

        model.addAttribute("location", location);
        model.addAttribute("another_budget", another_budget);
        model.addAttribute("response", prompts.get("sightseeing"));

        return "sightseeing";
    }

    @PostMapping("/display/schedule")
    public String schedule(@RequestParam(name = "location") String location,
                           Model model, @ModelAttribute("prompts") Map<String, String> prompts) {

        model.addAttribute("location", location);
        model.addAttribute("response", prompts.get("schedule"));

        return "schedule";
    }

    @PostMapping("/display/culture")
    public String culture(@RequestParam(name = "location") String location,
                          Model model, @ModelAttribute("prompts") Map<String, String> prompts) {

        model.addAttribute("location", location);
        model.addAttribute("response", prompts.get("culture"));

        return "culture";
    }

    @PostMapping("/display/weather")
    public String weather(Model model) {
        // TODO: 天気APIをここに追加する
        return "weather";
    }
}

