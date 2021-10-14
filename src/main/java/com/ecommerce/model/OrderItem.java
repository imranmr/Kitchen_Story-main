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
@Table(name="orderitem")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","orders"})
public class OrderItem {
	@Id @GeneratedValue
	@Column(name="orderitem")
	private long orderitemid;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="orderid")
	@JsonIgnoreProperties("orderitem")
	private Orders orders;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productid")
	@JsonIgnoreProperties("orderitem")
	private Product product;
	
	@Column(name="orderitemquantity")
	private int orderitemquantity;
	
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

	public long getOrderitemid() {
		return orderitemid;
	}

	public void setOrderitemid(long orderitemid) {
		this.orderitemid = orderitemid;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getOrderitemquantity() {
		return orderitemquantity;
	}

	public void setOrderitemquantity(int orderitemquantity) {
		this.orderitemquantity = orderitemquantity;
	}

	
	
	
}
