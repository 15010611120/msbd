package com.yxd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yxd.model.Product;

@Service
public interface ProductService {

	/**
	 * 添加产品
	 * @param o
	 * @return
	 */
	public int add(Product o);
	
	/**
	 * 查询所有产品
	 * @return
	 */
	public List<Product> findAll();
}
