package com.example.acmcristiancorrales;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Hackathons {

    //Here, we use the tag SerializedName to tell retrofit that in the JSON the array is called "array", but we want
    //To refer to this array as "events"
    //Also notice that events contains the object Event.
    @SerializedName("array")
    List<Event> events;

    //List of events. We name it "getEvents" because it's the method
    //That will return the events from this class.
    public List<Event> getEvents() {
        return events;
    }


}
