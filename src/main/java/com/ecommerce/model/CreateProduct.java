package com.ecommerce.model;

public class CreateProduct {
	private long userid;
	private String productname;
	private float price;
	private int productquantity;
	private String type;
	
	
	
	public long getUserid() {
	return userid;}
	public void setUserid(long userid) {
	this.userid = userid;}
	public String getProductname() {
	return productname;}
	public void setProductname(String productname) {
	this.productname = productname;}
	public float getPrice() {
	return price;}
	public void setPrice(float price) {
	this.price = price;}
	public int getProductquantity() {
	return productquantity;}
	public void setProductquantity(int productquantity) {
	this.productquantity = productquantity;}
	public String getType() {
	return type;}
	public void setType(String type) {
	this.type = type;}
	
	
}

