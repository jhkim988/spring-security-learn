package com.ss.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.ss.entity.User;

@Mapper
public interface UserRepository {
	Optional<User> findUserByUsername(String username);
	int save(User user);
}
