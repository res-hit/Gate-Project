package com.RestClientPalocs.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PricingTypeItem {

	@JsonProperty("price")
	private double price;

	@JsonProperty("description")
	private String description;

	@JsonProperty("type")
	private String type;

	public PricingTypeItem() {}

	public PricingTypeItem(String type,
                           String description,
                           double price) {
		this.type = type;
		this.description = description;
		this.price = price;
	}

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	@Override
	public int hashCode() {
		int res = price > 0 ? (int)price : 0;
		res*=51;
		res+=description.length();
		res*=51;
		res+=type.length();
		return res;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		PricingTypeItem that = (PricingTypeItem) o;
		return type.equals(that.type) &&
				description.equals(that.description) &&
				price == that.price;
	}

	@Override
 	public String toString(){
		return 
			"{" +
			"price : " + price + '\n' +
			",description : '" + description + "'\n" +
			",type : '" + type + "'\n" +
			"}";
		}
}