package com.haishan.saleoa.domain;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 封装订单单项
 * */

public class OrderItem {

	private List<Good> goodList; //订单货品列表
	private List<Shipment> shipmentList ;
	private String userId ;  //订单操作员编号
	private Customer customer ; //订单客户
	private double totalPrice;//总金额
	private Order order;
      

      public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
      
    public List<Good> getGoodList() {
		return goodList;
	}
	public void setGoodList(List<Good> goodList) {
		this.goodList = goodList;
	}
	public double getTotalPrice() {
		double money  = 0.0 ;
		for(Shipment item : shipmentList) {
			money += item.getTotalPrice();
			
		}
		return money;
	}
	public String getTatalPriceS() {
		double money  = 0.0 ;
		for(Shipment item : shipmentList) {
			money += item.getTotalPrice();
			//System.out.println("getTatalPriceS插桩测试"+money);
			}
		DecimalFormat    df   = new DecimalFormat("######0.00");  
	
		return 	df.format(money);
	}
	
	
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	  
	public List<Shipment> getShipmentList() {
		return shipmentList;
	}
	public void setShipmentList(List<Shipment> shipmentList) {
		this.shipmentList = shipmentList;
	}
	  
	
	
	
}
