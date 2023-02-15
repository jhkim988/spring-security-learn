package com.ss.sec04;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TokenRepository {
	Optional<Token> findTokenByIdentifier(String identifier);
	int save(Token token);
}
