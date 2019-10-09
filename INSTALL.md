# Installation

## Prerequisites

- Java version 1.8

- IDE Java like Eclipse or IntelliJ

## installation procedure

- Clone the project, URL=*https://github.com/manuc352/PDL_1920_groupe-7*;
- Install __maven__;
- In the class wikiMain, change the path line 20 by your personnal path to access of your repertory Input;
- Same thing for the path line 28 with the repertory output

##Running options

- Automatically, the extractor will extract a big quantity of tables which are on a list of URL which are written in the file wikiurls.txt in the repertory inputdata. 
If you want to run the extractor on only one URL, you have to replace links in wikiurls.txt by your URL.   

##After running 

- The system has created a file for each table in the URL wikipedia. 
Files which are created thanks to wikitext code will be in the repertory wikitext, and files which are create thanks to HTML code will be in the repertory HTML

##Errors 

-  If you have a compilation warning of type : "release version 5 not supported", 
go to File> Settings > Build, execution, deployment > Java Compiler, in target bytecode version, write 1.8.   
