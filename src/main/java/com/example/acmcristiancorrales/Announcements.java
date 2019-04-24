package com.example.acmcristiancorrales;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.acmcristiancorrales.R;
import com.example.acmcristiancorrales.RecyclerAnnouncements;
import com.example.acmcristiancorrales.RecyclerHackathons;
import com.example.acmcristiancorrales.Announcement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Announcements extends Fragment {
    //Instance of Firebasedatabase
    final private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //Instance of our actual database
    private DatabaseReference mDatabaseReference;

    private RecyclerView recyclerView;


    private RecyclerAnnouncements mAnnouncementAdapter;

    //Our announcements
    private List<Announcement> mAnnouncements;

    public Announcements() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mAnnouncements = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_announcements, container, false);
        recyclerView = rootView.findViewById(R.id.announcements_recycler);
        mAnnouncementAdapter = new RecyclerAnnouncements(mAnnouncements,container.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        recyclerView.setAdapter(mAnnouncementAdapter);
        getActivity().setTitle("Announcements");

        retrieveData();

        return rootView;
    }

    public void retrieveData() {
        //Gets the reference from the firebase database.
        mDatabaseReference = database.getReference();

        //queries specific location on database, in this case the child of the root (project) node
        //is Announcements, so we want that information.
        Query query = mDatabaseReference.child("Announcements");

        //In order to listen for changes, we will add a listener.
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            //Returns a new "dataSnapshot" of how the data in the database looks.
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //For every single snapshot in our data snapshot, we will get their children - these will
                //be the "announcement1" and "announcement2".
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    //Here, we use our model to create an object of the singleSnapshot.
                    Announcement announcement = singleSnapshot.getValue(Announcement.class);
                    //Adds this onto the list we have in this class.
                    mAnnouncements.add(announcement);
                }
                mAnnouncementAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Do Nothing
            }
        });
    }
}
