package com.example.acmcristiancorrales;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.acmcristiancorrales.R;
import com.example.acmcristiancorrales.Announcement;
import com.example.acmcristiancorrales.Event;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

public class RecyclerAnnouncements extends RecyclerView.Adapter<RecyclerAnnouncements.RecyclerViewHolder>{

    //These are our variables, we have a list of announcements (supplied from the constructor)
    //And our context, since we're working in another class.
    private List<Announcement> announcements;
    private Context mContext;

    //This is our constructor.
    //Here, we take in our data (the announcements) and the context.
    public RecyclerAnnouncements(List<Announcement> announcements, Context context) {
        this.announcements = announcements;
        this.mContext = context;
    }

    //Since we extend the Adapter, we must implement some methods.
    //The RecyclerViewHolder is one. This tells the adapter what item will we use
    //For our RecyclerView
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Here, we inflate the Layout with our item.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.announcement_item,viewGroup,false);
        //Then we return the ViewHolder so we can use it in our Resources class.
        return new RecyclerViewHolder(view);
    }
    //Whenever you scroll down, the RecyclerView recycles an old view to be replace
    // with new information that is supplied. This is where that happens.
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
        recyclerViewHolder.bind(i);
    }

    //Returns the size of the array.
    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        //These are the variables for each view.
        ImageView mAnnouncementImage;
        TextView mAnnouncementTitle;
        TextView mAnnouncementDate;
        TextView mAnnouncementBody;
        TextView mAnnouncementAuthor;

        //Here in the RecyclerViewHolder, we instantiate each View by finding their id.
        //Since findViewById returns a view, we must type-cast it to match each type of the variables above.
        public RecyclerViewHolder(View itemView){
            super(itemView);
            mAnnouncementImage = (ImageView) itemView.findViewById(R.id.announcement_image_url);
            mAnnouncementTitle = (TextView) itemView.findViewById(R.id.announcement_title);
            mAnnouncementDate = (TextView) itemView.findViewById(R.id.announcement_date);
            mAnnouncementBody = (TextView) itemView.findViewById(R.id.announcement_body);
            mAnnouncementAuthor = (TextView) itemView.findViewById(R.id.announcement_author);
        }

        //Here, we bind the information with the view itself.
        void bind(final int position){
            Picasso.with(mContext).load(announcements.get(position).getImageUrl()).into(mAnnouncementImage);
            mAnnouncementTitle.setText(announcements.get(position).getTitle());
            mAnnouncementDate.setText(announcements.get(position).getDate());
            mAnnouncementBody.setText(announcements.get(position).getBody());
            mAnnouncementAuthor.setText(announcements.get(position).getAuthor());
        }

    }
}
