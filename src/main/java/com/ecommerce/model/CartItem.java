package com.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="cartitem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cart"})
public class CartItem {
	@Id @GeneratedValue
	@Column(name="cartitemid")
	private long cartitemid;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="cartid")
	@JsonIgnoreProperties("cartitem")
	private Cart cart;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productid")
	@JsonIgnoreProperties("cartitem")
	private Product product;
	
	@Column(name="cartitemquantity")
	private int cartitemquantity;
	
	@Column(name="totalprice")
	private float totalprice;
	
	@Column(name="itemprice")
	private float itemprice;

	
	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice = totalprice;
	}

	public float getItemprice() {
		return itemprice;
	}

	public void setItemprice(float itemprice) {
		this.itemprice = itemprice;
	}

	public long getCartitemid() {
		return cartitemid;
	}

	public void setCartitemid(long cartitemid) {
		this.cartitemid = cartitemid;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getCartitemquantity() {
		return cartitemquantity;
	}

	public void setCartitemquantity(int cartitemquantity) {
		this.cartitemquantity = cartitemquantity;
	}

	
	
	
}
