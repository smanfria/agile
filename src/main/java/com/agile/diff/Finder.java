package com.agile.diff;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class Finder {

    static Optional<Element> findElementById(File htmlFile, String targetElementId) {
        try {
            org.jsoup.nodes.Document doc = Jsoup.parse(
                    htmlFile,
                    "utf8",
                    htmlFile.getAbsolutePath());

            return Optional.ofNullable(doc.getElementById(targetElementId));

        } catch (IOException e) {
            return Optional.empty();
        }
    }

    public static Optional<Elements> findElementsByQuery(File htmlFile, String cssQuery) {
        try {
            Document doc = Jsoup.parse(
                    htmlFile,
                    "utf8",
                    htmlFile.getAbsolutePath());

            return Optional.ofNullable(doc.select(cssQuery));

        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
