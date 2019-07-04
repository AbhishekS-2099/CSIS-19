package com.example.myapplication1;


public class event {

    private  String eventName;
    private String eventDate;
    private String eventType;
    private String key;

    public event(String eventDate, String eventName, String eventType){
        this.eventDate=eventDate;
        this.eventName=eventName;
        this.eventType=eventType;
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
