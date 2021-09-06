package org.shinybot;

import org.shinybot.utility.readFiles;

import java.io.File;
import java.io.FileNotFoundException;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File("owner_id.txt");
        System.out.println(readFiles.getFirstLineFromFile(file));
    }
}
