package com.forster;

import java.util.ArrayList;

public class FskCategory {
    private String FskCategory;
    private int totalMovies;
    private ArrayList<Movie> movies;

    public FskCategory(String FskCategory, int totalMovies, ArrayList<Movie> movies) {
        this.FskCategory = FskCategory;
        this.totalMovies = totalMovies;
        this.movies = movies;
    }

    public FskCategory() {
    }

    public String getFskCategory() {
        return FskCategory;
    }

    public void setFskCategory(String fskCategory) {
        FskCategory = fskCategory;
    }

    public int getTotalMovies() {
        return totalMovies;
    }

    public void setTotalMovies(int totalMovies) {
        this.totalMovies = totalMovies;
    }

    public ArrayList<Movie> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }
}
