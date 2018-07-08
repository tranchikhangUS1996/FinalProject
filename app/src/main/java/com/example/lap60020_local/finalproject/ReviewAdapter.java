package com.example.lap60020_local.finalproject;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class ReviewAdapter extends
        RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView tvTen;
        public TextView tvContent;
        public TextView tvIcon;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            tvTen = (TextView) itemView.findViewById(R.id.tvTen);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            tvIcon = (TextView) itemView.findViewById(R.id.tvIcon);
        }
    }

    // Store a member variable for the contacts
    private List<Reviews> mContacts;

    // Pass in the contact array into the constructor
    public ReviewAdapter(List<Reviews> contacts) {
        mContacts = contacts;
    }

    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.line_list, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(ReviewAdapter.ViewHolder viewHolder, int position) {
        // Get the data model based on position
        Reviews review = mContacts.get(position);

        // Set item views based on your views and data model
        TextView tvTen = viewHolder.tvTen;
        tvTen.setText(review.getTen());
        TextView tvContent = viewHolder.tvContent;
        tvContent.setText(review.getContent());
        TextView tvIcon = viewHolder.tvIcon;
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
        GradientDrawable drawable = (GradientDrawable) tvIcon.getBackground();
        drawable.setColor(color);
        tvIcon.setText(review.getTen().toString().charAt(0) + "");
    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mContacts.size();
    }
}