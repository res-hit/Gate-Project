package com.RestClientPalocs.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class AccessRecording {
    @JsonProperty("date")
    private String date;

    @JsonProperty("reservationId")
    private int reservationId;

    @JsonProperty("time")
    private String time;

    @JsonProperty("gateId")
    private int gateId;

    @JsonProperty("eventId")
    private int eventId;

    @JsonProperty("eventName")
    private String eventName;

    @JsonProperty("key")
    private String key;

    public AccessRecording(String date, String time, int gateId, int eventId, String eventName, String key, int reservationId) {
        this.date = date;
        this.time = time;
        this.gateId = gateId;
        this.eventId = eventId;
        this.eventName = eventName;
        this.key = key;
        this.reservationId = reservationId;
    }

    public AccessRecording() {}

    public int getReservationId() { return reservationId; }

    public void setReservationId(int reservationId) { this.reservationId = reservationId; }

    public int getGateId() { return gateId; }

    public void setGateId(int gateId) { this.gateId = gateId; }

    public String getDate() { return date; }

    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }

    public void setTime(String time) { this.time = time; }

    public int getEventId() { return eventId; }

    public void setEventId(int eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }

    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getKey() { return key; }

    public void setKey(String key) { this.key = key; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessRecording that = (AccessRecording) o;
        return eventId == that.eventId &&
                gateId == that.gateId &&
                eventName.equals(that.eventName) &&
                key.equals(that.key) &&
                date.equals(that.date) &&
                time.equals(that.time) &&
                reservationId == that.reservationId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId, gateId, eventId, eventName, key, date, time);
    }

    @Override
    public String toString(){
        return
                "{" +
                        "gateId : " + gateId + " \n" +
                        ",eventId : " + eventId + " \n" +
                        ",eventName : '" + eventName + "' \n" +
                        ",reservationId : " + reservationId + "\n" +
                        ",key : '" + key + "' \n" +
                        ",date : '" + date + "' \n" +
                        ",time : '" + time + "' \n" +
                "}";
    }
}
