package com.example.groupassignment;

public class ReviewStorage {
    private String movie;
    private String username;
    private String comment;
    private String stars;

    public ReviewStorage(String m, String u, String c, String s){
        movie = m;
        username = u;
        comment = c;
        stars = s;
    }

    public String getKino(){return movie;}
    public String getUsername(){return username;}
    public String getComment(){return comment;}
    public String getStars(){return stars;}
}
