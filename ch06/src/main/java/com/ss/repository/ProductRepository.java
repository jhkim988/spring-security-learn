package com.ss.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ss.entity.Product;

@Mapper
public interface ProductRepository {
	List<Product> findAll();
}
