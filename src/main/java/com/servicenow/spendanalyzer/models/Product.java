package com.servicenow.spendanalyzer.models;


public class Product {
    private String name;
    private double totalSpend;

    public Product(String name, double totalSpend){
        this.name = name;
        this.totalSpend = totalSpend;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public String getName() {
        return name;
    }

    public void updateTotalSpend(double amount){
        this.totalSpend+=amount;
    }

}