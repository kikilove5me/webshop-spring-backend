package com.springproject.model;

import java.util.List;

public class ProductResponse {

    private final String id;
    private final String name;
    private final String description;
    private final long priceInCent;
    private final List<String> tags;

    public ProductResponse(String id, String name, String description, long priceInCent, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getPriceInCent() {
        return priceInCent;
    }

    public List<String> getTags() {
        return tags;
    }
}
