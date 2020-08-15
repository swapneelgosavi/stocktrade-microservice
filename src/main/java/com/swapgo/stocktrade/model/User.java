package com.swapgo.stocktrade.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class User {

	@Id
	@GeneratedValue
	private int id;
	
	@Size(min=2, message = "Name should be mininmum 2 character")  //validation to make name should be min length of 2
	private String name;
	
	@OneToMany(mappedBy = "user")
	private List<Share> shares;
	
	public User() {
	}
	
	
	public User(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Share> getShares() {
		return shares;
	}
	public void setShares(List<Share> shares) {
		this.shares = shares;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", shares=" + shares + "]";
	}
	
	
}
