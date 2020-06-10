package com.RestClientPalocs.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

//import org.codehaus.jackson.annotate.JsonProperty;

public class Event {

	@JsonProperty("eventId")
	private String eventId;

	@JsonProperty("date")
	private List<String> date = new ArrayList<>();

	@JsonProperty("time")
	private String time;

	@JsonProperty("eventName")
	private String eventName;

	@JsonProperty("self")
	private String self;

	@JsonProperty("maxParticipants")
	private int maxParticipants;

	@JsonProperty("slotDuration")
	private int slotDuration;

	@JsonProperty("peoplePerSlot")
	private int peoplePerSlot;

	@JsonProperty("accessSlots")
	private List<AccessSlot> accessSlots = new ArrayList<>();

	@JsonProperty("ticketsLeft")
	private int ticketsLeft;

	@JsonProperty("pricingTypes")
	private List<PricingTypeItem> pricingTypes = new ArrayList<>();

	@JsonProperty("free")
	private boolean free = true;

	@JsonProperty("author")
	private String author;

	public Event(){

	}

	public Event(String self,
                 List<String> date,
                 String time,
                 String eventId,
                 String eventName,
                 int maxParticipants,
                 int slotDuration,
                 List<AccessSlot> accessSlots,
                 int peoplePerSlot,
                 int ticketsLeft,
                 boolean free,
                 List<PricingTypeItem> pricingTypes,
                 String author){
		this.self=self;
		this.date.addAll(date);
		this.time=time;
		this.eventId=eventId;
		this.eventName=eventName;
		this.maxParticipants=maxParticipants;
		this.slotDuration = slotDuration;
		this.accessSlots.addAll(accessSlots);
		this.peoplePerSlot=peoplePerSlot;
		this.ticketsLeft = ticketsLeft;
		this.free = free;
		this.pricingTypes.addAll(pricingTypes);
		this.author = author;
	}

	public String getAuthor() { return author; }

	public void setAuthor(String author) { this.author = author; }

	public void setPricingTypes(List<PricingTypeItem> pricingTypes) { this.pricingTypes = pricingTypes; }

	public List<PricingTypeItem> getPricingTypes() { return pricingTypes; }

	public void setPeoplePerSlot(int peoplePerSlot) { this.peoplePerSlot = peoplePerSlot; }

	public int getPeoplePerSlot() { return peoplePerSlot; }

	public void setFree(boolean free){
		this.free = free;
	}

	public boolean isFree(){
		return free;
	}

	public void setTime(String time) { this.time = time; }

	public String getTime() { return time; }

	public void setSelf(String self) { this.self = self; }

	public String getSelf() { return self; }

	public void setDate(List<String> date){
		this.date = date;
	}

	public List<String> getDate(){ return date; }

	public void setEventId(String eventId){
		this.eventId = eventId;
	}

	public String getEventId(){
		return eventId;
	}

	public void setEventName(String eventName){
		this.eventName = eventName;
	}

	public String getEventName(){
		return eventName;
	}

	public void setMaxParticipants(int maxParticipants){
		this.maxParticipants = maxParticipants;
	}

	public int getMaxParticipants(){
		return maxParticipants;
	}

	public void setAccessSlots(List<AccessSlot> accessSlots){
		this.accessSlots = accessSlots;
	}

	public List<AccessSlot> getAccessSlots(){
		return accessSlots;
	}

	public void setSlotDuration(int slotDuration){
		this.slotDuration = slotDuration;
	}

	public int getSlotDuration(){
		return slotDuration;
	}

	public int getTicketsLeft() { return ticketsLeft; }

	public void setTicketsLeft(int ticketsLeft) { this.ticketsLeft = ticketsLeft; }

	@Override
	public boolean equals(Object o){
		if(this == o)
			return true;
		if(o == null || getClass() != o.getClass())
			return false;
		Event that = (Event) o;
		return self.equals(that.self) &&
				date.size() == that.date.size() &&
				date.containsAll(that.date) &&
				time.equals(that.time) &&
				eventId.equals(that.eventId) &&
				eventName.equals(that.eventName) &&
				maxParticipants == that.maxParticipants &&
				accessSlots.size() == that.accessSlots.size() &&
				accessSlots.containsAll(that.accessSlots) &&
				pricingTypes.size() == that.pricingTypes.size() &&
				pricingTypes.containsAll(that.pricingTypes) &&
				slotDuration == that.slotDuration &&
				ticketsLeft == that.ticketsLeft &&
				peoplePerSlot == that.peoplePerSlot &&
				free == that.free &&
				author.equals(that.author);
	}

	@Override
	public int hashCode(){
		int res = eventId != null ? eventId.hashCode() : 0;
		res*=51;
		res+=maxParticipants;
		res*=51;
		res+=accessSlots.size() + slotDuration + peoplePerSlot + ticketsLeft;
		return res;
	}

	@Override
 	public String toString(){
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for( ; i < accessSlots.size()-1; i++){
			sb.append(accessSlots.get(i).toString()).append(",\n");
		}
		if(accessSlots.size() > 0)
			sb.append(accessSlots.get(i).toString());
		StringBuilder sb2 = new StringBuilder();
		for(i = 0; i < pricingTypes.size()-1; i++){
			sb2.append(pricingTypes.get(i).toString()).append(",\n");
		}
		if(pricingTypes.size() > 0)
			sb2.append(pricingTypes.get(i).toString());
		StringBuilder sb3 = new StringBuilder();
		for(i=0; i < date.size() - 1; i++ ){
			sb3.append(date.get(i)).append(",\n");
		}
		if(date.size() > 0){
			sb3.append(date.get(i));
		}
		return 
			"{\n" +
			"self : '" + self + "'\n" +
			",date : [" + sb3.toString() + "]\n" +
			",time : '" + time + "'\n" +
			",eventId : '" + eventId + "'\n" +
			",eventName : '" + eventName + "'\n" +
			",maxParticipants : " + maxParticipants + "\n" +
			",slotDuration : " + slotDuration + "\n" +
			",peoplePerSlot : " + peoplePerSlot + "\n" +
			",accessSlots : [" + sb.toString() + "]\n" +
			",ticketsLeft : " + ticketsLeft + "\n" +
			",free : " + free + "\n" +
			",pricingTypes : ["+ sb2.toString() + "]\n" +
			",author : '" + author + "'\n" +
			"}";
		}
}