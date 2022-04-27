package com.example.groupassignment;

import java.util.ArrayList;

public class Review {

    //Singleton
    private static Review instance = null;

    public static Review getInstance() {
        if (instance == null) {
            instance = new Review();
        }
        return instance;
    }

    private ArrayList<ReviewStorage> reviews;

    public Review() {
        reviews = new ArrayList<>();

        reviews.add(new ReviewStorage("elokuva", "minä", "hyvä on", 5));
        reviews.add(new ReviewStorage("elokuva", "minä", "hyvä on", 4));
        reviews.add(new ReviewStorage("elokuva", "minä", "hyvä on", 3));

    }


    public boolean addReview(String movie, String username, String comment, int stars) {
        if(0<=stars && stars<=5 && !comment.isEmpty()){
            reviews.add(new ReviewStorage(movie, username, comment, stars));
            return true;
        }
        else{
            return false;
        }
        //Edittext for comment when review is submitted
        //stars
        //username is known
    }

    public boolean readReview(String movie){
        for(int i=0;i<reviews.size();i++){
            ReviewStorage review = reviews.get(i);
            if(review.getMovie().equals(movie)){
            }
        }
        return false;
    }


}
