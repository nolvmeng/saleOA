package com.haishan.saleoa.domain;

import java.text.DecimalFormat;

public class GoodItem {

	private Good good;
	private int amount;
	
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

	//构造方法
	public GoodItem(Good good, int amount){
		this.good = good;
		this.amount = amount; 
	}
	
	public String getItemMoneyS(){
		DecimalFormat    df   = new DecimalFormat("######0.00");
		
		return df.format(good.getGoodPrice() * amount);  
	}
	
	
	//��ȡ�����ܶ�
	public float getItemMoney(){
		return good.getGoodPrice() * amount;
	}
	
	
}
