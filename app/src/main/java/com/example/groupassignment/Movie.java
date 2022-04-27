package com.example.groupassignment;

public class Movie {
    private String movie;
    private String duration;
    private float IMDB;
    private int ageRate;
    private String story;

    public Movie(String m, String d, float i, int a, String s){
        movie = m;
        duration = d;
        IMDB= i;
        ageRate = a;
        story = s;
    }

    public String getUsername(){return movie;}
    public String getPassword(){return duration;}
    public float getIMDB(){return IMDB;}
    public int getAgeRate(){return ageRate;}
    public String getStory(){return story;}
}
