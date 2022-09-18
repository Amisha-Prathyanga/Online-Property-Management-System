package com.wanted;

public class wanted {
	private int id;
	private String type;
	private String description;
	private String price;
	private String name;
	private String phone;
	private String location;
	
	
	public wanted(int id, String type, String description, String price, String name, String phone, String location) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.price = price;
		this.name = name;
		this.phone = phone;
		this.location = location;
	}


	public wanted(String type, String description, String price, String name, String phone, String location) {
		super();
		this.type = type;
		this.description = description;
		this.price = price;
		this.name = name;
		this.phone = phone;
		this.location = location;
	}


	public int getId() {
		return id;
	}


	public String getType() {
		return type;
	}


	public String getDescription() {
		return description;
	}


	public String getPrice() {
		return price;
	}


	public String getName() {
		return name;
	}


	public String getPhone() {
		return phone;
	}


	public String getLocation() {
		return location;
	}


	public void setId(int id) {
		this.id = id;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public void setName(String name) {
		this.name = name;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public void setLocation(String location) {
		this.location = location;
	}
	
	
}
