package fr.istic.pdl1819_grp5;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;


import static org.junit.jupiter.api.Assertions.*;

class ConverterToCsvTest {

    @Test
    void convertHtmlTable() throws IOException {
        ConverterToCsv c=new ConverterToCsv();
        Document doc= Jsoup.parse("<table><tr><th>Month</th><th>Savings</th><th rowspan=\"3\">Savings for holiday!</th></tr><tr><td>January</td><td>$100</td></tr><tr><td>February</td><td>$80</td></tr></table>\n");
        Element table = doc.getElementsByTag("table").first();
        assertTrue(FileUtils.contentEquals(new File("src/test/1 rowspan/csv.csv"),c.convertHtmlTable(table).saveCsv("src/test/1 rowspan")));
        doc= Jsoup.parse("<table><tr><th>Company</th><th>Contact</th><th>Country</th></tr><tr><td>Alfreds Futterkiste</td><td>Maria Anders</td><td>Germany</td></tr><tr><td>Centro comercial Moctezuma</td><td>Francisco Chang</td><td>Mexico</td></tr><tr><td>Ernst Handel</td><td>Roland Mendel</td><td>Austria</td></tr><tr><td>Island Trading</td><td>Helen Bennett</td><td>UK</td></tr><tr><td>Laughing Bacchus Winecellars</td><td>Yoshi Tannamuri</td><td>Canada</td></tr><tr><td>Magazzini Alimentari Riuniti</td><td>Giovanni Rovelli</td><td>Italy</td></tr></table>\n");
        table = doc.getElementsByTag("table").first();
        ConverterToCsv c2=new ConverterToCsv();
        assertTrue(FileUtils.contentEquals(new File("src/test/2 simple/csv.csv"),c2.convertHtmlTable(table).saveCsv("src/test/2 simple")));
        doc= Jsoup.parse("<table border=\"1\"><!-- First row --><tr><td>1</td><td colspan=\"2\">2 and 3</td><td>4</td></tr><!-- Second row --><tr><td rowspan=\"3\">5, 9 and 13</td><td>6</td><td>7</td><td>8</td></tr><!-- Third row --><tr><td>10</td><td>11</td><td>12</td></tr><!-- Fourth row --><tr><td colspan=\"3\">14, 15 and 16</td></tr></table>");
        table = doc.getElementsByTag("table").first();
        ConverterToCsv c3=new ConverterToCsv();
        assertTrue(FileUtils.contentEquals(new File("src/test/3 rowspan/csv.csv"),c3.convertHtmlTable(table).saveCsv("src/test/3 rowspan")));
    }
}