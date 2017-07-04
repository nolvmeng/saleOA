package com.haishan.saleoa.domain;

import java.sql.Date;

public class Good {
	
	public String getGoodId() {
		return goodId;
	}
	public void setGoodId(String goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public float getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(float goodPrice) {
		this.goodPrice = goodPrice;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public int getReserve() {
		return reserve;
	}
	public void setReserve(int reserve) {
		this.reserve = reserve;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getExist() {
		return exist;
	}
	public void setExist(String exist) {
		this.exist = exist;
	}
	
	public String toSqlString(){
		return    "goodId=" + goodId + " goodName=\"" + goodName
				+ "\", goodPrice=" + goodPrice + ", productDate=" + productDate
				+ ", reserve=" + reserve + ", category=\"" + category+"\"" ;
	}
	
	
	@Override
	public String toString() {
		return    "goodId=" + goodId + ", goodName=" + goodName
				+ ", goodPrice=" + goodPrice + ", productDate=" + productDate
				+ ", reserve=" + reserve + ", category=" + category ;
	}


	private String goodId;
	private String goodName;
	private float goodPrice;
	private Date productDate ;
	private int reserve;
	private String category;
	private String exist;
	
	

}
