package com.example.hackthon_vol6_team.controller;

import com.example.hackthon_vol6_team.constant.MessageConst;
import com.example.hackthon_vol6_team.entity.UserInfo;
import com.example.hackthon_vol6_team.form.SignupForm;
import com.example.hackthon_vol6_team.service.SignupService;
import com.example.hackthon_vol6_team.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * ユーザ登録画面　Controller
 *
 * @author fuj1o
 *
 */
@Controller
@RequiredArgsConstructor
public class SignupController {

    /** ユーザ登録画面　Service */
    private final SignupService service;

    /* メッセージソース */
    private final MessageSource messageSource;

    /**
     * 初期画面
     *
     * @param model モデル
     * @param form 入力情報
     * @return 表示画面
     */
    @GetMapping("/signup")
    public String loginView(Model model, SignupForm form){
        return "signup";
    }

    /**
     * ユーザ登録
     *
     * @param model モデル
     * @param bdResult 入力チェック結果
     * @param form 入力情報
     */
    @PostMapping("/signup")
    public void  signup(Model model,@Validated SignupForm form, BindingResult bdResult) {
        if(bdResult.hasErrors()) {
            var message = AppUtil.getMessage(messageSource,MessageConst.FORM_ERROR);
            model.addAttribute("message",message);
            return ;
        }
        Optional<UserInfo> userInfoOpt = service.resistUserInfo(form);
        var message = AppUtil.getMessage(messageSource,judgeMessageKey(userInfoOpt));
        model.addAttribute("message", message);
}

/**
 * ユーザ登録
 *
 * @param userInfoOpt ユーザ登録結果
 * @return メッセージキー
 */
private String judgeMessageKey(Optional<UserInfo> userInfoOpt) {
    if (userInfoOpt.isEmpty()) {
        return MessageConst.SIGNUP_EXISTED_LOGIN_ID;
    } else {
        return MessageConst.SIGNUP_RESIST_SUCCEED;
    }
  }
}