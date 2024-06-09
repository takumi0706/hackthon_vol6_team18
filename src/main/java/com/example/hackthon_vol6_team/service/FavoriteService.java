package com.example.hackthon_vol6_team.service;

import com.example.hackthon_vol6_team.entity.UserFavorite;
import com.example.hackthon_vol6_team.entity.UserInfo;
import com.example.hackthon_vol6_team.repositry.UserFavoriteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FavoriteService {

    private final UserFavoriteRepository repository;
    public Optional<UserFavorite> searchUserFavoById(String location) {
        return repository.findById(location);
    }
}
