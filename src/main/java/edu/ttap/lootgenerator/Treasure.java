package edu.ttap.lootgenerator;

/** a treasure ( you :^] ) */
public class Treasure {
    
    String name;
    
    String[] items = new String[3];
    
    /** 
     * treasure constructor
     * @param name name
     * @param item1 first item
     * @param item2 second item
     * @param item3 third item
    */
    public Treasure(String name, String item1, String item2, String item3) {
        this.name = name;
        this.items[0] = item1;
        this.items[1] = item2;
        this.items[2] = item3;
    }
}
