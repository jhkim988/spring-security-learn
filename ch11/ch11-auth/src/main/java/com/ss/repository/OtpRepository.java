package com.ss.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

import com.ss.entity.Otp;

@Mapper
public interface OtpRepository {
	Optional<Otp> findOtpByUsername(String username);
	int save(Otp otp);
}
