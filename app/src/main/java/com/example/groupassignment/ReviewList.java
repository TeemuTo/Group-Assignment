package com.example.groupassignment;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class ReviewList extends ArrayAdapter<ReviewStorage> {

    private Context context;
    ArrayList<ReviewStorage> reviews;

    public ReviewList(Activity context,ArrayList<ReviewStorage> reviews) {
        super(context, R.layout.review_item, reviews);

        this.context=context;
        this.reviews = reviews;


    }

    public View getView(int position, View view, ViewGroup parent){
        View v = view;
        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.review_item, null);
        }

        TextView user = v.findViewById(R.id.PersonName);
        TextView comment = v.findViewById(R.id.ReviewComment);
        TextView stars = v.findViewById(R.id.CountOfStars);

        user.setText(reviews.get(position).getUsername());
        comment.setText(reviews.get(position).getComment());
        stars.setText(reviews.get(position).getStars());

        return v;
    }
}
