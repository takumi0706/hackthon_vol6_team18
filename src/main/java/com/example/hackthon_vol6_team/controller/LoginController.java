package com.example.hackthon_vol6_team.controller;

import com.example.hackthon_vol6_team.form.LoginForm;
import com.example.hackthon_vol6_team.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping("/login")
    public String loginView(Model model){
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String  login(Model model,LoginForm form) {
        var userInfo = service.searchUserById(form.getLoginId());
        var isCorrectUserAuth = userInfo.isPresent() && form.getPassword().equals(userInfo.get().getPassword());
        if(isCorrectUserAuth) {
            return "redirect:/menu";
        } else {
            model.addAttribute("errorMsg", "ログインIDとパスワードの組み合わせが間違っています。");
            return "login";
        }
    }

    @GetMapping("/menu")
    public String view(){
        return "menu";
    }
}