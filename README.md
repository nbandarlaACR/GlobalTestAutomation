# UI and API Testing Automation framework for ACR Connect & AILAB

## Framework Structure 
### Dependencies 
This api automation framework depends on following 
external libraries. 

```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>4.3.1</version>
</dependency>

<dependency>
    <groupId>org.testng</groupId>
    <artifactId>testng</artifactId>
    <version>6.14.3</version>
</dependency>

<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>3.141.59</version>
</dependency>

```

### Framework Project Structure Diagram
```
|-reports                        #  all the generated test execution reports are here 
|-pom.xml                        #  project object model file for the maven software
|-testng.xml                     #  TestNG configuration files for the test structures and groupings 
|-src
   |---test
         |----java               #  all the java source files needs to stored in this folder 
                |-[+]base        #  java class package, all the commons class wil be stored here 
                |-[+]pages       #  java class package, all page object class will be stored here 
                |-[+]testcase    #  java class package, all test class will be stored here 
                |-[+]utility     #  java class package, all the utility class will be stored here 
         |----resources          #  resources folder, json files, xml files, excel files and reports 
                |-[d]payloads    #  all the json files used in tests are stored here 
                |-[d]xml         #  all the xml files used in tests are stored here 
|-.gitignore                     #  git ignore config file 
|-READMD.md                      #  you are currently viewing this file 
```

## Framework Module Relationships
Please refer to the diagram below for the relationships between different modules 
that are available in this framework. 
![screenshot](/images/framework.png)

## Pre-requisites
* Download and install Chrome or Firefox browser  ( viewing report )
* Download and install JDK v1.8 + 
* Download and install Apache Maven v3.0+
* Download and install Git v2.0+ 

## Usage Recommendation
This framework contains multiple test contexts that are used in CI pipeline such as
Smoke, Regression, and End-to-End. Please refer to following diagram for more details. 
![screenshot](/images/utilization.png)


## How to run Tests 
All the test triggering is done through maven commands, this framework supports multiple different types of 
test executions such as smoke, regression, and end-to-end on different possible environment such as QA, Staging, and 
UAT. 
#### Executing specific tests 
If you would like to execute all of the tests that are stated on testng.xml file 
```shell script
mvn test 
```

If you would like to execute a specific test that are stated on testng.xml file
```shell script
mvn test -Dtestof="smoke_api"
```
If you would like to execute a specific test on specific environment ( default=UAT ) 
```shell script
mvn test -Dtestof="test1" -Denv="Staging"
```


## How to get Report 
All the test execution reports are avaialbe as HTML report on following folder after test execution 
```
 report
   |--[HTML] reports
```
"# GlobalTestAutomation" 
