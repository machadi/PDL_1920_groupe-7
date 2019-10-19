package fr.istic.pdl1819_grp5;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class wikiMain {

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {


        /*if(args.length<2){
            System.err.println("Usage : wiki -<inputFile> -<OuputDirectory>\n" +
                    "-inputFile : list of wikipedia's article title \n" +
                    "-ouputDirectory : destination directory\n");
            System.exit(0);
        }*/

        File urlsFile = new File("C:\\Users\\emman\\IdeaProjects\\PDL_1920_groupe-7\\inputdata\\wikiurls.txt");


        if(!urlsFile.exists() && !urlsFile.isDirectory()){
            System.err.println("input file not found");
            System.exit(0);
        }

        File directory = new File("C:\\Users\\emman\\IdeaProjects\\PDL_1920_groupe-7\\output");

        if(!directory.exists() || !directory.isDirectory()){
            System.err.println("Bad destination path");
            System.exit(0);
        }



        File htmlDir = new File(directory.getAbsoluteFile()+""+File.separator+"html");
        File wikitextDir = new File(directory.getAbsoluteFile()+""+File.separator+"wikitext");
        String url;
        String csvFileName;
        htmlDir.mkdir();
        wikitextDir.mkdir();

        WikipediaMatrix wiki = new WikipediaMatrix();

        // Html extraction
        wiki.setUrlsMatrix(getListofUrls(urlsFile));
        wiki.setExtractType(ExtractType.HTML);
        System.out.println("Extracting via html...");
        Set<UrlMatrix> urlMatrixSet = wiki.getConvertResult();
        //save files
        long execHtml = System.currentTimeMillis();//to measure time of execution

        int numberFileHtml=0; //Creation of the variable which contains the number of files
        for (UrlMatrix urlMatrix : urlMatrixSet){
            int i=0;
            url=urlMatrix.getLink();

            Set<FileMatrix> fileMatrices = urlMatrix.getFileMatrix();
            for (FileMatrix f : fileMatrices){
                csvFileName=mkCSVFileName(url.substring(url.lastIndexOf("/")+1,url.length()),i);
                f.saveCsv(htmlDir.getAbsolutePath()+File.separator+csvFileName);
                i++;
            }
            numberFileHtml+=i; //Number of files = current value of i
        }
        System.out.println("Extractor HTML created "+numberFileHtml+" files.");//affichage du nombre de tableaux extraits
        System.out.println("Temps d'exécution = "+(System.currentTimeMillis()-execHtml)+" ms");

        // Wikitext extraction
        wiki.setUrlsMatrix(getListofUrls(urlsFile));
        wiki.setExtractType(ExtractType.WIKITEXT);
        System.out.println("Extracting via wikitext...");
        urlMatrixSet = wiki.getConvertResult();
        //save files
        long execWiki = System.currentTimeMillis();//to measure time of execution
        int numberFileWiki=0; //Creation of the variable which contains the number of files
        for (UrlMatrix urlMatrix : urlMatrixSet){
            Set<FileMatrix> fileMatrices = urlMatrix.getFileMatrix();
            int i=0;
            url=urlMatrix.getLink();

            for (FileMatrix f : fileMatrices){
                csvFileName=mkCSVFileName(url.substring(url.lastIndexOf("/")+1,url.length()),i);
                f.saveCsv(wikitextDir.getAbsolutePath()+File.separator+csvFileName);
                i++;
            }
            numberFileWiki+=i; //Number of files = current value of i

        }
        System.out.println("Extractor Wikitext created "+numberFileWiki+" files.");//affichage du nombre de tableaux extraits
        System.out.println("Temps d'exécution = "+(System.currentTimeMillis()-execWiki)+" ms");

    }


    private static Set<UrlMatrix> getListofUrls(File inputFile) throws IOException {
        Set<UrlMatrix> urlsMatrix = new HashSet<UrlMatrix>();

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(inputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
        String url;
        String wurl;
        URL uneURL=null;
        while ((url = br.readLine()) != null) {
            wurl=BASE_WIKIPEDIA_URL+url;
            uneURL = new URL(wurl);
            HttpURLConnection connexion = (HttpURLConnection)uneURL.openConnection();
            if (connexion.getResponseCode() == HttpURLConnection.HTTP_OK){
                urlsMatrix.add(new UrlMatrix(wurl));
            }
        }
        return urlsMatrix;
    }
    private static String mkCSVFileName(String url, int n) {
        return url.trim() + "-" + n + ".csv";
    }
}
