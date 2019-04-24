package com.example.acmcristiancorrales;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Resources extends Fragment {

    List<Event> hackathons;

    private RecyclerView recyclerView;
    private RecyclerHackathons recyclerAdapter;

    public Resources() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //Understanding this completely is out of the scope of the workshop
        //Just know that we are getting our root view since we are using a fragment instead of an activity.
        //We must know what activity we are on.
        View rootView = inflater.inflate(R.layout.fragment_resources, container, false);

        //Find the results TextView in our XML through Id
        recyclerView = rootView.findViewById(R.id.hackathons_recycler);

        //LoadJSON is a custom method, we take in the parameter container since this is a fragment.
        LoadJson(container);

        return rootView;

    }

    public void LoadJson(final ViewGroup container){
        MLHinterface apiInterface = APIClient.getApiClient().create(MLHinterface.class);

        Call<List<Event>> call;
        call = apiInterface.getEvents();

        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                hackathons = filterEvents(response.body());
                //Here, we create an instance of our Adapter named RecyclerHackathons, we supply the necessary parameters.
                recyclerAdapter = new RecyclerHackathons(hackathons, container.getContext());

//Now, we set the layout manager. Since we want everything displayed vertically in a Linear fashion, we will use the LinearLayoutManager. There are other layout managers out there which can be found on the LayoutManager documentation.
                recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
//We set our adapter.
                recyclerView.setAdapter(recyclerAdapter);
                //This method notifies RecyclerView whenever data is changed.
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {

            }
        });

    }

    public List<Event> filterEvents(final List<Event> allEvents){
        List<Event> caEvents = new ArrayList<>();
        for(int i = allEvents.size() - 1; i > 0; i--){
            //Filter California Events
            if(allEvents.get(i).getLocation().contains("CA")){
                caEvents.add(allEvents.get(i));
            }
        }
        return caEvents;
    }



}
