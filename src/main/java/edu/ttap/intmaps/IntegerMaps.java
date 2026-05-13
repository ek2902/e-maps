package edu.ttap.intmaps;

import java.util.Map;
import java.util.TreeSet;
import java.util.Scanner;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

class LetterCounter {
    private static class Pair<K, V> {
        public K key;

        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    int arraySize = 200;
    ArrayList<Pair<Character, Integer>> map;

    public LetterCounter() {
        map = new ArrayList<>(arraySize);
        for (int i = 0; i < arraySize; i++) {
            map.add(null);
        }
    }
    
    public boolean hasKey(char ch) {
        int prefIndex = (int) ch % arraySize;
        if (map.get(prefIndex) == null) {
            return false;
        } else if (map.get(prefIndex).key.equals(ch)) {
            return true;
        } else {
            int index = prefIndex + 1;
            while (index != prefIndex) {
                if (index == arraySize) {
                    index = 0;
                }

                if (map.get(index) == null) {
                    return false;
                } else if (map.get(index).key.equals(ch)) {
                    return true;
                }
                
                index++;
            }
        }
        return false;
    }
    
    public void put(char ch, int v) {
        int prefIndex = (int) ch % arraySize;
        if (map.get(prefIndex) == null) {
            map.add(prefIndex, new Pair(ch, v));
        } else if (map.get(prefIndex).key.equals(ch)) {
            map.get(prefIndex).value = v;
        } else {
            int index = prefIndex + 1;
            while (index != prefIndex) {
                if (index == arraySize) {
                    index = 0;
                }
                if (map.get(index) == null) {
                    map.add(index, new Pair(ch, v));
                    return;
                        
                } else if (map.get(index).key.equals(ch)) {
                    map.get(index).value = v;
                    return;
                }
                index++;
                
            }
            System.err.println("Error: map is full");
        }
    }

    public int get(char ch) {
        int prefIndex = (int) ch % arraySize;
        if (map.get(prefIndex) == null) {
            throw new IllegalArgumentException();
        } else if (map.get(prefIndex).key.equals(ch)) {
            return map.get(prefIndex).value;
        } else {
            int index = prefIndex + 1;
            while (index != prefIndex) {
                if (index == arraySize) {
                    index = 0;
                }
                if (map.get(index) == null) {
                    throw new IllegalArgumentException();
                } else if (map.get(index).key.equals(ch)) {
                    return map.get(index).value;
                }
                index++;
            }
        }
        throw new IllegalArgumentException();
    }

    public void print(int index) {
        if (map.get(index) != null) {
            Pair<Character,Integer> p = map.get(index);
            System.out.println(p.key + " : " + p.value);
        }
    }
}

public class IntegerMaps {
    public static void reportCount(String path) throws IOException {
        // int[] counts = new int[26];
        // Scanner text = new Scanner(new File(path));
        // while (text.hasNext()) {
        //     String next = text.nextLine();
        //     next.toLowerCase();
        //     for (int i = 0; i < next.length(); i++) {
        //         int c = ((int) next.charAt(i)) - 97;
        //         if (0 <= c & c <= 26) {
        //             counts[c]++;
        //         }
        //     }
        // }

        // for(int i = 0; i < 26; i++) {
        //     System.out.println((char)(i + 97) + ": " + counts[i]);
        // }

        LetterCounter letterCounter = new LetterCounter();

        Scanner text = new Scanner(new File(path));
        while (text.hasNext()) {
            String next = text.nextLine();
            for (int i = 0; i < next.length(); i++) {
                Character c = next.charAt(i);
                int val = 0;
                if (letterCounter.hasKey(c)) {
                    val = letterCounter.get(c);
                }
                letterCounter.put(c, val + 1);
            }
        }

        for (int i = 0; i < letterCounter.arraySize; i++) { 
            letterCounter.print(i);
        }
    }

    public static int countChars(String path) throws IOException {
        Scanner text = new Scanner(new File(path));
        Set<Character> set = new TreeSet<>();
        while (text.hasNext()) {
            String next = text.nextLine();
            for (int i = 0; i < next.length(); i++) {
                set.add(next.charAt(i));
            }
        }
        for (Character c : set) {
            System.out.println(c + ": " + (int) c);
        }
        return set.size();
    }

    
}