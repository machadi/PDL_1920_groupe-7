# Wikipedia matrix

Wikipedia matrix is a table extractor via two ways : HTML and WIKITEXT.
Its goal is to extract as many relevant tables from wikipedia links.
the output format is csv.

## Getting Started
clone https://github.com/jocelindegni/PDL_1819_grp5.git for development and testing purposes

## Prerequisites
* java IDE
* [Maven](https://maven.apache.org/) - Dependency Management to get all dependencies of this project.
 
## Running the tests
ConvertToCsvTest is in charge of testing the extractor.
two methods: 
* convertFromHtml : test if the csv produced are corrects.
* wikitextVsHtml : Verifies that all relevant tables have been extracted. 

## Deployment
run mvn package.

## demo
[see the demo](https://www.youtube.com/watch?v=OQXkuxb6DE4)

## Versioning
For the versions available, see the [tags on this repository](https://github.com/jocelindegni/PDL_1819_grp5/releases). 

## Built With
* [bliki](http://www.dropwizard.io/1.0.2/docs/) -  java parser library for converting Wikipedia wikitext notation to HTML.
* [jsoup](https://jsoup.org/) -Java library for working with real-world HTML.
* [Maven](https://maven.apache.org/) - Dependency Management.


## Authors
* Jocelin DEGNI
* Yann ATTOUBE
* Anderson KONAN
* Kiko DAGNOGO
