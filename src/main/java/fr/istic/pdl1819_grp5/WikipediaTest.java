package fr.istic.pdl1819_grp5;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class WikipediaTest {


    public static void main(String[] args) throws IOException {


        //insert your url
        String url = "https://fr.wikipedia.org/wiki/Tableau_synoptique";

        Set<UrlMatrix> urlMatrixSet = new HashSet<UrlMatrix>();

        urlMatrixSet.add(new UrlMatrix(url));

        WikipediaMatrix wikipediaMatrix = new WikipediaMatrix();
        //configuration WikipediaMatrix
        wikipediaMatrix.setExtractType(ExtractType.WIKITEXT); // Change to wikitext if you want this extraction
        wikipediaMatrix.setUrlsMatrix(urlMatrixSet);
        urlMatrixSet = wikipediaMatrix.getConvertResult();


        for (UrlMatrix urlMatrix : urlMatrixSet){
            Set<FileMatrix> fileMatrixSet = urlMatrix.getFileMatrix();
            int csvNumber =1;
            for (FileMatrix fileMatrix : fileMatrixSet){
            	 Csv file = new Csv(ExtractType.WIKITEXT+"tableau"+csvNumber);
            	 file.saveCsvFile(fileMatrix);
            	 csvNumber++;
             
               
            }
        }
        
        
       

    }
}
