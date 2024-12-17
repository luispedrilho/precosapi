package com.example.precosapi.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchResponse {
    private List<Item> results;

    // Getters e Setters
    public List<Item> getResults() {
        return results;
    }

    public void setResults(List<Item> results) {
        this.results = results;
    }
}


