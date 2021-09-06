package org.shinybot.utility;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class readFiles {
    private static Logger LOGGER = LoggerFactory.getLogger(error.class);

    public static String getFirstLineFromFile(@NotNull File file) throws FileNotFoundException {
        try {
            Scanner reader = new Scanner(file);
            String result = reader.next();
            return result;
        } catch (FileNotFoundException e) {
            LOGGER.error("{} IS NOT FOUND. PLEASE CREATE OR RENAME A FILE CALLED {}", file.getName(), file.getName());
            return null;
        }
    }
}
