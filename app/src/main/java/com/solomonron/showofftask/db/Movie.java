package com.solomonron.showofftask.db;

public class Movie {


    private int id;
    private String title;
    private String url;
    private double rating;
    private int releaseYear;
    private String genre;

    public Movie(int id, String title, String url, double rating, int releaseYear, String genre) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public Movie(int id, String title, String url, double rating, int releaseYear) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.rating = rating;
        this.releaseYear = releaseYear;
    }

    public Movie(String title, String url, double rating, int releaseYear, String genre) {
        this.title = title;
        this.url = url;
        this.rating = rating;
        this.releaseYear = releaseYear;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return title;
    }
}


