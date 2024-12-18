package com.example.precosapi.dto;

public class ItemResponseDTO {
    private String title;
    private String imageUrl;
    private double averagePrice;
    private String sellerName;

    public ItemResponseDTO(String title, String imageUrl, double averagePrice, String sellerName) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.averagePrice = averagePrice;
        this.sellerName = sellerName;
    }

    // Getters e Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(double averagePrice) {
        this.averagePrice = averagePrice;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }
}
