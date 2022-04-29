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

public class ReviewList extends ArrayAdapter<String> {

    private Activity context;
    private ArrayList<String> user;
    private ArrayList<String> comment;
    private ArrayList<Integer> stars;

    public ReviewList(Activity context,ArrayList<String> user, ArrayList<String> comment, ArrayList<Integer> stars) {
        super(context, R.layout.review_item);

        this.context=context;
        this.user=user;
        this.comment=comment;
        this.stars=stars;
    }

    public View getView(int position, View view, ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.review_item, null, true);

        TextView person = (TextView) rowView.findViewById(R.id.PersonName);
        TextView review = (TextView) rowView.findViewById(R.id.comment);
        TextView star = (TextView) rowView.findViewById((R.id.CountOfStars));

        person.setText(user.get(position));
        review.setText(comment.get(position));
        star.setText(stars.get(position));

        return rowView;
    }
}
