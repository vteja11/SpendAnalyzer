package com.servicenow.spendanalyzer.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    @DisplayName("Should create a product with given Name and Spend")
    public void testProductCreation(){
        String productName = "TestProduct";
        double amountSpent =  100;
        Product testProduct = new Product(productName, amountSpent);
        testProduct(testProduct, productName, amountSpent);
    }

    @Test
    @DisplayName("Ensure total spend is updated")
    public void testTotalSpendUpdateOfProduct(){
        String productName = "TestProduct";
        double initialSpend =  120, additionalSpend = 231, totalSpend=initialSpend+additionalSpend;
        Product testProduct = new Product(productName, initialSpend);
        testProduct.updateTotalSpend(additionalSpend);
        testProduct(testProduct, productName, totalSpend);
    }

    private static void testProduct(Product product, String expectedName, double expectedSpend){
        assertAll("testingProductDetails",
                ()-> assertEquals(expectedName, product.getName(), "Product Name Test Failed"),
                () -> assertEquals(expectedSpend, product.getTotalSpend(), "Product Total Spend Test Failed")
        );

    }

    public static void testProductsCollection(Collection<Product> expectedProducts, Collection<Product> actualProducts){
        assertEquals(expectedProducts.size(),actualProducts.size(), "Size Test Of Products Failed, expected size of products was: "+expectedProducts.size()+" But Actual size of products was: "+actualProducts.size());
        Iterator<Product> actualProductsItr = actualProducts.iterator();
        Iterator<Product> expectedProductsItr = expectedProducts.iterator();

        while(actualProductsItr.hasNext()&&expectedProductsItr.hasNext()){
            Product actualProduct = actualProductsItr.next();
            Product expectedProduct = expectedProductsItr.next();
            testProduct(actualProduct, expectedProduct.getName(), expectedProduct.getTotalSpend());
        }
    }

}
