package com.agile.diff;

import java.io.File;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        String baseHtmlPath = args[0];
        String compareHtmlPath = args[1];

        LOGGER.info(baseHtmlPath);
        LOGGER.info(compareHtmlPath);

        String id = "make-everything-ok-button";

        if (args.length == 3) {
            id = args[2];
            LOGGER.info(id);
        }

        File baseHtmlFile = FileReader.readFromFileSystem(baseHtmlPath);
        File compareHtmlFile = FileReader.readFromFileSystem(compareHtmlPath);

        HtmlAnalyzer htmlAnalyzer = new HtmlAnalyzer(baseHtmlFile, compareHtmlFile);
        AnalyzerResult result = htmlAnalyzer.analyzeDifferencesById(id);

        LOGGER.info(result.toString());

    }


}
