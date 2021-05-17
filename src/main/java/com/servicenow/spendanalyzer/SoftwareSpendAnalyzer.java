package com.servicenow.spendanalyzer;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import com.servicenow.spendanalyzer.models.Vendor;


public class  SoftwareSpendAnalyzer {
    private static final int vendorIndex= 1 , productIndex=2, amountIndex=3;


    public static void generateSpendReport(String path, boolean hasHeaders){
        Map<String, Vendor> vendors = analyzeCsvFile(path, hasHeaders);
        printSpendReport(vendors);
    }

    public static Map<String, Vendor> analyzeCsvFile(String path, boolean hasHeaders){
        Map<String, Vendor> vendors = new TreeMap<>();
        try {
            String row = "";
            BufferedReader csvReader = new BufferedReader(new FileReader(path));
            if(hasHeaders) {
                csvReader.readLine();
            }
            while((row = csvReader.readLine())!=null){
                String[] cols = row.split(",");
                String vendorName = cols[vendorIndex];
                String productName = cols[productIndex];
                double amountSpend = Double.parseDouble(cols[amountIndex]);
                updateVendorSpend(vendorName, productName, amountSpend, vendors);
            }
        }
        catch (FileNotFoundException e){
            System.err.print("The System is unable to find the given path/file, please provide the absolute path of input file");
        }
        catch (Exception e){
            System.err.println(e);
        }
        return vendors;
    }

    private static void updateVendorSpend(String vendorName, String productName, double amount, Map<String, Vendor> vendors){
        Vendor vendor;
        if(vendors.containsKey(vendorName)){
            vendor = vendors.get(vendorName);
            vendor.updateProductsOfVendor(productName, amount);
        }
        else{
            vendor = new Vendor(vendorName, productName, amount);
            vendors.put(vendorName, vendor);
        }
    }

    private static void printSpendReport(Map<String, Vendor> vendors){
        for(String vendorName: vendors.keySet()){
            Vendor vendor = vendors.get(vendorName);
            vendor.printReport();
        }
    }

    public static void main(String[] args){
        if(args.length<1) {
            System.err.print("Missing Input File Path");
        }
        else {
            String inputFilePath = args[0];
            generateSpendReport(inputFilePath, true);
        }
    }
}

