package com.agile.diff;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

class HtmlAnalyzer {
    private static final Logger LOGGER = Logger.getLogger(HtmlAnalyzer.class.getName());

    private final File baseHtmlFile;
    private final File compareHtmlFile;

    HtmlAnalyzer(File baseHtmlFile, File compareHtmlFile) {
        this.baseHtmlFile = baseHtmlFile;
        this.compareHtmlFile = compareHtmlFile;
    }

    AnalyzerResult analyzeDifferencesById(String id) {
        Element baseReferenceElement = Finder.findElementById(baseHtmlFile, id).orElseThrow(
                () -> {
                    String message = "No element found";
                    LOGGER.severe(message);
                    return new RuntimeException(message);
                });
        Element compareCommonElement = Finder.findElementById(compareHtmlFile, id).orElseGet(
                () -> this.findCommonElementByReference(baseReferenceElement, compareHtmlFile));
        Element baseCommonElement = Finder.findElementById(baseHtmlFile, compareCommonElement.id()).orElseThrow(
                () -> {
                    String message = "No common element found";
                    LOGGER.severe(message);
                    return new RuntimeException(message);
                });

        Element baseHtmlElement = this.findPanelDivClass(baseCommonElement);
        Element compareHtmlElement = this.findPanelDivClass(compareCommonElement);

        return this.getDifference(baseHtmlElement, compareHtmlElement);
    }

    private AnalyzerResult getDifference(Element baseHtmlElement, Element compareHtmlElement) {
        Elements result = compareHtmlElement.children();
        result.removeAll(baseHtmlElement.children());
        String path = compareHtmlElement.parents().stream().map(Element::tagName).collect(Collectors.joining("->"));
        return new AnalyzerResult(result.toString(), path);
    }

    private Element findPanelDivClass(Element element) {
        return element.select("div[class=\"panel panel-default\"]").first();
    }

    private Element findCommonElementByReference(Element referenceElement, File htmlContent) {
        String referenceId = referenceElement.id();
        Optional<Element> element = Finder.findElementById(htmlContent, referenceId);
        return element.orElseGet(() -> this.findCommonElementByReference(this.findFirstParentElementWithId(referenceElement),
                htmlContent));
    }

    private Element findFirstParentElementWithId(Element element) {
        Element parent = element.parent();
        if ("".equals(parent.id())) {
            return this.findFirstParentElementWithId(parent);
        }
        return parent;
    }
}
