package com.RestClientPalocs.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessSlot {

	@JsonProperty("accessSlot")
	private String accessSlot;

	@JsonProperty("date")
	private String date;

	@JsonProperty("eventId")
	private int eventId;

	@JsonProperty("eventName")
	private String eventName;

	@JsonProperty("ticketsLeft")
	private int ticketsLeft;

    public AccessSlot(int id, String accessSlot, String eventName, int ticketsLeft, String date) {
    	this.eventId=id;
    	this.accessSlot=accessSlot;
    	this.ticketsLeft=ticketsLeft;
    	this.eventName=eventName;
    	this.date = date;
    }

    public AccessSlot(){

	}

	public void setDate(String date) { this.date = date; }

	public String getDate() { return date; }

    public void setAccessSlot(String accessSlot){
		this.accessSlot = accessSlot;
	}

	public String getAccessSlot(){
		return accessSlot;
	}

	public void setEventId(int eventId){
		this.eventId = eventId;
	}

	public int getEventId(){
		return eventId;
	}

	public void setEventName(String eventName){
		this.eventName = eventName;
	}

	public String getEventName(){
		return eventName;
	}

	public void setTicketsLeft(int ticketsLeft){
		this.ticketsLeft = ticketsLeft;
	}

	public int getTicketsLeft(){
		return ticketsLeft;
	}

	@Override
	public int hashCode() {
		int res = eventId;
		res*=51;
		res+=ticketsLeft;
		res*=51;
		res+=accessSlot.length() + eventName.length() + date.length();
		return res;
	}

	@Override
	public boolean equals(Object o) {
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		AccessSlot that = (AccessSlot) o;
		return eventId == that.eventId &&
				eventName.equals(that.eventName) &&
				ticketsLeft == that.ticketsLeft &&
				accessSlot.equals(that.accessSlot) &&
				date.equals(that.date);
	}

	@Override
 	public String toString(){
		return 
		"{" +
			"accessSlot : '" + accessSlot + "' \n" +
			",eventId : " + eventId + " \n" +
			",eventName : '" + eventName + "' \n" +
			",ticketsLeft : " + ticketsLeft + " \n" +
			",date : '" + date + "' \n" +
		"}";
    }
}