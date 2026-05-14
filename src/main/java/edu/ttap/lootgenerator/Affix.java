package edu.ttap.lootgenerator;

/** an affix */
public class Affix {

    String name;

    String mod;

    int modStat;

    /**
     * affix constructor
     * 
     * @param name name
     * @param mod modifier name
     * @param modStat modifier value
     */
    public Affix(String name, String mod, int modStat) {
        this.name = name;
        this.mod = mod;
        this.modStat = modStat;
    }
}
