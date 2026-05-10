package edu.ttap.intmaps;
import java.util.Map;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class IntegerMaps {
    

    public static void reportCount(String path) throws IOException {
        char[] counts = new char[26];
        Scanner text = new Scanner(new File(path));
        while (text.hasNext()) {
            String next = text.nextLine();
            next.toLowerCase();
            for (int i = 0; i < next.length(); i++) {
                int c = ((int) next.charAt(i)) - 97;
                if (0 <= c & c <= 26) {
                    counts[i] ++;
                }
            }
        }

        for(int i = 0; i < 26; i++) {
            System.out.println((char)(i + 97) + ": " + counts[i]);
        }
    }
}