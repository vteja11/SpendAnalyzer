# SpendAnalyzer

## Dependencies
* Java (JDK >= 8)
* Maven 

## Environment Setup

* Download and Install JDK >=8
* Download Maven (https://mirror.nodesdirect.com/apache/maven/maven-3/3.8.1/binaries/apache-maven-3.8.1-bin.zip)
* Install Maven based on OS (https://maven.apache.org/install.html)

## Environment Verification
```
To Make sure your environment is set properly, type the following commands
Windows

$ Java -version
java version "1.8.0_231"

$ echo %JAVA_HOME%
C:\Program Files\Java\jdk1.8.0_231

Make sure that JAVA_HOME and Java points to the same version of JDK

$ mvn -v
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

After Navigating to root directory of project

$ mvn package
```

## Steps To Run
```
In the root directory of the project

$ java -jar target/SpendAnalyzer-1.0.jar <Input CSV File Absolute Path>

Example
$ java -jar target/SpendAnalyzer-1.0.jar C:\Users\vempa\Desktop\JavaProjects\SpendAnalyzer\src\test\resources\softwarespendtest6.csv

Use the CSV Files in src\test\resources for demo
```

## Assumptions
```
1. The Input File is Valid, i.e it has All the four required columns and amount is a number
```

## Limitations
```
1. The maximum aggregation possible is 1.7 x 10^308, i.e the total sum of the amount spent for a vendor should be less than 
   the given maximum. If the sum exceeds the given maximum, '∞' is shown in the report 
```

