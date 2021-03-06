package com.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.model.Cart;

public interface CartRepository extends CrudRepository<Cart, Long> {
	public Cart findByUserUserid(long userid);
}
