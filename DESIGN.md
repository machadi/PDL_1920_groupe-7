# Class diagram

The code part consists of:

* 8 classes including 1 interface

* 1 class implements the interface

* 1 inheriting classes

* 1 class enum 

* 4 "classic" java classes

### The interface  « Converter »:
It has two abstract methods implemented by ConverterToCsv (convertFromHtml and convertFromWikitext).

### The class enum « ExtractType»:
This class defines html and wikitext values as a possible value for data extraction.

### The class « WikipediaMatrix »:
The Main create a WikipediaMatrix and determines the setters of setUrlsMatrix()* and setExtractType().  Depending on the type (HTML and Wikitext) declared in setExtractType(), we use getConvertResult(). 
The method getConvertConvert(), Depending onhis type and for each UrlMatrix uses the UrlMatrix* class  UrlMatrix  using the setter setFilesMatrix()* to return a set of UrlMatrix.

###### *setUrlsMatrix': Creates a set of UrlMatrix.

###### *UrlMatrix: obtained upstream by setUrlsMatrix.

###### *SetFilesMatrix: Use convertFromHtml or convertFromWikitext and return a set of FileMatrix.

### class  « UrlMatrix »:

The Main gets a Set of FileMatrix obtained using setFilesMatrix().

### class « FileMatrix »:  saveCsv

For each FileMatrix obtained using the UrlMatrix class I save the file using the method saveCsv (String csvPath).

# UML Diagram

<img align="center" src="/src/img/ipdl_uml.png">

# Sequence diagram

<img align="center" src="/src/img/sequence1.png">
<img align="center" src="/src/img/sequence2.png">

