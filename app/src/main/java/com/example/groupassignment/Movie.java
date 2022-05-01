package com.example.groupassignment;

public class Movie {
    private String movie;
    private String duration;
    private String rating;
    private String story;

    public Movie(String m, String d, String r, String s){
        movie = m;
        duration = d;
        rating = r;
        story = s;
    }

    public String getFilm(){return movie;}
    public String getDuration(){return duration;}
    public String getRating(){return rating;}
    public String getStory(){return story;}
}
