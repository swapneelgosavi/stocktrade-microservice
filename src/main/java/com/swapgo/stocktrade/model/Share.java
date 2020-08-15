package com.swapgo.stocktrade.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Share {

	@Id
	@GeneratedValue
	private int id;
	private float price;
	private int quantity;
	
	@Size(min=3, message = "Script name should be mininmum of 3 character")
	private String scriptName;
	
	@FutureOrPresent  //validation on date 
	private Date tradeDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
	
	
	public Share() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Date getTradeDate() {
		return tradeDate;
	}

	public void setTradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	@Override
	public String toString() {
		return "Share [id=" + id + ", price=" + price + ", quantity=" + quantity + ", scriptName=" + scriptName
				+ ", tradeDate=" + tradeDate + "]";
	}
	
}
