package com.example.hackthon_vol6_team.util;

import org.springframework.context.MessageSource;

import java.util.Locale;

/**
 * アプリケーション共通クラス
 *
 * @author fuj1o
 *
 */

public class AppUtil {

    /**
     *メッセージ取得
     *
     * @param messageSource　メッセージソース
     * @param key メッセージキー
     * @param params 置換文字群(可変引数)
     * @return メッセージ
     */
    public static String getMessage(MessageSource messageSource, String key, Object... params) {
        return messageSource.getMessage(key, params, Locale.JAPAN);
    }
}
