package com.example.hackthon_vol6_team.controller;


import com.example.hackthon_vol6_team.service.FavoriteService;
import com.example.hackthon_vol6_team.service.ResistUserFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class FavoriteController {

    @Autowired
    private final FavoriteService favoriteService;
    @Autowired
    private ResistUserFavoriteService resistUserFavoriteService;

}
