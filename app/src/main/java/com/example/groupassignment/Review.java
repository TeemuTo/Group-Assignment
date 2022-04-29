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
        reviews.add(new ReviewStorage("Elokuva", "Teemu", "Aivan loistava", 5));
        reviews.add(new ReviewStorage("Elokuva", "Henri", "ihan ok", 3));
        reviews.add(new ReviewStorage("Elokuva", "Jyri", "se on elokuva", 1));

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

    public ArrayList<String> readReviewWriter(String movie){
        ArrayList<String> list;
        list = new ArrayList<>();
        for(int i=0;i<reviews.size();i++){
            ReviewStorage review = reviews.get(i);
            if(review.getKino().equals(movie)){
                list.add(review.getUsername());
            }
        }
        return list;
    }

    public ArrayList<String> readReviewComment(String movie){
        ArrayList<String> list;
        list = new ArrayList<>();
        for(int i=0;i<reviews.size();i++){
            ReviewStorage review = reviews.get(i);
            if(review.getKino().equals(movie)){
                list.add(review.getComment());
            }
        }
        return list;
    }

    public ArrayList<Integer> readReviewStars(String movie){
        ArrayList<Integer> list;
        list = new ArrayList<>();
        for(int i=0;i<reviews.size();i++){
            ReviewStorage review = reviews.get(i);
            if(review.getKino().equals(movie)){
                list.add(review.getStars());
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
