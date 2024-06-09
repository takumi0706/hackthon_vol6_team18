package com.example.hackthon_vol6_team.repositry;

import com.example.hackthon_vol6_team.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ユーザー情報テーブルDAO
 *
 * @author fuj1o
 *
 */
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

}