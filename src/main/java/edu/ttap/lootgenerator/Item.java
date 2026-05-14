package edu.ttap.lootgenerator;

/**
 * an item
 */
public class Item {
    String name = "";

    String minac = "";
    
    String maxac = "";

    /**
     * item constructor
     * @param name name of item
     * @param minac minimum for stat
     * @param maxac maximum for stat
     */

    public Item(String name, String minac, String maxac) {
        this.name = name;

        this.minac = minac;
        this.maxac = maxac;
    }
}
