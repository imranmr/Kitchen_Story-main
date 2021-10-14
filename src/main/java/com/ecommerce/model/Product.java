package com.ecommerce.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cartitem"})
public class Product {
	@Id @GeneratedValue
	@Column(name="productid")
	private long productid;
	
	
	@ManyToOne
	@JoinColumn(name="userid")
	@JsonIgnoreProperties("product")
	private User user;
	
	@Column(name="productname")
	private String productname;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@JsonIgnoreProperties("product")
    private List<ProductDetail> productdetail;
	
	@Column(name="price")
	private float price;
	
	@Column(name="productquantity")
	private int productquantity;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@JsonIgnoreProperties("product")
    private List<CartItem> cartitem;
	
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	@JsonIgnoreProperties("product")
    private List<OrderItem> orderitem;

	public long getProductid() {
		return productid;
	}

	public void setProductid(long productid) {
		this.productid = productid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}


	public List<ProductDetail> getProductdetail() {
		return productdetail;
	}

	public void setProductdetail(List<ProductDetail> productdetail) {
		this.productdetail = productdetail;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getProductquantity() {
		return productquantity;
	}

	public void setProductquantity(int productquantity) {
		this.productquantity = productquantity;
	}

	public List<CartItem> getCartitem() {
		return cartitem;
	}

	public void setCartitem(List<CartItem> cartitem) {
		this.cartitem = cartitem;
	}

	public List<OrderItem> getOrderitem() {
		return orderitem;
	}

	public void setOrderitem(List<OrderItem> orderitem) {
		this.orderitem = orderitem;
	}
	
	
	
}
