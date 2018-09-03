package com.agile.diff;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

class FileReader {

    static File readFromFileSystem(String path) {
        return new File(path);
    }

    static File readFromClassPath(String path) throws URISyntaxException {
        URI fileUri = Objects.requireNonNull(FileReader.class.getClassLoader().getResource(path)).toURI();
        return readFromFileSystem(fileUri.getPath());
    }

    static String readContentFormFile(File file) throws FileNotFoundException {
        Scanner scanner = new Scanner(file);

        List<String> fileContents = new ArrayList<String>();

        while (scanner.hasNextLine()) {
            fileContents.add(scanner.nextLine());
        }

        scanner.close();


        return String.join("\n", fileContents);
    }
}
