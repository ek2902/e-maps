package edu.ttap;

import java.io.IOException;

import edu.ttap.intmaps.IntegerMaps;
import edu.ttap.maps.SubstitutionCipher;

/**
 * The driver for our lab on lists.
 */
public class Main {

    
    /**
     * The main entry point for the program.
     * @param args the command-line arguments
     */
    public static void main(String[] args) throws IOException {
        // SubstitutionCipher.main(args);
        IntegerMaps.reportCount("src/data/pg2600.txt");
    }
}
