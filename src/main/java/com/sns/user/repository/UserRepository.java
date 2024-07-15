package com.sns.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sns.user.entity.UserEntity;
import java.util.List;


public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	//JPQL
	public UserEntity findByLoginId(String loginId);
}
