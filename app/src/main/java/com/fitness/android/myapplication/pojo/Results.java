package com.fitness.android.myapplication.pojo;

import java.util.List;

public class Results {

    private int page;
    private int total_results;
    private int total_pages;
    private List<MoviesModel> results;

    public Results() {
    }

    public Results(int page, int total_result, int total_page, List<MoviesModel> results) {
        this.page = page;
        this.total_results = total_result;
        this.total_pages = total_page;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_result() {
        return total_results;
    }

    public int getTotal_page() {
        return total_pages;
    }

    public List<MoviesModel> getResults() {
        return results;
    }
}
