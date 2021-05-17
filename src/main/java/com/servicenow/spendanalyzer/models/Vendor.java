package com.servicenow.spendanalyzer.models;

import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.Locale;
import java.text.NumberFormat;


public class Vendor {
    private String name;
    private double totalSpend;
    private Map<String, Product> products;

    private static final NumberFormat usdCurrencyFormat = NumberFormat.getCurrencyInstance(new Locale("en", "US"));

    public Vendor(String vendorName, String productName, double amount ){
        this.products = new TreeMap<>();
        this.name = vendorName;
        updateProductsOfVendor(productName,amount);
    }

    public void updateProductsOfVendor(String productName, double amount){
        Product product;
        if(this.products.containsKey(productName)){
            product = this.products.get(productName);
            product.updateTotalSpend(amount);
        }
        else{
            product = new Product(productName, amount);
            this.products.put(productName, product);
        }
        this.totalSpend+=amount;
    }

    public void printReport(){
        printReport(usdCurrencyFormat);
    }

    public void printReport(NumberFormat currencyFormat){
        System.out.println(this.name+" "+currencyFormat.format(this.totalSpend));
        for(String productName: this.products.keySet()){
            Product product = this.products.get(productName);
            System.out.println("  "+product.getName()+" "+currencyFormat.format((product.getTotalSpend())));
        }
    }

    public String getName() {
        return name;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public Map<String, Product> getProducts() {
        return products;
    }
}