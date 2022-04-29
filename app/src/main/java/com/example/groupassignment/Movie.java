package com.example.groupassignment;

public class Movie {
    private String movie;
    private String duration;
    private String IMDB;
    private int ageRate;
    private String story;

    public Movie(String m, String d, String i, int a, String s){
        movie = m;
        duration = d;
        IMDB= i;
        ageRate = a;
        story = s;
    }

    public String getFilm(){return movie;}
    public String getDuration(){return duration;}
    public String getIMDB(){return IMDB;}
    public int getAgeRate(){return ageRate;}
    public String getStory(){return story;}
}
