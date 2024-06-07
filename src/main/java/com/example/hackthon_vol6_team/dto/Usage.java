package com.example.hackthon_vol6_team.dto;

import lombok.Data;

@Data
public class Usage {
    public int prompt_tokens;
    public int completion_tokens;
    public int total_tokens;

}

