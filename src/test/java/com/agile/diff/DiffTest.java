package com.agile.diff;

import org.jsoup.Jsoup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;


public class DiffTest {

    private static final String HTML_ELEMENT_ID = "make-everything-ok-button";
    private File baseHtml;

    @Before
    public void setUp() throws Exception {
        baseHtml = FileReader.readFromClassPath("com/agile/diff/html/sample-0-origin.html");
    }

    @Test
    public void testSample1() throws URISyntaxException, FileNotFoundException {
        File compareHtml = FileReader.readFromClassPath("com/agile/diff/html/sample-1-evil-gemini.html");
        String expectedDiff = FileReader.readContentFormFile(FileReader.readFromClassPath("com/agile/diff/expected/sample-1-expected.html"));

        testEquality(compareHtml, expectedDiff);
    }

    @Test
    public void testSample2() throws URISyntaxException, FileNotFoundException {
        File compareHtml = FileReader.readFromClassPath("com/agile/diff/html/sample-2-container-and-clone.html");
        String expectedDiff = FileReader.readContentFormFile(FileReader.readFromClassPath("com/agile/diff/expected/sample-2-expected.html"));

        testEquality(compareHtml, expectedDiff);
    }

    @Test
    public void testSample3() throws URISyntaxException, FileNotFoundException {
        File compareHtml = FileReader.readFromClassPath("com/agile/diff/html/sample-3-the-escape.html");
        String expectedDiff = FileReader.readContentFormFile(FileReader.readFromClassPath("com/agile/diff/expected/sample-3-expected.html"));

        testEquality(compareHtml, expectedDiff);
    }

    @Test
    public void testSample4() throws URISyntaxException, FileNotFoundException {
        File compareHtml = FileReader.readFromClassPath("com/agile/diff/html/sample-4-the-mash.html");
        String expectedDiff = FileReader.readContentFormFile(FileReader.readFromClassPath("com/agile/diff/expected/sample-4-expected.html"));

        testEquality(compareHtml, expectedDiff);
    }

    private void testEquality(File compareHtml, String expectedDiff) {
        HtmlAnalyzer htmlAnalyzer = new HtmlAnalyzer(baseHtml, compareHtml);
        AnalyzerResult result = htmlAnalyzer.analyzeDifferencesById(HTML_ELEMENT_ID);

        Assert.assertEquals(normalize(expectedDiff), normalize(result.getDifference()));
    }

    private String normalize(String expectedDiff) {
        return Jsoup.parseBodyFragment(expectedDiff).toString().trim();
    }
}