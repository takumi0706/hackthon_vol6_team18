package com.example.hackthon_vol6_team.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherService {

    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private ChatGptService chatGptService;

    @Value("${openweather.api.key}")
    private String apiKey; // プロパティから読み込み

    @Value("${openweather.api.url}")
    private String apiUrl;


    public String getWeather(String location) {
        String url = String.format(apiUrl, location, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        String response;

        logger.debug("Attempting to get weather for location: {}", location);

        try {
            response = restTemplate.getForObject(url, String.class);
            return parseWeatherResponse(response);
        } catch (HttpClientErrorException.NotFound e) {
            String correctCityName = chatGptService.getCorrectCityName(location);
            logger.info("Corrected City Name: {}", correctCityName);

            url = String.format(apiUrl, correctCityName, apiKey);
            try {
                response = restTemplate.getForObject(url, String.class);
                return parseWeatherResponse(response);
            } catch (HttpClientErrorException.NotFound ex) {
                // 2回目の試行でエラーが発生した場合、再度都市名を取得して試行
                correctCityName = chatGptService.getCorrectCityName(correctCityName);
                logger.info("Second Corrected City Name: {}", correctCityName);

                url = String.format(apiUrl, correctCityName, apiKey);
                try {
                    response = restTemplate.getForObject(url, String.class);
                    return parseWeatherResponse(response);
                } catch (HttpClientErrorException.NotFound ex2) {
                    return "指定された都市が見つかりません。正しい都市名を入力してください。";
                } catch (Exception ex2) {
                    ex2.printStackTrace();
                    return "天気データの取得に失敗しました。再度確認してください。";
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                return "天気データの取得に失敗しました。再度確認してください。";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "天気データの取得に失敗しました。再度確認してください。";
        }
    }

    private String parseWeatherResponse(String response) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode root = mapper.readTree(response);
            String weatherDescription = root.path("weather").get(0).path("description").asText();
            String temperature = root.path("main").path("temp").asText();
            String cityName = root.path("name").asText();

            double kelvinTemp = Double.parseDouble(temperature);
            double celsiusTemp = kelvinTemp - 273.15;
            long roundedCelsiusTemp = Math.round(celsiusTemp);
            String formattedTemp = String.valueOf(roundedCelsiusTemp);

            String translatedDescription = translateWeatherDescription(weatherDescription);

            return String.format("都市: %s, 天気: %s, 気温: %s℃", cityName, translatedDescription, formattedTemp);
        } catch (Exception e) {
            e.printStackTrace();
            return "天気データの解析中にエラーが発生しました";
        }
    }

    private String translateWeatherDescription(String description) {
        switch (description.toLowerCase()) {
            case "clear sky":
                return "晴天";
            case "few clouds":
                return "少しだけ雲あり";
            case "scattered clouds":
                return "少し雲あり";
            case "broken clouds":
                return "所々曇り";
            case "overcast clouds":
                return "曇天";
            case "shower rain":
                return "にわか雨";
            case "rain":
                return "雨";
            case "thunderstorm":
                return "雷雨";
            case "snow":
                return "雪";
            case "mist":
                return "霧";
            default:
                try {
                    return chatGptService.translateWeatherDescription(description);
                } catch (Exception e) {
                    e.printStackTrace();
                    return description;
                }
        }
    }
}
