# Success rate of the extractor 

Of the 336 URLs we have:

HTML extractor creates 1627 files
Wikitext extractor creates 1553 files

Also, for given URLs we have:

- 0 extracted table
- less table extracted than there is on the page
- less table extracted in wikitext than in html
- less table extracted in html than in wikitext

# Criticism of the existing

By resuming the project of last year, we noticed that there were several tests that did not work, 
such as veriteterrain tests which led us to analyze the code of the ConverterToCsv class where the convertFromHtml method and the convertFromWikitext method 
where we found that for a given url the order of extraction of the arrays are not ordered, which changes the content of the csv files extracted each time the code is executed.
In fact the convertFromHtml method and the convertFromWikitext method used an unordered collection type which is HashSet, 
so we replaced it with LinkedHashSet which is a well ordered collection.

In conclusion, we find that the HTML extractor is better than the Wikitext extractor. However, 
on some URLs the Wikitext extractor is still better suited than the HTML extractor.
