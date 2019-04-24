package com.example.acmcristiancorrales;

public class Event {

    private String name;
    private String url;
    private String startDate;
    private String endDate;
    private String location;
    private boolean isHighSchool;
    private String imageUrl;

    public Event(String name, String url, String startDate, String endDate, String location, boolean isHighSchool, String imageUrl) {
        this.name = name;
        this.url = url;
        this.startDate = startDate;
        this.endDate = endDate;
        this.location = location;
        this.isHighSchool = isHighSchool;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isHighSchool() {
        return isHighSchool;
    }

    public void setHighSchool(boolean highSchool) {
        isHighSchool = highSchool;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
