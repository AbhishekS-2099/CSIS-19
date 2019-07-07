package com.example.myapplication1;


public class event {

    private  String eventName;
    private String eventDate;
    private String eventType;
    private String eventLocation;
    private String key;
    private String eventTimeStart;
    private String eventTimeEnd;

    public event(String eventDate, String eventName, String eventType,String eventLocation){
        this.eventDate=eventDate;
        this.eventName=eventName;
        this.eventType=eventType;
        this.eventLocation=eventLocation;
    }
    public event(){}

    public String getEventTimeStart() {
        return eventTimeStart;
    }

    public void setEventTimeStart(String eventTimeStart) {
        this.eventTimeStart = eventTimeStart;
    }

    public String getEventTimeEnd() {
        return eventTimeEnd;
    }

    public void setEventTimeEnd(String eventTimeEnd) {
        this.eventTimeEnd = eventTimeEnd;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object object){
        if(object==null)
            return false;
        if(!event.class.isAssignableFrom(object.getClass()))
            return false;
        final event event = (event)object;
        return event.getKey().equals(key);

    }
}
