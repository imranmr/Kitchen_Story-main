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
@Table(name="productdetail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","product"})
public class ProductDetail {
	@Id @GeneratedValue
	@Column(name="productdetailid")
	private long productdetailid;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="productid")
	@JsonIgnoreProperties("productdetail")
	private Product product;
	
	@Column(name="type")
	private String type;

	public long getProductdetailid() {
		return productdetailid;
	}

	public void setProductdetailid(long productdetailid) {
		this.productdetailid = productdetailid;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
