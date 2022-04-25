package com.example.groupassignment;

public class ReviewStorage {
    private String movie;
    private String username;
    private String comment;
    private int stars;

    public ReviewStorage(String m, String u, String c, int s){
        movie = m;
        username = u;
        comment = c;
        stars = s;
    }

    public String getMovie(){return movie;}
    public String getUsername(){return username;}
    public String getComment(){return comment;}
    public int getStars(){return stars;}
}
