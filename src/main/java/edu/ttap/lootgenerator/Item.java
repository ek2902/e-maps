package edu.ttap.lootgenerator;

public class Item {
    String name;
    String prefix;
    String suffix;
    String mod;

    
    int baseStat;
    int affStat;
    int modStat;

    public Item (String name) {
        this.name = name;
    }

    public Item (String name, int baseStat, int affStat) {
        this.name = name;

        this.baseStat = baseStat;
        this.affStat = affStat;
    }
}
