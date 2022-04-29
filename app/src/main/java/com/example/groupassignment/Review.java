package com.example.groupassignment;

import java.util.ArrayList;

public class Review {

    private String rating;
    //Singleton
    private static Review instance = null;

    private ArrayList<ReviewStorage> reviewList;

    public static Review getInstance() {
        if (instance == null) {
            instance = new Review();
        }
        return instance;
    }

    private ArrayList<ReviewStorage> reviews;

    public Review() {
        reviews = new ArrayList<>();
        reviews.add(new ReviewStorage("Elokuva", "Teemu", "Aivan loistava", "5"));
        reviews.add(new ReviewStorage("Elokuva", "Henri", "ihan ok", "3"));
        reviews.add(new ReviewStorage("Elokuva", "Jyri", "se on elokuva", "1"));

    }

    public ArrayList<ReviewStorage> readReview(String movie){
        reviewList = new ArrayList<>();
        for(int i=0;i<reviews.size();i++) {
            ReviewStorage review = reviews.get(i);
            if (review.getKino().equals(movie)) {
                reviewList.add(new ReviewStorage(movie, review.getUsername(), review.getComment(), review.getStars()));
            }
        }
        return reviewList;
    }


    public boolean addReview(String movie, String username, String comment,String stars) {
        if(!comment.isEmpty()){
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


    public void setRate(String rate){
        this.rating = rate;
    }

    public String getRate(){
        return rating;
    }
}
