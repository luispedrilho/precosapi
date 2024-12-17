package com.example.precosapi.dto;

public class PrecoDTO {
    private double lowerPrice;
    private int lowerPriceCount; // Itens entre preço mínimo e médio
    private double averagePrice;
    private double higherPrice;
    private int higherPriceCount; // Itens entre preço médio e máximo
    private double suggestedPrice;

    public PrecoDTO(double lowerPrice, int lowerPriceCount, double averagePrice, double higherPrice, int higherPriceCount, double suggestedPrice) {
        this.lowerPrice = lowerPrice;
        this.lowerPriceCount = lowerPriceCount;
        this.averagePrice = averagePrice;
        this.higherPrice = higherPrice;
        this.higherPriceCount = higherPriceCount;
        this.suggestedPrice = suggestedPrice;
    }

    // Getters e Setters
    public double getLowerPrice() { return lowerPrice; }
    public int getLowerPriceCount() { return lowerPriceCount; }
    public double getAveragePrice() { return averagePrice; }
    public double getHigherPrice() { return higherPrice; }
    public int getHigherPriceCount() { return higherPriceCount; }
    public double getSuggestedPrice() { return suggestedPrice; }
}
