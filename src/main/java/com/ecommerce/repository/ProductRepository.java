package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.model.Product;

public interface ProductRepository extends CrudRepository<Product, Long> {
	public List<Product> findByProductname(String name);
}
