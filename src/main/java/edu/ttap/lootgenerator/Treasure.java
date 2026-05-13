package edu.ttap.lootgenerator;

public class Treasure {
    String name;
    String[] items;
    
    public Treasure(String name, String item1, String item2, String item3) {
        this.name = name;
        this.items[0] = item1;
        this.items[1] = item2;
        this.items[2] = item3;
    }
}
