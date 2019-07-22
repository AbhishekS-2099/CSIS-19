package com.example.myapplication1;

public class speaker {

    private String Name;
    private String talkOn;
    private String briefAbout;

    public speaker() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getTalkOn() {
        return talkOn;
    }

    public void setTalkOn(String talkOn) {
        this.talkOn = talkOn;
    }

    public String getBriefAbout() {
        return briefAbout;
    }

    public void setBriefAbout(String briefAbout) {
        this.briefAbout = briefAbout;
    }

    public speaker(String name, String talkOn, String briefAbout) {
        this.Name = name;
        this.talkOn = talkOn;
        this.briefAbout = briefAbout;
    }
}
