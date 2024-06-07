package com.example.hackthon_vol6_team.dto;

import lombok.Data;

@Data
public class Choice {
    public int index;
    public Message message;
    public String finish_reason;

}

