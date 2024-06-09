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
                        {"foodItem",
                        "食事プランナーアシスタント",
                        "おすすめの食事（この文字は表示しなくてよい）",
                        """
                        ### 料理の名前 \n
                        特徴(コロンつけずに下に箇条書きで出力）
                        """,
                        food_budget},

                        {"foodRestaurant",
                        "食事プランナーアシスタント",
                        "おすすめのレストラン（この文字は表示しなくてよい）",
                        """
                        ### お店の名前 \n
                        住所:お店の住所(お店のgoogle mapのURL) \n
                        特徴(コロンつけずに下に箇条書きで出力）
                        """,
                        food_budget},

                        {"sightseeingPlace",
                        "観光地プランナーアシスタント",
                        "おすすめの観光地（この文字は表示しなくてよい）",
                        """
                        ### 観光地の名前 \n
                        住所:観光地の住所(観光地のgoogle mapのURL) \n
                        特徴(コロンつけずに下に箇条書きで出力）
                        """,
                        ""},

                        {"sightseeingBuild",
                        "観光地プランナーアシスタント",
                        "おすすめの観光施設（この文字は表示しなくてよい）",
                        """
                        ### 観光施設の名前 \n
                        住所:観光施設の住所(観光施設のgoogle mapのURL) \n
                        特徴(コロンつけずに下に箇条書きで出力）
                        """,
                        ""},

                        {"culture",
                        "旅行プランナーアシスタント",
                        "その地域での文化（この文字は表示しなくてよい）",
                        "",
                        ""},

                        {"care",
                        "旅行プランナーアシスタント",
                        "気を付けたほうがいいこと（この文字は表示しなくてよい）",
                        "",
                        ""},

                        {"accommodation",
                        "宿泊プランナーアシスタント",
                        "おすすめの宿泊施設（この文字は表示しなくてよい）",
                        """
                        ### 宿泊施設の名前 \n
                        住所:宿泊施設の住所(宿泊施設のgoogle mapのURL) \n
                        特徴
                        """,
                        another_budget},

                        {"schedule",
                        "旅行プランナーアシスタント",
                        "旅行のスケジュール（この文字は表示しなくてよい）",
                        "",
                        schedule}
                };

                for (String[] data : promptData) {
                    String prompt = String.format("""
                            あなたは%sです。ユーザーの質問に対して、以下の形式ですべて回答してください。
                            ユーザーの質問項目以外のことは答えないでください。
                            %s
                            %s
                            %sの付近について。%s
                            """, data[1], data[2], data[3],location, data[4]);
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

        if(food_budget == null || food_budget.trim().isEmpty()) {
            food_budget = "未定";
        }

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

        if(another_budget == null || another_budget.trim().isEmpty()) {
            another_budget = "未定";
        }

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
