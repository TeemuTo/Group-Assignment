package com.example.groupassignment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class MovieData {

    //Singleton
    private static MovieData instance = null;


    public static MovieData getInstance(){
        if(instance == null) {
            instance = new MovieData();
        }
        return instance;
    }
    private String film;

    private boolean image = true;

    private ArrayList<Movie> movies;
    private ArrayList<String> favorites;
    private ArrayList<String> search;

    public MovieData(){
        movies = new ArrayList<Movie>();
        movies.add(new Movie("leffa 1", "123", "5.9",  "Tarinaosio"));
        movies.add(new Movie("Harry potter", "12","6.9", "Toisenlainen leffa"));
        favorites = new ArrayList<String>();
        favorites.add("No movies yet");

    }

    public ArrayList<String> searchMovies(){
        search = new ArrayList<>();
        for (int i=0; i<movies.size();i++){
            Movie movie = movies.get(i);
            search.add(movie.getFilm());
        }
        return search;
    }


    public void setMovie(String movie){
        this.film = movie;
    }

    public String getMovie(){
        return film;
    }

    public String getTime(){
        for(int i=0;i<movies.size();i++){
            Movie movie = movies.get(i);
            if(film.equals(movie.getFilm())){
                return movie.getDuration();
            }
        }
        return "no time";
    }

    public String getrate(){
        for(int i=0;i<movies.size();i++){
            Movie movie = movies.get(i);
            if(film.equals(movie.getFilm())){
                return movie.getRating();
            }
        }
        return "0.0";
    }

    public String getSummary(){
        for(int i=0;i<movies.size();i++){
            Movie movie = movies.get(i);
            if(film.equals(movie.getFilm())){
                return movie.getStory();
            }
        }
        return "No overview";
    }

    public void addFavorite(String movie){
        favorites.add(movie);
    }



    public void removeFavorite(String movie){
        if(favorites.size()==1){
            favorites.add("No movies yet");
        }
        favorites.remove(movie);
    }

    public boolean checkFavorite(String movie){
        for(int i=0;i<favorites.size();i++){
            if(favorites.get(i).equals(movie)){
                return true;
            }
        }
        return false;
    }

    public boolean findFavorite(String movie){
        if(favorites.size()==1){
            return true;
        }
        for (int i=0;i<favorites.size();i++){
            if(movie.equals(favorites.get(i))){
                return false;
            }
        }
        return true;
    }

    public ArrayList<String> listFavorite(){
        if(favorites.size()==2){
            favorites.remove("No movies yet");
        }
        return favorites;
    }
    public ArrayList<String> sortByMovie(){
        ArrayList<String> sortMovie = new ArrayList<>();
        sortMovie = favorites;
        Collections.sort(sortMovie);
        return sortMovie;
    }
    public void setLike(){
        this.image = true;
    }

    public void setLiked(){
        this.image = false;
    }

    public boolean getImage(){
        return image;
    }
}
