package com.forster;

import java.util.List;

public class Movies {

    private String id;
    private String title;
    private int  production_year;
    private String object_class;
    private List<String> fsk_level_list_facet;

    public Movies(String id, String title, int production_year, String object_class, List<String> fsk_level_list_facet) {
        this.id = id;
        this.title = title;
        this.production_year = production_year;
        this.object_class = object_class;
        this.fsk_level_list_facet = fsk_level_list_facet;
    }

    public Movies() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProduction_year() {
        return production_year;
    }

    public void setProduction_year(int production_year) {
        this.production_year = production_year;
    }

    public String getObject_class() {
        return object_class;
    }

    public void setObject_class(String object_class) {
        this.object_class = object_class;
    }

    public List<String> getFsk_level_list_facet() {
        return fsk_level_list_facet;
    }

    public void setFsk_level_list_facet(List<String> fsk_level_list_facet) {
        this.fsk_level_list_facet = fsk_level_list_facet;
    }
}
