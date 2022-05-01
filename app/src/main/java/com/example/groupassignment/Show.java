package com.example.groupassignment;

import android.icu.text.CaseMap;

public class Show {
    private String movie;
    private String duration;
    private String Genres;
    private String Rating;

    @Override
    public String toString() {
        return "Show{" +
                "Title='" + movie + '\'' +
        ", LengthInMinutes='" + duration + '\'' +
                ", Rating='" + Rating + '\'' +
        ", Genres='" + Genres + '\'' +
        '}';
    }

    public String getTitle(String Title) {
        return Title;
    }

    public void setTitle(String Title) {
        this.movie = Title;
    }

    public String getLengthInMinutes() {
        return duration;
    }

    public void setLengthInMinutes(String LengthInMinutes) {
        this.duration = LengthInMinutes;
    }

    public String getGenres() {
        return Genres;
    }

    public void setGenres(String Genres) {
        this.Genres = Genres;
    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }
}
