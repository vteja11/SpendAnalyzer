package com.servicenow.spendanalyzer.models;

import com.servicenow.spendanalyzer.models.Product;
import com.servicenow.spendanalyzer.models.ProductTest;
import com.servicenow.spendanalyzer.models.Vendor;
import org.junit.jupiter.api.*;

import java.util.*;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VendorTest {

    // Product Transactions (Input)
    private static final String[][] productList1 = {{"Aws ec2","100"},{"Aws lambda","200"}, {"Aws ecs","132"}, {"Aws fargate","331"}, {"Aws cloudwatch","432"}, {"Aws EMR","150"}, {"Aws ec2","4251"}, {"Aws ecs","465"}, {"Aws fargate","400"}, {"Aws cloudwatch", "500000"}, {"Aws lambda","27637253"}, {"Aws ec2","811777"}};
    // Aggregation Of Product Transactions (Expected Output)
    private static final String[][] productList1Agg = { {"Aws EMR", "150"}, {"Aws cloudwatch", "500432"},{"Aws ec2","816128"}, {"Aws ecs","597"}, {"Aws fargate", "731"}, {"Aws lambda","27637453"}, {"TestProduct","100"}};

    private static final String[][] productList2 = {{"Anthos", "975"}, {"Cloud CDN", "678678"},{"BigQuery", "68676"},{"Kubernetes","676788"}, {"Anthos", "09320"}, {"Cloud Run", "5455657"},{"BigQuery", "67227"},{"Cloud CDN", "67678"}  };
    private static final String[][] productList2Agg ={{"Anthos", "10295"}, {"BigQuery", "135903"}, {"Cloud CDN", "746356"},{"Cloud Run", "5455657"}, {"Kubernetes","676788"}, {"TestProduct","100"}};

    private static final String[][] productList3 = {{"Watson", "322"}, {"Garage","87382"}, {"Security verify", "738"},{"Assistant", "7872"}, {"debate", "83125"}, {"Watson","3782"}, {"Garage", "89228"}, {"Assistant", "1000"},{"debate","8292"}};
    private static final String[][] productList3Agg = {{"Assistant", "8872"}, {"Garage","176610"},{"Security verify", "738"}, {"TestProduct","100"}, {"Watson", "4104"}, {"debate", "91417"}};

    private static final  String[][] productList4 = {{"Hana", "327"}, {"SAP CLOUD", "873"},{"ec2", "827"}, {"GCP", "8337"},{"Hana", "272"}, {"lambda", "822"}, {"fargate","7362"}, {"smartbot360", "716"},{"SAP CLOUD", "8332"}, {"GCP", "232"}, {"ec2", "32"}};
    private static final  String[][] productList4Agg = {{"GCP", "8569"},{"Hana", "599"}, {"SAP CLOUD", "9205"}, {"TestProduct","100"}, {"ec2", "859"}, {"fargate","7362"},  {"lambda", "822"}, {"smartbot360", "716"},};

    private static List<productsTestCase> testCases;

    @BeforeAll
    public static void updateTestCases(){
        testCases = new ArrayList<>();
        testCases.add(new productsTestCase(productList1, productList1Agg));
        testCases.add(new productsTestCase(productList2, productList2Agg));
        testCases.add(new productsTestCase(productList3, productList3Agg));
        testCases.add(new productsTestCase(productList4, productList4Agg));
    }

    @Test
    @DisplayName("Should Create a Vendor With the Given Details")
    public void testVendorCreation(){
        String vendorName = "TestVendor";
        String productName = "TestProduct";
        double amountSpend = 100;

        // Vendor creation
        Vendor vendor = new Vendor(vendorName, productName, amountSpend);

        // Vendor has Collection of products;
        // so creating a collection of products for testing
        Map<String, Product> expectedProducts = new LinkedHashMap<>();
        expectedProducts.put(productName, new Product(productName, amountSpend));

        // helper method to test vendor
        testVendor(vendor, vendorName, amountSpend, expectedProducts);
    }

    @TestFactory
    @DisplayName("Ensure the products of vendors is updated properly")
    public Stream<DynamicTest> testUpdatingProductsOfVendors(){
        return testCases.stream().map( (entry) -> {
            String[][] productsTransactions = entry.getProductsTransactions();
            String[][] productsAggregate = entry.getProductsAggregate();
            return DynamicTest.dynamicTest("Should update the products of vendor ", () -> testUpdateProductsOfVendor( productsTransactions, productsAggregate));
        });
    }

    private void testUpdateProductsOfVendor(String[][] productsTransactions, String[][] productsAggregate){
        /*
            1.Creates a Vendor
            2.Updates the vendor with the given product transactions
            3.Tests the updated vendor with the expected products aggregation
         */
        String vendorName = "TestVendor";
        String productName = "TestProduct";
        double initialSpend = 100, totalSpend=initialSpend;

        //vendor creation
        Vendor vendor = new Vendor(vendorName, productName, initialSpend);

        //update vendor
        for(String[] product: productsTransactions){
            String currProductName = product[0];
            double currProductSpend= Double.parseDouble(product[1]);
            vendor.updateProductsOfVendor(currProductName, currProductSpend);
            totalSpend+=currProductSpend;
        }

        // Get the expected collections of product
        Map<String, Product> expectedProducts = getexpectedProducts(productsAggregate);

        //Test the vendor
        testVendor(vendor, vendorName, totalSpend, expectedProducts);
    }


    private void testVendor(Vendor vendor, String expectedVendorName, double expectedTotalSpend, Map<String, Product> expectedProducts){

        assertAll("testingVendorDetails",
                //test name
                ()-> assertEquals(expectedVendorName,  vendor.getName(), "Vendor name test failed"),
                // test totalspend
                ()-> assertEquals(expectedTotalSpend, vendor.getTotalSpend(), "Vendor total spend test failed"),
                // test whether all the products are same
                () -> ProductTest.testProductsCollection(expectedProducts.values(),vendor.getProducts().values()));
    }

    private Map<String, Product> getexpectedProducts(String[][] products){
        /*
        Creates a Map collection from List of Products
         */
        Map<String, Product> expectedProducts = new LinkedHashMap<>();
        for(String[] product: products){
            String productName = product[0];
            String totalSpend = product[1];
            expectedProducts.put(productName, new Product(productName, Double.parseDouble(totalSpend)));
        }
        return expectedProducts;
    }



    static class productsTestCase{
        /*
            Each TestCase should have the following
            1. Input (List of Transactions)
            2. Expected Output (Aggregation of Input Trasactions
         */

        private String[][] productsTransactions; // Input Transactions
        private String[][] productsAggregate;   // expected Output for the Input Transactions( Aggregation of Transactions)

        public productsTestCase(String[][] productsTransactions,String[][] productsAggregate){
            this.productsTransactions = productsTransactions;
            this.productsAggregate = productsAggregate;
        }

        public String[][] getProductsTransactions() {
            return productsTransactions;
        }

        public String[][] getProductsAggregate() {
            return productsAggregate;
        }
    }

}
