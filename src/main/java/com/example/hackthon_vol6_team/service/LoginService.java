package com.example.hackthon_vol6_team.service;

import com.example.hackthon_vol6_team.entity.UserInfo;
import com.example.hackthon_vol6_team.repositry.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ログイン画面 Service
 *
 * @author fuj1o
 *
 */

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserInfoRepository repository;

    /**
     * ユーザ情報テーブル　主キー検索
     *
     * @param loginId ログインID
     * @return 検索結果
     */
    public Optional<UserInfo>searchUserById(String loginId) {
        return repository.findById(loginId);
    }
}
