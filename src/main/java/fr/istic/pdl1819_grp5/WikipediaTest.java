package fr.istic.pdl1819_grp5;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class WikipediaTest {


    public static void main(String[] args) throws IOException {
        //insert your url
        String url = "https://en.wikipedia.org/wiki/Comparison_of_Canon_EOS_digital_cameras";
        Set<UrlMatrix> urlMatrixSet = new HashSet<UrlMatrix>();

        urlMatrixSet.add(new UrlMatrix(url));

        WikipediaMatrix wikipediaMatrix = new WikipediaMatrix();
        //configuration WikipediaMatrix
        wikipediaMatrix.setExtractType(ExtractType.HTML); // Change to wikitext if you want this extraction
        wikipediaMatrix.setUrlsMatrix(urlMatrixSet);
        urlMatrixSet = wikipediaMatrix.getConvertResult();


        for (UrlMatrix urlMatrix : urlMatrixSet){
            Set<FileMatrix> fileMatrixSet = urlMatrix.getFileMatrix();
            for (FileMatrix fileMatrix : fileMatrixSet){
                System.out.println(fileMatrix.getText());
                System.out.println("-------------------------------------");
            }
        }

    }
}
