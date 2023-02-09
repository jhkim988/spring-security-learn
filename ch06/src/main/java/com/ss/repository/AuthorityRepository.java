package com.ss.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ss.entity.Authority;
import com.ss.entity.User;

@Mapper
public interface AuthorityRepository {
	List<Authority> findByUser(User user);
}
