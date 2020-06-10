package com.RestClientPalocs.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReservationRequest{

	@JsonProperty("numberOfPeople")
	private int numberOfPeople;

	@JsonProperty("ReservationOwner")
	private String reservationOwner;

	@JsonProperty("self")
	private String self;

	@JsonProperty("hourSlot")
	private String accessSlot;

	@JsonProperty("event_id")
	private String eventId;

	@JsonProperty("id")
	private int id;

	@JsonProperty("pricingType")
	private String pricingType;

	@JsonProperty("price")
	private double price;

	@JsonProperty("date")
	private String date;

	public ReservationRequest(){

	}

	public ReservationRequest(int numberOfPeople,
							  String reservationOwner,
							  String self,
							  String eventId,
							  String accessSlot,
							  int id,
							  String pricingType,
							  double price,
							  String date){
		this.numberOfPeople=numberOfPeople;
		this.reservationOwner=reservationOwner;
		this.id=id;
		this.eventId=eventId;
		this.self=self;
		this.accessSlot=accessSlot;
		this.pricingType=pricingType;
		this.price = price;
		this.date = date;
	}

	public void setReservationOwner(String reservationOwner) { this.reservationOwner = reservationOwner; }

	public String getReservationOwner() { return reservationOwner; }

	public void setDate(String date) { this.date = date; }

	public String getDate() { return date; }

	public void setAccessSlot(String accessSlot) { this.accessSlot = accessSlot; }

	public String getAccessSlot() { return accessSlot; }

	public void setPrice(double price){
		this.price = price;
	}

	public double getPrice(){
		return price;
	}

	public void setPricingType(String pricingType) { this.pricingType = pricingType; }

	public String getPricingType() { return pricingType; }

	public void setSelf(String self) { this.self = self; }

	public String getSelf() { return self; }

	public void setEventId(String eventId){
		this.eventId = eventId;
	}

	public String getEventId(){
		return eventId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNumberOfPeople(int people){
		this.numberOfPeople = people;
	}

	public int getNumberOfPeople(){
		return numberOfPeople;
	}

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		ReservationRequest that = (ReservationRequest) o;
		return self.equals(that.self) &&
				numberOfPeople == that.numberOfPeople &&
				eventId.equals(that.eventId) &&
				reservationOwner.equals(that.reservationOwner) &&
				id == that.id &&
				pricingType.equals(that.pricingType) &&
				accessSlot.equals(that.accessSlot) &&
				this.price == that.price &&
				this.date.equals(that.date);
	}

	@Override
	public int hashCode(){
		int res = eventId != null ? eventId.hashCode() : 0;
		res*=51;
		res+=numberOfPeople;
		res*=51;
		res+=id;
		return res;
	}

	@Override
	public String toString(){
		return
				"{\n" +
						"self : '" + self + "'\n" +
						",eventId : '" + eventId + "' \n" +
						",id : " + id + '\n' +
						",numberOfPeople : " + numberOfPeople + '\n' +
						",reservationOwner : '" + reservationOwner + "'\n" +
						",pricingType : '" + pricingType + "'\n" +
						",accessSlot : '" + accessSlot + "'\n" +
						",price : " + price + "\n" +
						",date : '" + date + "'\n" +
				"}";
	}
}