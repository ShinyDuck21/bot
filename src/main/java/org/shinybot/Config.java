package org.shinybot;

import org.shinybot.utility.readFiles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Config {
    public static String getToken() throws FileNotFoundException {
        File tokenFile = new File("token.txt");

        Scanner reader = new Scanner(tokenFile);
        String token = reader.next();
        return token;
    }

    public static String getOwnerId() throws FileNotFoundException {
        File ownerIdFile = new File("owner_id.txt");

        return readFiles.getFirstLineFromFile(ownerIdFile);
    }

    public static String getPrefix() throws FileNotFoundException {
        File prefixFile = new File("prefix.txt");

        return readFiles.getFirstLineFromFile(prefixFile);
    }
}
