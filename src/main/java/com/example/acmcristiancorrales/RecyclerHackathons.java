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
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerHackathons extends RecyclerView.Adapter<RecyclerHackathons.RecyclerViewHolder>{

    //These are our variables, we have a list of Hackathons (supplied from the constructor)
    //And our context, since we're working in another class.
    private List<Event> mHackathons;
    private Context mContext;

    //This is our constructor.
    //Here, we take in our data (the hackathons) and the context.
    public RecyclerHackathons(List<Event> hackathons, Context context) {
        this.mHackathons = hackathons;
        this.mContext = context;
    }

    //Since we extend the Adapter, we must implement some methods.
    //The RecyclerViewHolder is one. This tells the adapter what item will we use
    //For our RecyclerView
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Here, we inflate the Layout with our item.
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_item,viewGroup,false);
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
        return mHackathons.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //These are the variables for each view.
        ImageView mEventImage;
        TextView mEvenTitle;
        TextView mEventStart;
        TextView mEventLocation;

        //Here in the RecyclerViewHolder, we instantiate each View by finding their id.
        //Since findViewById returns a view, we must type-cast it to match each type of the variables above.
        public RecyclerViewHolder(View itemView){
            super(itemView);
            mEventImage = (ImageView) itemView.findViewById(R.id.image_url);
            mEvenTitle = (TextView) itemView.findViewById(R.id.title);
            mEventStart = (TextView) itemView.findViewById(R.id.start_date);
            mEventLocation = (TextView) itemView.findViewById(R.id.location);


        }

        //Here, we bind the information with the view itself.
        void bind(final int position){
            Picasso.with(mContext).load(mHackathons.get(position).getImageUrl()).into(mEventImage);
            mEvenTitle.setText(mHackathons.get(position).getName());
            mEventStart.setText(mHackathons.get(position).getStartDate());
            mEventLocation.setText(mHackathons.get(position).getLocation());
            //We also set each item view to have a onClickListener.
            itemView.setOnClickListener(this);
        }

        //We give functionality to the "onClick" saying that if the view is clicked, create a new intent and open up the
        //URL on a browser.
        @Override
        public void onClick(View view) {
            String urlString = mHackathons.get(getAdapterPosition()).getUrl();
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(urlString));
            mContext.startActivity(browserIntent);
        }

    }
}

