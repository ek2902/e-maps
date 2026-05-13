package edu.ttap.lootgenerator;

import java.io.File;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Random;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    public ArrayList<Monster> monsters;
    public ArrayList<Treasure> treasure;
    public ArrayList<Item> items;
    

    public void clear() {
        // result = new ArrayList<>();
    }

    public void readMonsters (Scanner s) {
        String line = "";
        String[] monsInputs = null;
        while (s.hasNext()) {
            line = s.nextLine();
            monsInputs = line.split("\t");
            monsters.add(new Monster(monsInputs[0], monsInputs[3]));
        }
    }

    public void readTreasure (Scanner s) {
        String line = "";
        String[] treInputs = null;
        while (s.hasNext()) {
            line = s.nextLine();
            treInputs = line.split("\t");
            treasure.add(new Treasure(treInputs[0], treInputs[1], treInputs[2], treInputs[3]));
        }
    }

    public void readItems (Scanner s) {
        String line = "";
        String[] statInputs = null;
        while (s.hasNext()) {
            line = s.nextLine();
            statInputs = line.split("\t");
            items.add(new Item(statInputs[0]));
        }
    }

    public void readPrefixes (Scanner pref) {
        String linePref = "";
        String[] affInputs = null;
        while (pref.hasNext()) {
            linePref = pref.nextLine();
            affInputs = linePref.split("\t");
        }
    }

    public Monster pickMonster() {
        Random rng = new Random();
        return monsters.get(rng.nextInt(monsters.size()));
    }

    public Treasure fetchTreasureClass(Monster mon) {
        for (Treasure t : treasure) {
            if (mon.treasureClass.equals(t.name)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    public Item generateBaseItem(Treasure t) {
        Random rng = new Random();
        Item item = new Item(t.items[rng.nextInt(3)]);
        for (Treasure v : treasure) {
            if (item.name.equals(v.name)) {
                return generateBaseItem(v);
            }
        }
        return item;
    }

    public void generateBaseStats(Item i) {
        Random rng = new Random();
    }

    public void generateAffix(Item i) {

    }
    
    public static void main(String[] args) throws IOException{
        LootGenerator lootGen = new LootGenerator();
        Scanner monScanner = new Scanner(new File(DATA_SET + "/monstats"));

        Monster curMon = lootGen.pickMonster();
        Item genItem = lootGen.generateBaseItem(lootGen.fetchTreasureClass(curMon));

        System.out.println("This program kills monsters and generates loot!");
        System.out.println("Fighting " + curMon.name);
        System.out.println("You have slain " + curMon.name + "!");
        System.out.println(curMon.name + " dropped:");
        System.out.println(genItem.prefix + " " + genItem.name + " " + genItem.suffix);
        System.out.println("Defense: " + genItem.baseStat);
        System.out.println(genItem.modStat + " " + genItem.mod);

    }
}
