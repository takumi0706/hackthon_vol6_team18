package com.example.hackthon_vol6_team.service;

import com.example.hackthon_vol6_team.entity.UserInfo;
import com.example.hackthon_vol6_team.repositry.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserInfoRepository repository;

    public Optional<UserInfo>searchUserById(String loginId) {
        return repository.findById(loginId);
    }
}
