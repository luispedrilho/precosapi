package com.example.precosapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {
    private String id;
    private String title;
    private String price;
    private String currency_id;

    // Usando @JsonProperty para garantir que o campo JSON 'catalog_product_id' seja mapeado corretamente
    @JsonProperty("catalog_product_id")
    private String catalogProductId; // O nome na classe pode ser diferente (camelCase), mas o JSON tem snake_case

    // Getters e Setters
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrencyId() {
        return currency_id;
    }

    public void setCurrencyId(String currency_id) {
        this.currency_id = currency_id;
    }

    // Getter e Setter para o catalog_product_id
    public String getCatalogProductId() {
        return catalogProductId;
    }

    public void setCatalogProductId(String catalogProductId) {
        this.catalogProductId = catalogProductId;
    }
}
