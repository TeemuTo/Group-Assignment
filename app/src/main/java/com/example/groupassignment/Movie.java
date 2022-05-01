package com.example.groupassignment;

public class Movie {
    private String movie;
    private String duration;
    private String rating;
    private int ageRate;
    private String story;

    public Movie(String m, String d, String r, int a, String s){
        movie = m;
        duration = d;
        rating= r;
        ageRate = a;
        story = s;
    }

    public String getFilm(){return movie;}
    public String getDuration(){return duration;}
    public String getrating(){return rating;}
    public int getAgeRate(){return ageRate;}
    public String getStory(){return story;}
}
