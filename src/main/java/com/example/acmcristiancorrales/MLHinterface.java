package com.example.acmcristiancorrales;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MLHinterface {

    @GET("na-2019")
        //Call is retrofit Object.
        //We will put our List of Event(s) into this object and name this method "getEvents()"
        //Since this is a interface we do not include the method body.
    Call<List<Event>> getEvents();
    //Retrofit understands this object call and will generate the necessary code to
    //complete our HTTP request.


}
