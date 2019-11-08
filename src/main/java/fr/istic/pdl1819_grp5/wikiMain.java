package fr.istic.pdl1819_grp5;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class wikiMain {

    /**
     * mise en place des loggers
     *
     * @param args
     * @throws IOException
     */

    private static Logger logger = Logger.getLogger("wikiMain.class");

    public static void loggerstart() {
        try {
            logger.setLevel(Level.ALL);
            logger.log(Level.INFO, "EXTRACTIONG OF TABLES FROM WIKIPEDIA PAGES");
            FileHandler fichier = new FileHandler("LoggerFile.xml");
            logger.addHandler(fichier);
        } catch (IOException e) {
            logger.log(Level.INFO, "logger did not load");
        }


    }

    public static void main(String[] args) throws IOException {

        loggerstart();


        //echanges du logger



        /*if(args.length<2){
            System.err.println("Usage : wiki -<inputFile> -<OuputDirectory>\n" +
                    "-inputFile : list of wikipedia's article title \n" +
                    "-ouputDirectory : destination directory\n");
            System.exit(0);
        }*/


        File urlsFile = new File("C:\\Users\\ocean\\IdeaProjects\\PDL_1920_groupe-7\\inputdata\\wikiurls.txt");


        if (!urlsFile.exists() && !urlsFile.isDirectory()) {
            logger.log(Level.INFO, "input file note found");
            System.exit(0);
        }

        File directory = new File("C:\\Users\\ocean\\IdeaProjects\\PDL_1920_groupe-7\\output");


        if (!directory.exists() || !directory.isDirectory()) {
            logger.log(Level.INFO, "bad destination path");
            System.exit(0);
        }
        //calling of fountable function
        foundtable();

        File htmlDir = new File(directory.getAbsoluteFile() + "" + File.separator + "html");
        File wikitextDir = new File(directory.getAbsoluteFile() + "" + File.separator + "wikitext");
        String url;
        String csvFileName;
        htmlDir.mkdir();
        wikitextDir.mkdir();

        WikipediaMatrix wiki = new WikipediaMatrix();

        // Html extraction
        wiki.setUrlsMatrix(getListofUrls(urlsFile));
        wiki.setExtractType(ExtractType.HTML);
        logger.log(Level.INFO, "Extracting via html...");
        //System.out.println("Extracting via html...");
        Set<UrlMatrix> urlMatrixSet = wiki.getConvertResult();


        //save files
        long execHtml = System.currentTimeMillis();//to measure time of execution
        ArrayList<Integer> extractedHTML = new ArrayList<Integer>();
        ArrayList<String> urls = new ArrayList<String>();

        int numberFileHtml = 0; //Creation of the variable which contains the number of files
        for (UrlMatrix urlMatrix : urlMatrixSet) {
            int i = 0;
            url = urlMatrix.getLink();
            urls.add(url);
            //System.out.println(url);

            Set<FileMatrix> fileMatrices = urlMatrix.getFileMatrix();
            for (FileMatrix f : fileMatrices) {
                //extraction des tableaux de type wikitext en format csv
                csvFileName = mkCSVFileName(url.substring(url.lastIndexOf("/") + 1, url.length()), i);
                f.saveCsv(htmlDir.getAbsolutePath() + File.separator + csvFileName);
                i++;
            }
            extractedHTML.add(i);
            numberFileHtml += i; //Number of files = current value of i
        }
        System.out.println("Extractor HTML created " + numberFileHtml + " files.");//affichage du nombre de tableaux extraits
        System.out.println("Temps d'exécution = " + (System.currentTimeMillis() - execHtml) + " ms");

        // Wikitext extraction
        ArrayList<String> urlsWikitext = new ArrayList<String>();
        ArrayList<Integer> extractedWikitext = new ArrayList<Integer>();
        wiki.setUrlsMatrix(getListofUrls(urlsFile));
        wiki.setExtractType(ExtractType.WIKITEXT);
        logger.log(Level.INFO, "Extracting via wikitext...");
        //System.out.println("Extracting via wikitext...");
        urlMatrixSet = wiki.getConvertResult();
        //save files
        long execWiki = System.currentTimeMillis();//to measure time of execution
        int numberFileWiki = 0; //Creation of the variable which contains the number of files
        for (UrlMatrix urlMatrix : urlMatrixSet) {
            Set<FileMatrix> fileMatrices = urlMatrix.getFileMatrix();
            int i = 0;
            url = urlMatrix.getLink();
            //System.out.println(url);
            urlsWikitext.add(url);

            for (FileMatrix f : fileMatrices) {
                //extraction des tableaux de type wikitext en format csv
                csvFileName = mkCSVFileName(url.substring(url.lastIndexOf("/") + 1, url.length()), i);
                f.saveCsv(wikitextDir.getAbsolutePath() + File.separator + csvFileName);
                i++;
            }
            extractedWikitext.add(i);
            numberFileWiki += i; //Number of files = current value of i

        }
        System.out.println("Extractor Wikitext created " + numberFileWiki + " files.");//affichage du nombre de tableaux extraits
        System.out.println("Temps d'exécution = " + (System.currentTimeMillis() - execWiki) + " ms");

        saveStats(urls, extractedHTML, extractedWikitext, urlsWikitext);
    }


    private static void saveStats(ArrayList<String> urls, ArrayList<Integer> extractedHTML, ArrayList<Integer> extractedWikitext, ArrayList<String> urlsWikitext) {
        FileMatrix fm = new FileMatrix("stats.csv");
        fm.setText("URL,Tables_extracted_with_Html,Tables_extracted_with_Wikitext" + "\n");
        ArrayList<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < urls.size(); i++) {
            String currentUrl = urls.get(i);
            int index = urlsWikitext.indexOf(currentUrl);
            result.add(extractedWikitext.get(index));
        }
        for (int i = 0; i < urls.size(); i++) {
            fm.
                    append(urls.get(i) + "," + extractedHTML.get(i) + "," + result.get(i) + "\n");
        }

        try {
            fm.saveCsv("C:\\Users\\ocean\\IdeaProjects\\PDL_1920_groupe-7\\output\\statsExtractor.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Set<UrlMatrix> getListofUrls(File inputFile) {
        logger.entering(wikiMain.class.getName(), "getListofUrls", inputFile);
        try {
            Set<UrlMatrix> urlsMatrix = new HashSet<UrlMatrix>();

            BufferedReader br = null;
            br = new BufferedReader(new FileReader(inputFile));
            String BASE_WIKIPEDIA_URL = "https://en.wikipedia.org/wiki/";
            String url;
            String wurl;
            URL uneURL = null;
            while ((url = br.readLine()) != null) {
                wurl = BASE_WIKIPEDIA_URL + url;
                uneURL = new URL(wurl);
                HttpURLConnection connexion = (HttpURLConnection) uneURL.openConnection();
                if (connexion.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    urlsMatrix.add(new UrlMatrix(wurl));
                }
            }
            logger.exiting(wikiMain.class.getName(), "getListofUrls", urlsMatrix);
            return urlsMatrix;

        } catch (Exception e) {
            logger.log(Level.SEVERE, "ERR_INTERNET_DISCONNECTED");
            return null;


        }


    }


    //function which find tables by criteria
    public static void foundtable() throws IOException {

        logger.log(Level.INFO, "entering of the function which find tables by criteria");
        logger.log(Level.INFO, "Loading..........");
        File urlsFile = new File("C:\\Users\\ocean\\IdeaProjects\\PDL_1920_groupe-7\\inputdata\\wikiurls.txt");

        FileWriter wikitablestat = new FileWriter("C:\\Users\\ocean\\IdeaProjects\\PDL_1920_groupe-7\\output\\Wkitable_stat.csv");


        Set<UrlMatrix> liturls = getListofUrls(urlsFile);
        char separator = ',';
        int comptbox = 0, comptnav = 0, comptlinks = 0, comptother = 0;
        int totalbox = 0, totalnav = 0, totallinks = 0, totalothers = 0;
        wikitablestat.write("url" + separator + "box" + separator + "nav" + separator + "links" + separator + "others");
        wikitablestat.write("\n");
        for (UrlMatrix url : liturls) {
            try {
                Document doc = Jsoup.connect(url.getLink()).get();
                Elements tables = doc.getElementsByTag("table");
                for (int i = 0; i < tables.size(); i++) {
                    if (tables.get(i).className().contains("box")) {
                        comptbox++;

                    }
                    if (tables.get(i).select(".nv-") != null) {
                        comptnav++;


                    }
                    if (tables.get(i).select(".nowraplinks").first() != null) {
                        comptlinks++;

                    }
                    if (tables.get(i).className().contains("wikitable") && (tables.get(i).selectFirst("[class*=\"nv-\"]") == null || tables.get(i).selectFirst("[class*=\"box\"]") == null
                            || !tables.get(i).className().contains("box") || !tables.get(i).className().contains("nv-"))) {

                        comptother++;
                    }


                }
            } catch (IOException e) {
                e.printStackTrace();

            }
            totalbox = totalbox + comptbox;
            totalnav = totalnav + comptnav;
            totallinks = totallinks + comptlinks;
            totalothers = totalothers + comptother;
            String scomptbox = Integer.toString(comptbox);
            String scomptnav = Integer.toString(comptnav);
            String scomptlinks = Integer.toString(comptlinks);
            String scomptother = Integer.toString(comptother);

            String urlmodif = "";
            if (url.getLink().contains(",")) {


                for (int i = 0; i < url.getLink().length(); i++) {

                    if (url.getLink().charAt(i) != ',') {
                        urlmodif += url.getLink().charAt(i);

                    }
                }
                wikitablestat.write(urlmodif + separator + scomptbox + separator + scomptnav + separator + scomptlinks + separator + scomptother);
                wikitablestat.write("\n");
            } else {
                wikitablestat.write(url.getLink() + separator + scomptbox + separator + scomptnav + separator + scomptlinks + separator + scomptother);
                wikitablestat.write("\n");

            }


            comptbox = 0;
            comptlinks = 0;
            comptnav = 0;
            comptother = 0;


        }
        wikitablestat.write("TOTAL" + separator + totalbox + separator + totalnav + separator + totallinks + separator + totalothers);
        wikitablestat.close();
        logger.log(Level.INFO, "end of searching table by criteria");
    }


    private static String mkCSVFileName(String url, int n) {
        return url.trim() + "-" + n + ".csv";
    }
}