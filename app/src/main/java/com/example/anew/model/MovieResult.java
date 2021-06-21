package com.example.anew.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieResult {

    @SerializedName("budget")
    @Expose
    private Integer budget;

    @SerializedName("homepage")
    @Expose
    private String homepage;

    @SerializedName("original_language")
    @Expose
    private String original_language;

    @SerializedName("original_title")
    @Expose
    private String original_title;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String poster_path;

    @SerializedName("release_date")
    @Expose
    private String release_date;

    @SerializedName("revenue")
    @Expose
    private Integer revenue;

    @SerializedName("runtime")
    @Expose
    private Integer runtime;

    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("popularity")
    @Expose
    private Number popularity;

    @SerializedName("imdb_id")
    @Expose
    private String imdb_id;

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Number getPopularity() {
        return popularity;
    }

    public void setPopularity(Number popularity) {
        this.popularity = popularity;
    }

    public String getImdb_id() {
        return imdb_id;
    }

    public void setImdb_id(String imdb_id) {
        this.imdb_id = imdb_id;
    }
}
