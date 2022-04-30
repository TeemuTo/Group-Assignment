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

    public void addToList(ArrayList<ReviewStorage> list){
        if(list.size()==0){

        }
        else {
            for (int i = 0; i < list.size(); i++) {
                ReviewStorage item = list.get(i);
                reviews.add(new ReviewStorage(item.getKino(), item.getUsername(), item.getComment(), item.getStars()));
            }
        }
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

    public void setRate(String rate){
        this.rating = rate;
    }

    public String getRate(){
        return rating;
    }
}
