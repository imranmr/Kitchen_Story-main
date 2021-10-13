package com.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.model.ProductDetail;

public interface ProductDetailRepository extends CrudRepository<ProductDetail, Long> {
	public ProductDetail findByProductProductid(long productid);
}
