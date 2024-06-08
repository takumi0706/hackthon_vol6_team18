package com.example.hackthon_vol6_team.controller;

import com.example.hackthon_vol6_team.constant.MessageConst;
import com.example.hackthon_vol6_team.form.LoginForm;
import com.example.hackthon_vol6_team.service.LoginService;
import com.example.hackthon_vol6_team.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * ログイン画面　Controller
 *
 * @author fuj1o
 *
 */
@Controller
@RequiredArgsConstructor
public class LoginController {

    /** ログイン画面　Service */
    private final LoginService service;

    /** PasswordEncoder */
    private final PasswordEncoder PasswordEncoder;

    /* メッセージソース */
    private final MessageSource messageSource;

    /**
     * 初期画面
     *
     * @param model モデル
     * @return 表示画面
     */
    @GetMapping("/")
    public String loginView(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    /**
     *
     * @param model モデル
     * @param form 入力情報
     * @return 表示画面
     */
    @PostMapping("/")
    public String  login(Model model,LoginForm form) {
        var userInfo = service.searchUserById(form.getLoginId());
        // TODO:パスワードはハッシュ化したものを使用する
        var isCorrectUserAuth = userInfo.isPresent() && PasswordEncoder.matches(form.getPassword(), userInfo.get().getPassword());
        if(isCorrectUserAuth) {
            return "redirect:/home";
        } else {
            var errorMsg = AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_INPUT);
            model.addAttribute("message", errorMsg);
            return "login";
        }
    }
}