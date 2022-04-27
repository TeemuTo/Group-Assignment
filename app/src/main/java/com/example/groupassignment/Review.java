package com.example.groupassignment;

import java.util.ArrayList;

public class Review {

    private int rating;
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

    public ArrayList<ReviewStorage> readReview(String movie){
        ArrayList<ReviewStorage> list;
        list = new ArrayList<>();
        for(int i=0;i<reviews.size();i++){
            ReviewStorage review = reviews.get(i);
            if(review.getMovie().equals(movie)){
                list.add(new ReviewStorage(review.getMovie(), review.getUsername(), review.getComment(), review.getStars()));
            }
        }
        return list;
    }

    public void setRate(int rate){
        this.rating = rate;
    }

    public int getRate(){
        return rating;
    }
}
