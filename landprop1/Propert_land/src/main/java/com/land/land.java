package com.land;

public class land {
	private int id;
	private String type;
	private String description;
	private String price;
	
	
	public land(int id, String type, String description, String price) {
		super();
		this.id = id;
		this.type = type;
		this.description = description;
		this.price = price;
	}
	
	


	public land(String type, String description, String price) {
		super();
		this.type = type;
		this.description = description;
		this.price = price;
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
	
	
	
	
}
