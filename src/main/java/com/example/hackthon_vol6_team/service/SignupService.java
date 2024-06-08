package com.example.hackthon_vol6_team.service;

import com.example.hackthon_vol6_team.entity.UserInfo;
import com.example.hackthon_vol6_team.form.SignupForm;
import com.example.hackthon_vol6_team.repositry.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.dozer.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * ユーザ登録画面 Service
 *
 * @author fuj1o
 *
 */

@Service
@RequiredArgsConstructor
public class SignupService {

    private final UserInfoRepository repository;

    private final Mapper mapper;

    /** PasswordEncoder */
    private final PasswordEncoder passwordEncoder;

    /**
     * ユーザ情報テーブル　新規登録
     *
     * @param form 入力情報
     * @return 登録情報(ユーザ情報Entity), 既に同じユーザIDが存在する場合はEmpty
     */
    public Optional<UserInfo>resistUserInfo(SignupForm form) {

        var userInfoExistedOpt = repository.findById(form.getLoginId());
        if(userInfoExistedOpt.isPresent()){
            return Optional.empty();
        }

        var userInfo = mapper.map(form, UserInfo.class);

        var encodedPassword = passwordEncoder.encode(form.getPassword());
        userInfo.setPassword(encodedPassword);

        return Optional.of(repository.save(userInfo));
    }
}