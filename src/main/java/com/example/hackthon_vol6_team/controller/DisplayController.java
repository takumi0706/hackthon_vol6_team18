//package com.example.hackthon_vol6_team.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.core.env.PropertyResolver;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import com.example.hackthon_vol6_team.service.ChatGptService;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Controller
//@SessionAttributes("prompts")
//public class DisplayController {
//
//    @Autowired
//    private ChatGptService chatGptService;
//    @Qualifier("propertyResolver")
//    @Autowired
//    private PropertyResolver propertyResolver;
//
//    @ModelAttribute("prompts")
//    public Map<String, String> prompts() {
//        return new HashMap<>();
//    }
//
//    @GetMapping("/display")
//    public String displayGet(@RequestParam(name = "location", required = false) String location, Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
//        if (location == null || location.trim().isEmpty()) {
//            model.addAttribute("error", "場所名を入力してください。");
//            return "home";
//        } else {model.addAttribute("location", location);
//            return "display";
//        }
//    }
//
//    @PostMapping("/display")
//    public String displayPost(@RequestParam(name = "location") String location, Model model,
//                              @RequestParam(name = "food_budget", required = false) String food_budget,
//                              @RequestParam(name = "another_budget", required = false) String another_budget,
//                              @RequestParam(name = "schedule", required = false) String schedule,
//                              @ModelAttribute("prompts") Map<String, String> prompts) {
//        if (location == null || location.trim().isEmpty()) {
//            model.addAttribute("error", "場所名を入力してください。");
//            return "home";
//        } else {
//            if(prompts.isEmpty()) {
//                String foodItemPrompt = """
//                        あなたは食事プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        おすすめの食事:
//                        """ + location + "の付近について。" + food_budget;
//
//                String foodRestaurantPrompt = """
//                        あなたは食事プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        おすすめのレストラン:
//                        """ + location + "の付近について。" + food_budget;
//
//
//                String sightseeingPlacePrompt = """
//                        あなたは観光地プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        おすすめの観光地:
//                        """ + location + "の付近について。";
//
//                String sightseeingBuildPrompt = """
//                        あなたは観光地プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        おすすめの観光施設:
//                        """ + location + "の付近について。";
//
//
//                String culturePrompt = """
//                        あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        それ以外のことには答えないでください。
//                        その地域での文化:
//                        """ + location + "の付近について。";
//
//                String carePrompt = """
//                        あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        それ以外のことには答えないでください。
//                        気を付けたほうがいいこと:
//                        """ + location + "の付近について。";
//
//                String accommodationPrompt = """
//                        あなたは宿泊プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        おすすめの宿泊施設:
//                        """ + location + "の付近について。" + another_budget;
//
//                String schedulePrompt = """
//                        あなたは旅行プランナーアシスタントです。ユーザーの質問に対して、以下の形式で回答してください。
//                        旅行のスケジュール:
//                        """ + location + "の付近について。" + schedule;
//
//
//                prompts.put("foodItem", chatGptService.getChatGptResponse(foodItemPrompt));
//                prompts.put("foodRestaurant", chatGptService.getChatGptResponse(foodRestaurantPrompt));
//                prompts.put("sightseeingPlace", chatGptService.getChatGptResponse(sightseeingPlacePrompt));
//                prompts.put("sightseeingBuild", chatGptService.getChatGptResponse(sightseeingBuildPrompt));
//                prompts.put("culture", chatGptService.getChatGptResponse(culturePrompt));
//                prompts.put("care", chatGptService.getChatGptResponse(carePrompt));
//                prompts.put("accommodation", chatGptService.getChatGptResponse(accommodationPrompt));
//                prompts.put("schedule", chatGptService.getChatGptResponse(schedulePrompt));
//            }
//            model.addAttribute("location", location);
//            return "display";
//        }
//    }
//
//    @PostMapping("/display/food")
//    public String food(@RequestParam(name = "location") String location,
//                       @RequestParam(name = "food_budget", required = false) String food_budget,
//                       Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
//
//        model.addAttribute("location", location);
//        model.addAttribute("food_budget", food_budget);
//        model.addAttribute("response1", prompts.get("food"));
//        model.addAttribute("response2", prompts.get("food"));
//
//        return "food";
//    }
//
//    @PostMapping("/display/accommodation")
//    public String accommodation(@RequestParam(name = "location") String location,
//                                Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
//
//        model.addAttribute("location", location);
//        model.addAttribute("response", prompts.get("accommodation"));
//
//        return "accommodation";
//    }
//
//    @PostMapping("/display/sightseeing")
//    public String sightseeing(@RequestParam(name = "location") String location,
//                              @RequestParam(name = "another_budget", required = false) String another_budget,
//                              Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
//
//        model.addAttribute("location", location);
//        model.addAttribute("another_budget", another_budget);
//        model.addAttribute("response", prompts.get("sightseeing"));
//
//        return "sightseeing";
//    }
//
//    @PostMapping("/display/schedule")
//    public String schedule(@RequestParam(name = "location") String location,
//                           Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
//
//        model.addAttribute("location", location);
//        model.addAttribute("response", prompts.get("schedule"));
//
//        return "schedule";
//    }
//
//    @PostMapping("/display/culture")
//    public String culture(@RequestParam(name = "location") String location,
//                          Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
//
//        model.addAttribute("location", location);
//        model.addAttribute("response", prompts.get("culture"));
//
//        return "culture";
//    }
//
//    @PostMapping("/display/weather")
//    public String weather(Model model) {
//        // TODO: 天気APIをここに追加する
//        return "weather";
//    }
//}
//



package com.example.hackthon_vol6_team.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.PropertyResolver;
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

    @Autowired
    @Qualifier("propertyResolver")
    private PropertyResolver propertyResolver;

    @ModelAttribute("prompts")
    public Map<String, String> prompts() {
        return new HashMap<>();
    }

    @GetMapping("/display")
    public String displayGet(@RequestParam(name = "location", required = false) String location, Model model, @ModelAttribute("prompts") Map<String, String> prompts) {
        if (location == null || location.trim().isEmpty()) {
            model.addAttribute("error", "場所名を入力してください。");
            return "home";
        } else {
            model.addAttribute("location", location);
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
            if (prompts.isEmpty()) {
                String[][] promptData = {
                        {"foodItem", "食事プランナーアシスタント", "おすすめの食事:", food_budget},
                        {"foodRestaurant", "食事プランナーアシスタント", "おすすめのレストラン:", food_budget},
                        {"sightseeingPlace", "観光地プランナーアシスタント", "おすすめの観光地:", ""},
                        {"sightseeingBuild", "観光地プランナーアシスタント", "おすすめの観光施設:", ""},
                        {"culture", "旅行プランナーアシスタント", "その地域での文化:", ""},
                        {"care", "旅行プランナーアシスタント", "気を付けたほうがいいこと:", ""},
                        {"accommodation", "宿泊プランナーアシスタント", "おすすめの宿泊施設:", another_budget},
                        {"schedule", "旅行プランナーアシスタント", "旅行のスケジュール:", schedule}
                };

                for (String[] data : promptData) {
                    String prompt = String.format("""
                            あなたは%sです。ユーザーの質問に対して、以下の形式で回答してください。
                            %s
                            %sの付近について。%s
                            """, data[1], data[2], location, data[3]);
                    prompts.put(data[0], chatGptService.getChatGptResponse(prompt));
                }
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
        model.addAttribute("response1", prompts.get("foodItem"));
        model.addAttribute("response2", prompts.get("foodRestaurant"));

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
        model.addAttribute("response1", prompts.get("sightseeingPlace"));
        model.addAttribute("response2", prompts.get("sightseeingBuild"));

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
        model.addAttribute("response1", prompts.get("culture"));
        model.addAttribute("response2", prompts.get("care"));

        return "culture";
    }

    @PostMapping("/display/weather")
    public String weather(Model model) {
        // TODO: 天気APIをここに追加する
        return "weather";
    }
}
