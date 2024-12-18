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

    // Adicionando o campo para a URL da imagem
    @JsonProperty("thumbnail")
    private String thumbnail; // Campo para a URL da imagem do item

    // Adicionando o campo para o vendedor
    @JsonProperty("seller")
    private Seller seller; // Representando o vendedor do item

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

    // Getter e Setter para o thumbnail
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    // Getter e Setter para o seller
    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    // Classe interna Seller para mapear o vendedor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Seller {
        private String nickname;

        // Getter e Setter para o nickname do vendedor
        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }
    }
}
