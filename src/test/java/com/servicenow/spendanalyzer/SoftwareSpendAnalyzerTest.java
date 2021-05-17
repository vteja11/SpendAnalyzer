package com.servicenow.spendanalyzer;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SoftwareSpendAnalyzerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private final ClassLoader classLoader = getClass().getClassLoader();

    private static final String[] inputTestFiles = {"softwarespendtestservicenow.csv",
                                                "softwarespendtest1.csv",
                                                "softwarespendtest2.csv",
                                                "softwarespendtest3.csv",
                                                "softwarespendtest4.csv",
                                                "softwarespendtest5.csv",
                                                "softwarespendtest6.csv"
    };
    private static final String[] expectedReports = {
            "Adobe $99,678.00\r\n  Creative Cloud $98,445.00\r\n  Illustrator $1,233.00\r\nAmazon $33,742.00\r\n  AWS $33,742.00\r\nBox $66,122.00\r\n  Box $66,122.00\r\nDocuSign $45,221.00\r\n  DocuSign $45,221.00\r\nMicrosoft $827,963.00\r\n  Azure $5,332.00\r\n  Office365 $822,631.00\r\n",
            "Adobe $99,678.00\r\n  Creative Cloud $98,445.00\r\n  Illustrator $1,233.00\r\nAmazon $77,161,704.00\r\n  AWS $33,742.00\r\n  Cloudwatch $75,678,787.00\r\n  ECS $5,354.00\r\n  EMR $34,546.00\r\n  Ec2 $56,565.00\r\n  Fargate $356,967.00\r\n  Lambda $7,867.00\r\n  ecs $987,876.00\r\nBox $66,122.00\r\n  Box $66,122.00\r\nDocuSign $45,221.00\r\n  DocuSign $45,221.00\r\nGoogle $13,673,872.00\r\n  Anthos $975.00\r\n  BigQuery $68,676.00\r\n  Cloud CDN $678,678.00\r\n  Cloud Functions $978.00\r\n  Cloud Run $5,455,657.00\r\n  Cloud SQL $6,767,886.00\r\n  Dataflow $669.00\r\n  Kubernetes Engine $676,788.00\r\n  Operations $23,565.00\r\nIBM $23,799,496.00\r\n  Cloud Pak for Data $221.00\r\n  Garage $87,821.00\r\n  Security Verify $21,228,765.00\r\n  Watson Assistant $2,322.00\r\n  Watson Discovery $983,923.00\r\n  Watson Explorer $1,412,662.00\r\n  Watson Studio $83,782.00\r\nMicrosoft $14,077,538.00\r\n  Azure $5,332.00\r\n  Microsoft 365. $99,497.00\r\n  Microsoft Edge $2,309,956.00\r\n  Microsoft Teams. $2,176,721.00\r\n  Office. $287,216.00\r\n  Office365 $822,631.00\r\n  OneDrive. $2,790,900.00\r\n  OneNote. $2,651,262.00\r\n  Outlook. $27,621.00\r\n  Windows. $2,906,402.00\r\nOracle $53,850,216.00\r\n  Autonomous Database $1,423.00\r\n  Digital Assistant $3,132,321.00\r\n  ERP $213,213.00\r\n  Gen 2 Cloud $2,323,242.00\r\n  MySQL Database $1,321,343.00\r\n  NoSQL Database $2,412,111.00\r\n  Soar $123,131.00\r\n  Visual Builder $44,323,432.00\r\nSAP $34,342,715.00\r\n  Analytics Cloud $3,243.00\r\n  Conversational AI $977,997.00\r\n  Data Intelligence $98,978.00\r\n  HANA $33,123,243.00\r\n  HANA Cloud $1,213.00\r\n  IQ $131,231.00\r\n  Ruum $6,679.00\r\n  SQL Anywhere $131.00\r\nSalesforce $1,557,002.00\r\n  App Builder $788,789.00\r\n  Dx $767,766.00\r\n  Flow $9.00\r\n  Object Creator $438.00\r\n",
            "Adobe $1,592,382.00\r\n  Creative Cloud $1,575,120.00\r\n  Illustrator $17,262.00\r\nAmazon $1,611,002,908.00\r\n  AWS $387,192.00\r\n  Cloudwatch $1,589,254,527.00\r\n  ECS $80,310.00\r\n  EMR $380,006.00\r\n  Ec2 $678,780.00\r\n  Fargate $8,210,241.00\r\n  Lambda $157,340.00\r\n  ecs $11,854,512.00\r\nBox $1,190,196.00\r\n  Box $1,190,196.00\r\nDocuSign $723,536.00\r\n  DocuSign $723,536.00\r\nGoogle $173,601,420.00\r\n  Anthos $16,575.00\r\n  BigQuery $824,112.00\r\n  Cloud CDN $8,144,136.00\r\n  Cloud Functions $20,538.00\r\n  Cloud Run $65,467,884.00\r\n  Cloud SQL $87,982,518.00\r\n  Dataflow $10,704.00\r\n  Kubernetes Engine $10,828,608.00\r\n  Operations $306,345.00\r\nIBM $437,502,939.00\r\n  Cloud Pak for Data $3,094.00\r\n  Garage $1,141,673.00\r\n  Security Verify $403,346,535.00\r\n  Watson Assistant $44,118.00\r\n  Watson Discovery $14,758,845.00\r\n  Watson Explorer $16,951,944.00\r\n  Watson Studio $1,256,730.00\r\nMicrosoft $192,082,498.00\r\n  Azure $101,308.00\r\n  Microsoft 365. $1,605,539.00\r\n  Microsoft Edge $34,649,340.00\r\n  Microsoft Teams. $30,474,094.00\r\n  Office. $4,882,672.00\r\n  Office365 $10,909,588.00\r\n  OneDrive. $39,187,512.00\r\n  OneNote. $29,163,882.00\r\n  Outlook. $220,968.00\r\n  Windows. $40,887,595.00\r\nOracle $899,138,283.00\r\n  Autonomous Database $19,922.00\r\n  Digital Assistant $43,852,494.00\r\n  ERP $2,984,982.00\r\n  Gen 2 Cloud $44,141,598.00\r\n  MySQL Database $17,177,459.00\r\n  NoSQL Database $33,769,554.00\r\n  Soar $3,693,930.00\r\n  Visual Builder $753,498,344.00\r\nSAP $518,356,858.00\r\n  Analytics Cloud $48,645.00\r\n  Conversational AI $17,603,946.00\r\n  Data Intelligence $1,781,604.00\r\n  HANA $496,848,645.00\r\n  HANA Cloud $23,047.00\r\n  IQ $1,968,465.00\r\n  Ruum $80,148.00\r\n  SQL Anywhere $2,358.00\r\nSalesforce $27,211,817.00\r\n  App Builder $12,620,624.00\r\n  Dx $14,587,554.00\r\n  Flow $135.00\r\n  Object Creator $3,504.00\r\n",
            "Adobe $2,314,788.00\r\n  Creative Cloud $2,264,235.00\r\n  Illustrator $50,553.00\r\nAmazon $2,386,182,753.00\r\n  AWS $764,455.00\r\n  Cloudwatch $2,346,042,397.00\r\n  ECS $149,912.00\r\n  EMR $863,650.00\r\n  Ec2 $1,640,385.00\r\n  Fargate $7,853,274.00\r\n  Lambda $220,276.00\r\n  ecs $28,648,404.00\r\nBox $1,785,294.00\r\n  Box $1,785,294.00\r\nDocuSign $949,641.00\r\n  DocuSign $949,641.00\r\nGoogle $313,466,087.00\r\n  Anthos $31,200.00\r\n  BigQuery $1,922,928.00\r\n  Cloud CDN $21,039,018.00\r\n  Cloud Functions $22,494.00\r\n  Cloud Run $109,113,140.00\r\n  Cloud SQL $162,429,264.00\r\n  Dataflow $22,077.00\r\n  Kubernetes Engine $18,273,276.00\r\n  Operations $612,690.00\r\nIBM $710,084,287.00\r\n  Cloud Pak for Data $5,525.00\r\n  Garage $2,898,093.00\r\n  Security Verify $636,862,950.00\r\n  Watson Assistant $51,084.00\r\n  Watson Discovery $28,533,767.00\r\n  Watson Explorer $39,554,536.00\r\n  Watson Studio $2,178,332.00\r\nMicrosoft $393,182,766.00\r\n  Azure $170,624.00\r\n  Microsoft 365. $2,316,702.00\r\n  Microsoft Edge $53,498,678.00\r\n  Microsoft Teams. $63,124,909.00\r\n  Office. $6,893,184.00\r\n  Office365 $26,412,430.00\r\n  OneDrive. $83,784,456.00\r\n  OneNote. $68,932,812.00\r\n  Outlook. $828,630.00\r\n  Windows. $87,220,341.00\r\nOracle $1,626,627,387.00\r\n  Autonomous Database $48,382.00\r\n  Digital Assistant $68,911,062.00\r\n  ERP $2,984,982.00\r\n  Gen 2 Cloud $65,050,776.00\r\n  MySQL Database $34,354,918.00\r\n  NoSQL Database $77,187,552.00\r\n  Soar $4,063,323.00\r\n  Visual Builder $1,374,026,392.00\r\nSAP $1,591,089,627.00\r\n  Analytics Cloud $74,589.00\r\n  Conversational AI $27,383,916.00\r\n  Data Intelligence $2,672,406.00\r\n  HANA $1,556,792,421.00\r\n  HANA Cloud $38,816.00\r\n  IQ $3,936,930.00\r\n  Ruum $187,012.00\r\n  SQL Anywhere $3,537.00\r\nSalesforce $39,632,357.00\r\n  App Builder $17,353,358.00\r\n  Dx $22,265,214.00\r\n  Flow $207.00\r\n  Object Creator $13,578.00\r\n",
            "Adobe $1,593,615.00\r\n  Creative Cloud $1,575,120.00\r\n  Illustrator $18,495.00\r\nAmazon $1,835,923,261.00\r\n  AWS $591,285.00\r\n  Cloudwatch $1,816,290,888.00\r\n  ECS $80,310.00\r\n  EMR $518,190.00\r\n  Ec2 $1,131,300.00\r\n  Fargate $5,354,505.00\r\n  Lambda $102,271.00\r\n  ecs $11,854,512.00\r\nBox $991,830.00\r\n  Box $991,830.00\r\nDocuSign $813,978.00\r\n  DocuSign $813,978.00\r\nGoogle $247,499,116.00\r\n  Anthos $9,750.00\r\n  BigQuery $1,648,224.00\r\n  Cloud CDN $9,501,492.00\r\n  Cloud Functions $9,780.00\r\n  Cloud Run $70,923,541.00\r\n  Cloud SQL $155,661,378.00\r\n  Dataflow $10,704.00\r\n  Kubernetes Engine $9,475,032.00\r\n  Operations $259,215.00\r\nIBM $419,288,000.00\r\n  Cloud Pak for Data $3,315.00\r\n  Garage $878,210.00\r\n  Security Verify $382,117,770.00\r\n  Watson Assistant $44,118.00\r\n  Watson Discovery $18,694,537.00\r\n  Watson Explorer $15,539,282.00\r\n  Watson Studio $2,010,768.00\r\nMicrosoft $284,937,886.00\r\n  Azure $95,976.00\r\n  Microsoft 365. $1,733,307.00\r\n  Microsoft Edge $26,272,126.00\r\n  Microsoft Teams. $45,711,141.00\r\n  Office. $5,744,320.00\r\n  Office365 $14,721,204.00\r\n  OneDrive. $58,637,628.00\r\n  OneNote. $50,373,978.00\r\n  Outlook. $580,041.00\r\n  Windows. $81,068,165.00\r\nOracle $1,445,493,902.00\r\n  Autonomous Database $21,345.00\r\n  Digital Assistant $53,249,457.00\r\n  ERP $4,264,260.00\r\n  Gen 2 Cloud $37,171,872.00\r\n  MySQL Database $27,748,203.00\r\n  NoSQL Database $36,181,665.00\r\n  Soar $1,477,572.00\r\n  Visual Builder $1,285,379,528.00\r\nSAP $486,149,172.00\r\n  Analytics Cloud $51,888.00\r\n  Conversational AI $18,581,943.00\r\n  Data Intelligence $1,187,736.00\r\n  HANA $463,725,402.00\r\n  HANA Cloud $13,343.00\r\n  IQ $2,493,389.00\r\n  Ruum $93,506.00\r\n  SQL Anywhere $1,965.00\r\nSalesforce $21,031,621.00\r\n  App Builder $11,043,046.00\r\n  Dx $9,980,958.00\r\n  Flow $171.00\r\n  Object Creator $7,446.00\r\n",
            "Adobe $72,111,387.00\r\n  Creative Cloud $71,274,180.00\r\n  Illustrator $837,207.00\r\nAmazon $54,757,206,056.00\r\n  AWS $24,940,691.00\r\n  Cloudwatch $53,731,938,770.00\r\n  ECS $3,640,720.00\r\n  EMR $24,113,108.00\r\n  Ec2 $39,256,110.00\r\n  Fargate $240,952,725.00\r\n  Lambda $5,790,112.00\r\n  ecs $686,573,820.00\r\nBox $53,029,844.00\r\n  Box $53,029,844.00\r\nDocuSign $31,202,490.00\r\n  DocuSign $31,202,490.00\r\nGoogle $9,367,112,597.00\r\n  Anthos $678,600.00\r\n  BigQuery $46,562,328.00\r\n  Cloud CDN $501,543,042.00\r\n  Cloud Functions $655,260.00\r\n  Cloud Run $3,726,213,731.00\r\n  Cloud SQL $4,608,930,366.00\r\n  Dataflow $474,321.00\r\n  Kubernetes Engine $465,630,144.00\r\n  Operations $16,424,805.00\r\nIBM $15,712,112,698.00\r\n  Cloud Pak for Data $158,015.00\r\n  Garage $62,265,089.00\r\n  Security Verify $13,926,069,840.00\r\n  Watson Assistant $1,581,282.00\r\n  Watson Discovery $690,713,946.00\r\n  Watson Explorer $970,498,794.00\r\n  Watson Studio $60,825,732.00\r\nMicrosoft $9,828,818,359.00\r\n  Azure $3,743,064.00\r\n  Microsoft 365. $73,019,405.00\r\n  Microsoft Edge $1,594,580,032.00\r\n  Microsoft Teams. $1,521,527,979.00\r\n  Office. $198,179,040.00\r\n  Office365 $617,018,411.00\r\n  OneDrive. $1,974,372,876.00\r\n  OneNote. $1,818,765,732.00\r\n  Outlook. $18,920,385.00\r\n  Windows. $2,008,691,435.00\r\nOracle $39,110,012,851.00\r\n  Autonomous Database $1,018,868.00\r\n  Digital Assistant $2,148,772,206.00\r\n  ERP $149,888,739.00\r\n  Gen 2 Cloud $1,651,825,062.00\r\n  MySQL Database $886,621,153.00\r\n  NoSQL Database $1,652,296,035.00\r\n  Soar $86,191,700.00\r\n  Visual Builder $32,533,399,088.00\r\nSAP $24,974,982,405.00\r\n  Analytics Cloud $2,360,904.00\r\n  Conversational AI $724,695,777.00\r\n  Data Intelligence $69,581,534.00\r\n  HANA $24,080,597,661.00\r\n  HANA Cloud $834,544.00\r\n  IQ $92,124,162.00\r\n  Ruum $4,695,337.00\r\n  SQL Anywhere $92,486.00\r\nSalesforce $1,080,619,860.00\r\n  App Builder $548,997,144.00\r\n  Dx $531,294,072.00\r\n  Flow $6,714.00\r\n  Object Creator $321,930.00\r\n",
            "Adobe $6,165,000.00\r\n  Illustrator $6,165,000.00\r\nAmazon $172,600,000.00\r\n  AWS $106,495,000.00\r\n  ECS $26,770,000.00\r\n  Lambda $39,335,000.00\r\nBox $330,610,000.00\r\n  Box $330,610,000.00\r\nDocuSign $226,105,000.00\r\n  DocuSign $226,105,000.00\r\nGoogle $68,369,360,000.00\r\n  Anthos $4,875,000.00\r\n  BigQuery $343,380,000.00\r\n  Cloud CDN $3,393,390,000.00\r\n  Cloud Functions $4,890,000.00\r\n  Cloud Run $27,278,285,000.00\r\n  Cloud SQL $33,839,430,000.00\r\n  Dataflow $3,345,000.00\r\n  Kubernetes Engine $3,383,940,000.00\r\n  Operations $117,825,000.00\r\nIBM $118,997,480,000.00\r\n  Cloud Pak for Data $1,105,000.00\r\n  Garage $439,105,000.00\r\n  Security Verify $106,143,825,000.00\r\n  Watson Assistant $11,610,000.00\r\n  Watson Discovery $4,919,615,000.00\r\n  Watson Explorer $7,063,310,000.00\r\n  Watson Studio $418,910,000.00\r\nMicrosoft $68,223,420,000.00\r\n  Azure $26,660,000.00\r\n  Microsoft 365. $497,485,000.00\r\n  Microsoft Edge $11,549,780,000.00\r\n  Microsoft Teams. $10,883,605,000.00\r\n  Office. $1,436,080,000.00\r\n  Office365 $1,948,885,000.00\r\n  OneDrive. $13,954,500,000.00\r\n  OneNote. $13,256,310,000.00\r\n  Outlook. $138,105,000.00\r\n  Windows. $14,532,010,000.00\r\nOracle $269,251,080,000.00\r\n  Autonomous Database $7,115,000.00\r\n  Digital Assistant $15,661,605,000.00\r\n  ERP $1,066,065,000.00\r\n  Gen 2 Cloud $11,616,210,000.00\r\n  MySQL Database $6,606,715,000.00\r\n  NoSQL Database $12,060,555,000.00\r\n  Soar $615,655,000.00\r\n  Visual Builder $221,617,160,000.00\r\nSAP $171,713,575,000.00\r\n  Analytics Cloud $16,215,000.00\r\n  Conversational AI $4,889,985,000.00\r\n  Data Intelligence $494,890,000.00\r\n  HANA $165,616,215,000.00\r\n  HANA Cloud $6,065,000.00\r\n  IQ $656,155,000.00\r\n  Ruum $33,395,000.00\r\n  SQL Anywhere $655,000.00\r\nSalesforce $7,785,010,000.00\r\n  App Builder $3,943,945,000.00\r\n  Dx $3,838,830,000.00\r\n  Flow $45,000.00\r\n  Object Creator $2,190,000.00\r\n",
    } ;

    private static String[][] testCases;

    @BeforeAll
    public static void updateTestCases(){
        int size = inputTestFiles.length;
        testCases = new String[size][2];
        for(int i=0;i<size;i++){
            testCases[i][0] = inputTestFiles[i];
            //As linux and Windows has different notations for new line
            testCases[i][1] = expectedReports[i].replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
        }
    }

    @BeforeEach
    public void setUpStreams() {
        // captures the console output
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }


    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    @DisplayName("Ensure correct handling of missing input file path")
    public void testMissingInputFilePath(){
        String[] inputs = new String[0];
        SoftwareSpendAnalyzer.main(inputs);
        assertEquals("Missing Input File Path", errContent.toString(), "Failed when input file is missing");
    }

    @Test
    @DisplayName("Ensure correct handling of the case when input path is invalid or file with the given name doesn't exists")
    public void testInvalidInputFilePath(){
        String[] inputs = {"file://c:user//doc//new"};
        SoftwareSpendAnalyzer.main(inputs);
        assertEquals("The System is unable to find the given path/file, please provide the absolute path of input file", errContent.toString(), "Failed when input file or path is incorrect");
    }


    @ParameterizedTest
    @MethodSource("provideArgsForGeneratingSpendReports")
    @DisplayName("Ensure correct reports are generated")
    public void testGeneratingSpendReports(String filename, String expectedOutput, String failMessage){
        String absFilePath = getFilePathFromResource(filename);
        String[] args = new String[]{absFilePath};
        SoftwareSpendAnalyzer.main(args);
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput, failMessage);
    }

    private static Stream<Arguments> provideArgsForGeneratingSpendReports(){
        return Arrays.stream(testCases).map( test-> Arguments.of(test[0], test[1], "Failed Generating Expected Test report for "+test[0]));
    }

    private String getFilePathFromResource(String filename){
        URL url = classLoader.getResource(filename);
        return url.getFile();
    }

}
