package com.example.hackthon_vol6_team.repositry;

import com.example.hackthon_vol6_team.entity.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite,String> {

}
