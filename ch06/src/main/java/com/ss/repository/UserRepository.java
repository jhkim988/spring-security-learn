package com.ss.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.ss.dto.Register;
import com.ss.entity.User;

@Mapper
public interface UserRepository {
	Optional<User> findUserByUsername(String username);
	boolean insert(Register.Request request);
}
