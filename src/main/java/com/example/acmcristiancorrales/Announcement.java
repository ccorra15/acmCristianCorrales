package com.example.acmcristiancorrales;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Announcement {

    private String title;
    private String author;
    private String body;
    private String date;
    private String imageUrl;

    //Instance of Firebasedatabase
    final private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //Instance of our actual database
    private DatabaseReference mDatabaseReference;

    //Our announcements list
    private List<Announcement> mAnnouncements;

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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Do Nothing
            }
        });
    }


    public Announcement() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
