package com.agile.diff;

public class AnalyzerResult {
    private final String difference;
    private final String path;

    public AnalyzerResult(String difference, String path) {
        this.difference = difference;
        this.path = path;
    }

    public String getDifference() {
        return difference;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "AnalyzerResult{" +
                "difference='" + difference + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}
