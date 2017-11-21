package com.yxd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yxd.mapper.ProductMapper;
import com.yxd.model.Product;
import com.yxd.service.ProductService;

@Service
@Transactional(rollbackFor=java.lang.Exception.class)
public class ProductServiceImpl implements ProductService {
	@Autowired
	public ProductMapper productMapper;
	
	/**
	 * 添加
	 */
	public int add(Product o) {
		return productMapper.insert(o);
	}
	
	/**
	 * 查询
	 */
	public List<Product> findAll() {
		return productMapper.selectAll();
	}

}
