# Installation

## Prerequisites

- Java version 1.8

- IDE Java like Eclipse or IntelliJ

## Installation procedure

- Clone the project, URL=*https://github.com/manuc352/PDL_1920_groupe-7.git*;
- Install __maven__;
- In the class wikiMain, change the path line 20 by your personal path to access your repertory inputdata;
- Same thing for the path line 28 with the repertory output

## Before running

- If you don't change anything, the extractor will extract tables which are extracted from wikipedia pages which corresponds of the list of URL written in the file *wikiurls.txt* in the repertory *inputdata*. 
- If you want to run the extractor on only one URL, you have to replace links in *wikiurls.txt* by your URL.   

## After running 

- The system has created a file for each table in each wikipedia page. 
- These CSV files are registered in directory *output*. CSV files from the *wikitext* extractor are in the directory *wikitext*, and files from html extractor are in the directory *html*.

## Errors 

-  If you have a compilation warning of type : "release version 5 not supported", 
go to File> Settings > Build, execution, deployment > Java Compiler, in target bytecode version, write 1.8.   
