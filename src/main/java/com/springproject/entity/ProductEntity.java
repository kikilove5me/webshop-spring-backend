package com.springproject.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "products")
public class ProductEntity {
    @Id
    private String id;
    private String name;
    private String description;
    @ElementCollection
    @CollectionTable(name="product_entity_tags", joinColumns = @JoinColumn(name="product_id"))
    private List<String> tags;
    private Integer priceInCent;

    public ProductEntity() {

    }
    public ProductEntity(String id, String name, String description, Integer priceInCent, List<String> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceInCent = priceInCent;
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Integer getPriceInCent() {
        return priceInCent;
    }

    public void setPriceInCent(Integer priceInCent) {
        this.priceInCent = priceInCent;
    }
}
