package com.example.hackthon_vol6_team.form;

import org.hibernate.validator.constraints.Length;
import lombok.Data;

/**
 * ユーザ登録画面 form
 *
 * @author fuj1o
 *
 */
@Data
public class SignupForm {

    @Length(min=6, max=20, message = "6文字以上20文字以下の範囲で入力してください。")
    private String loginId;

    @Length(min=8, max=20, message = "8文字以上20文字以下の範囲で入力してください。")
    private String password;
}