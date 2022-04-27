package com.example.groupassignment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;

public class MovieData {

    //Singleton
    private static MovieData instance = null;
    private String film;

    public static MovieData getInstance(){
        if(instance == null) {
            instance = new MovieData();
        }
        return instance;
    }

    private ArrayList<Movie> movies;

    public MovieData(){
        movies = new ArrayList<>();

    }

    public void setMovie(String movie){
        this.film = movie;
    }

    public String getMovie(){
        return film;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByIMDB(){
        Comparator<Movie> compare = Comparator.comparing(Movie::getIMDB);
    }
}
