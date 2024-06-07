package com.example.hackthon_vol6_team.dto;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptRequest {
    private String model;
    private List<Message> messages;
    private Integer max_tokens;

}