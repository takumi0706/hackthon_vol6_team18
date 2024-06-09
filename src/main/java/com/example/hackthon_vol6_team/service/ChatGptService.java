package com.example.hackthon_vol6_team.service;

import com.example.hackthon_vol6_team.dto.ChatGptRequest;
import com.example.hackthon_vol6_team.dto.ChatGptResponse;
import com.example.hackthon_vol6_team.dto.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.Collections;
import java.util.Objects;

@Service
public class ChatGptService {

//propertiesから値を取得
    @Value("${chatgpt.api.url}")
    private String apiUrl;

    @Value("${chatgpt.api.key}")
    private String apiKey;


    public String getChatGptResponse(String prompt) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        ChatGptRequest request = new ChatGptRequest();
        request.setModel("gpt-4o");
        request.setMessages(Collections.singletonList(new Message("user", prompt)));
        request.setMax_tokens(1000);

        HttpEntity<ChatGptRequest> entity = new HttpEntity<>(request, headers);

        ResponseEntity<ChatGptResponse> response = restTemplate.postForEntity(apiUrl, entity, ChatGptResponse.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return Objects.requireNonNull(response.getBody()).getChoices().getFirst().getMessage().getContent();
        } else {
            throw new RuntimeException("Failed to get response from ChatGPT API");
        }
    }
}