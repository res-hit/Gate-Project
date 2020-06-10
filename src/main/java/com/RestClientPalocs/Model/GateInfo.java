package com.RestClientPalocs.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class GateInfo {

    @JsonProperty("gateId")
    private int gateId;

    @JsonProperty("eventId")
    private int eventId;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("operatorKey")
    private String operatorKey;

    @JsonProperty("key")
    private String key;

    public GateInfo() {
    }

    public GateInfo(int gateId, int eventId, String operator, String operatorKey, String key) {
        this.gateId = gateId;
        this.eventId = eventId;
        this.operator = operator;
        this.operatorKey = operatorKey;
        this.key = key;
    }

    public int getGateId() {
        return gateId;
    }

    public void setGateId(int gateId) {
        this.gateId = gateId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperatorKey() {
        return operatorKey;
    }

    public void setOperatorKey(String operatorKey) {
        this.operatorKey = operatorKey;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GateInfo gateInfo = (GateInfo) o;
        return eventId == gateInfo.eventId &&
                gateId == gateInfo.gateId &&
                operator.equals(gateInfo.operator) &&
                operatorKey.equals(gateInfo.operatorKey) &&
                key.equals(gateInfo.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gateId, eventId, operator, operatorKey, key);
    }

    @Override
    public String toString(){
        return
                "{" +
                        "gateId : " + gateId + " \n" +
                        ",eventId : " + eventId + " \n" +
                        ",operator : '" + operator + "' \n" +
                        ",operatorKey : '" + operatorKey + "'\n" +
                        ",key : '" + key + "' \n" +
                "}";
    }
}
