package edu.ttap.maps;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.io.IOException;

/**
 * A substitution cipher is a simple encryption scheme that associates each
 * letter of the alphabet with a different letter.
 */
public class SubstitutionCipher {
    /**
     * Creates a substitution cipher by reading a mapping of characters from the given
     * file. Each mapping of the file should be of the form "a b", where 'a' is mapped to
     * 'b' in the cipher. We require 
     * @param filename the name of the file containing the mapping
     * @return the cipher as a mapping between characters
     */
    public static Map<Character, Character> createCipher(String filename) throws IOException {
        AssociationList<Character, Character> cipher = new AssociationList<Character, Character>();
        Scanner text = new Scanner(new File(filename));
        String curLine;
        while (text.hasNext()) {
            curLine = text.nextLine();
            cipher.put(curLine.charAt(0), curLine.charAt(2));
        }        
        return cipher;
    }

    /**
     * Determines whether the given mapping is a valid substitution cipher. A cipher is
     * valid if (a) it maps every letter of the alphabet (a–z) and (b) it is a bijection,
     * i.e., no two letters map to the same letter (so that we can roundtrip encode/decode
     * a message without loss of fidelity).
     * @param cipher
     * @return true iff the given mapping is a valid substitution cipher
     */
    public static boolean isValidCipher(Map<Character, Character> cipher) {
        Set<Character> keys = cipher.keySet();
        Set<Character> values = new HashSet();
        values.addAll(cipher.values());
        if (values.size() == 26 & keys.size() == 26) {
            for (Character c : values) {
                if (!(97 <= (int) c & (int) c <= 122)) {
                    return false;
                }
            }
            for (Character c : keys) {
                if (!(97 <= (int) c & (int) c <= 122)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Given a valid substitution cipher, produces the inverse mapping, which
     * can be used to decode the encoded massage. For example, if the cipher
     * maps 'a' to 'b', then the inverse mapping should map 'b' to 'a'.
     * @param cipher the cipher to invert
     * @return the inverse mapping of the given cipher
     */
    public static Map<Character, Character> invertCipher(Map<Character, Character> cipher) {
        Map<Character, Character> inverted = new HashMap<>();

        for (Character c : cipher.keySet()) {
            inverted.put(cipher.get(c), c);
        }

        return inverted;
    }

    /**
     * Translates the given string using the provided mapping.
     * @param s the string to translate
     * @param mapping the mapping to use
     * @return the translated string
     */
    public static String translate(String s, Map<Character, Character> mapping) {
        String translated = "";

        for (int i = 0; i < s.length(); i++) {
            Character gotten = mapping.get(s.charAt(i));
            char toAdd = s.charAt(i);
            if (gotten != null) {
                toAdd = mapping.get(s.charAt(i));
            }
            translated += toAdd;
        }
        return translated;
    }

    /**
     * The main driver for the substitution cipher program.
     * @param args the driver's command-line arguments
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            System.err.println(
                "Usage: java SubstitutionCipher <encode|decode> <cipherfile> <filename>");
            System.exit(1);
        }

        Scanner text = new Scanner(new File("src/data/" + args[2]));
        String s = "";
        while (text.hasNextLine()) {
            s += text.nextLine();
        }

        Map<Character, Character> cipher = createCipher("src/data/" + args[1]);

        if (!isValidCipher(cipher)) {
            System.err.println("Cipher is not valid");
            System.exit(1);
        }

        if (args[0].equals("decode")) {
            cipher = invertCipher(cipher);
        }

        System.out.println(translate(s, cipher));
    }
}
