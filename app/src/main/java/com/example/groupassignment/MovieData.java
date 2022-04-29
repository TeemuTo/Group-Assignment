package com.example.groupassignment;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Comparator;

public class MovieData {

    //Singleton
    private static MovieData instance = null;
    private String film;

    private ArrayList<String> favorites;
    private ArrayList<String> search;

    public static MovieData getInstance(){
        if(instance == null) {
            instance = new MovieData();
        }
        return instance;
    }

    private ArrayList<Movie> movies;

    public MovieData(){
        movies = new ArrayList<>();
        movies.add(new Movie("Elokuva", "1 t 30 min", (float) 6.9, 15, "ELokuva kertoo elokuvista."));

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

    public void addFavorite(String movie){
        favorites = new ArrayList<String>();
        favorites.add(movie);
    }

    public boolean checkFavorite(String movie){
        for(int i=0;i<favorites.size();i++){
            if(favorites.get(i).equals(movie)){
                return true;
            }
        }
        return false;
    }

    public ArrayList<String> listFavorite(){
        return favorites;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void sortByIMDB(){
        Comparator<Movie> compare = Comparator.comparing(Movie::getIMDB);
    }
}
