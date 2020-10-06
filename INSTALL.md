# Installation

## Prerequisites

- Java version 1.8

- IDE Java like Eclipse or IntelliJ

## Installation procedure

- Clone the project, URL=*https://github.com/machadi/PDL_2021_groupe-9.git*;
- Open the project as an existing __Maven__ project on IntelliJ/Eclipse;
- In the class wikiMain, change the path at line 49 by your personal path to access your repertory inputdata;
- Same thing for the path at line 57 with the repertory output

### Open an existing Maven project on IntelliJ

- From the main menu, select File | Open.
    Alternatively, click Open or Import on the welcome screen.
- In the dialog that opens, select the pom.xml file of the project you want to open. Click OK. 
- In the dialog that opens, click Open as Project.

IntelliJ IDEA opens and syncs the Maven project in the IDE.

You will find more information on the website of IntelliJ : https://www.jetbrains.com/help/idea/maven-support.html

### Open an existing Maven project on Eclipse

- Click File > Import.
- Type Maven in the search box under Select an import source:
- Select Existing Maven Projects. Click Next.
- Click Browse and select the folder that is the root of the Maven project (probably contains the pom.xml file)
- Click Finish.

## Before running

- If you don't change anything, the extractor will extract tables that are extracted from wikipedia pages which corresponds of the list of URL written in the file *wikiurls.txt* in the repertory *inputdata*. 
- If you want to run the extractor with only one URL, you have to replace links in *wikiurls.txt* by your URL.   

## After running 

- The system has created a file for each table in each wikipedia page. 
- These CSV files are registered in directory *output*. CSV files from the *wikitext* extractor are in the directory *wikitext*, and files from html extractor are in the directory *html*.

## Errors 

-  If you have a compilation warning of type : "release version 5 not supported", 
go to File> Settings > Build, execution, deployment > Java Compiler, in target bytecode version, write 1.8.   
