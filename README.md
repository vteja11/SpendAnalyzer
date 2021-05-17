# SpendAnalyzer

## Dependencies

* Java (JDK >= 8)
* Maven 

## Environment Setup

* Download and Install Java with JDK >=8
* Download Maven (https://mirror.nodesdirect.com/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip)
* Install Maven based on OS (https://maven.apache.org/install.html)

## Environment Verification
```
To Make sure your environment is set properly, type the following commands

Windows OS Commands

> Java -version
java version "1.8.0_231"

> echo %JAVA_HOME%
C:\Program Files\Java\jdk1.8.0_231

Make sure that JAVA_HOME and Java points to the same version of JDK

> mvn -v
Apache Maven 3.8.1 (05c21c65bdfed0f71a2f2ada8b84da59348c4c5d)
Maven home: C:\tmp\apache-maven-3.8.1\bin\..
Java version: 1.8.0_231, vendor: Oracle Corporation, runtime: C:\Program Files\Java\jdk1.8.0_231\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"

```

## Steps To Build 
```
1. Download and extract this repository
2. Navigate to the root directory of project (you will be able to see "src" folder in the root directory)

> git clone https://github.com/vteja11/SpendAnalyzer.git
> cd SpendAnalyzer

To Build the project run the below command

> mvn package
```

## Steps To Run
```
> java -jar <path to SpendAnalyzer/target/SoftwareSpendReporter-1.0.jar file> <Input Csv File Path>

Example

In the root directory of the project (you will be able to see "src" folder in the root directory)e

> java -jar target/SoftwareSpendReporter-1.0.jar src\test\resources\softwarespendtest6.csv

You can use csv files in src/test/resources/
```

## Demo
```
>java -jar target/SoftwareSpendReporter-1.0.jar src\test\resources\softwarespendtestservicenow.csv
Adobe $99,678.00
  Creative Cloud $98,445.00
  Illustrator $1,233.00
Amazon $33,742.00
  AWS $33,742.00
Box $66,122.00
  Box $66,122.00
DocuSign $45,221.00
  DocuSign $45,221.00
Microsoft $827,963.00
  Azure $5,332.00
  Office365 $822,631.00
```

## Assumptions
```
1. The Input File is a Valid Csv File, i.e it has All the four required columns and Amount column is Number
```

## Limitations
```
1. The maximum aggregation possible is 1.7 x 10^308, i.e the total sum of the amount spent for a vendor should be less than 
   the given maximum. If the sum exceeds the given maximum, '∞' is shown in the report 
```

## Trouble Shooting 
```
1. Make sure java is properly installed by typing "java -version" in console 
2. Make sure JAVA_HOME is set to same jdk version as java 
3. Make sure maven is installed properly by typing "mvn -v"
4. If you have problems with maven try to download binary archives rather than source archives
   Example: Error: Could not find or load main class org.codehaus.plexus.classworlds.launcher.Launcher
   Download maven binary archives(Link provided in Environment Setup Section)
6. This project is tested in both Windows OS and Linux OS. If any OS related problems occur please search online 
```

