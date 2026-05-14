package edu.ttap.lootgenerator;

import java.io.File;
//import java.io.FileReader;
//import java.io.IOError;
import java.io.IOException;
//import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import java.util.Random;

/** a lootgenerator */
public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "src/data/large";

    public ArrayList<Monster> monsters;

    public ArrayList<Treasure> treasure;

    public ArrayList<Item> items;

    public ArrayList<Affix> prefixes;

    public ArrayList<Affix> suffixes;

    /** 
     * clears all lists and resets lootgenerator
     */
    public void clear() {
        monsters = new ArrayList<>();
        treasure = new ArrayList<>();
        items = new ArrayList<>();
        prefixes = new ArrayList<>();
        suffixes = new ArrayList<>();
    }

    /**
     * reads monsters into monsters from the scanner s
     * @param s a scanner
     */
    public void readMonsters(Scanner s) {
        String line = "";
        String[] monsInputs = null;
        while (s.hasNext()) {
            line = s.nextLine();
            monsInputs = line.split("\t");
            monsters.add(new Monster(monsInputs[0], monsInputs[3]));
        }
    }

    /**
     * reads treasure into treasure from scanner s
     * @param s a scanner
     */
    public void readTreasure(Scanner s) {
        String line = "";
        String[] treInputs = null;
        while (s.hasNext()) {
            line = s.nextLine();
            treInputs = line.split("\t");
            treasure.add(new Treasure(treInputs[0], treInputs[1], treInputs[2], treInputs[3]));
        }
    }

    /**
     * reads items into items from a scanner s
     * @param s a scanner
     */
    public void readItems(Scanner s) {
        String line = "";
        String[] statInputs = null;
        while (s.hasNext()) {
            line = s.nextLine();
            statInputs = line.split("\t");
            items.add(new Item(statInputs[0], statInputs[1], statInputs[2]));

        }
    }

    /**
     * reads prefixes into prefixes from a scanner pref
     * @param pref a scanner
     */
    public void readPrefixes(Scanner pref) {
        Random rng = new Random();
        String linePref = "";
        String[] prefInputs = null;
        while (pref.hasNext()) {
            linePref = pref.nextLine();
            prefInputs = linePref.split("\t");

            prefixes.add(new Affix(prefInputs[0], prefInputs[1], 
                                    rng.nextInt(Integer.parseInt(prefInputs[2]), 
                                    Integer.parseInt(prefInputs[3]) + 1)));
        }
    }

    /**
     * reads suffixes into suffixes from a scanner suf
     * @param suf a scanner
     */
    public void readSuffixes(Scanner suf) {
        Random rng = new Random();
        String lineSuf = "";
        String[] sufInputs = null;
        while (suf.hasNext()) {
            lineSuf = suf.nextLine();
            sufInputs = lineSuf.split("\t");
            suffixes.add(new Affix(sufInputs[0], sufInputs[1], 
                                    rng.nextInt(Integer.parseInt(sufInputs[2]), 
                                    Integer.parseInt(sufInputs[3]) + 1)));
        }
    }

    /**
     * picks a random monster from monsters
     * @return a random monster
     */
    public Monster pickMonster() {
        Random rng = new Random();
        return monsters.get(rng.nextInt(monsters.size()));
    }

    /**
     * returns a random treasure class for a given monster
     * @param mon a given monster
     * @return a random treasure class or an IllegalArgumentException if treasure is not found
     */
    public Treasure fetchTreasureClass(Monster mon) {
        for (Treasure t : treasure) {
            if (mon.treasureClass.equals(t.name)) {
                return t;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * returns a base item given a treasure t
     * @param t a given treasure
     * @return a base item or an IllegalArgumentException if item is not found
     */
    public Item generateBaseItem(Treasure t) {
        Random rng = new Random();
        String item = t.items[rng.nextInt(3)];
        Item found;

        for (Treasure v : treasure) {
            if (item.equals(v.name)) {
                return generateBaseItem(v);
            }
        }

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).name.equals(item)) {
                return items.get(i);
            }
        }

        throw new IllegalArgumentException();
    }

    /**
     * generates base stats for a given item
     * @param i a given item
     * @return the base stats (here, only defense) for that item
     */
    public Integer generateBaseStats(Item i) {
        Random rng = new Random();
        return rng.nextInt(Integer.parseInt(i.minac), Integer.parseInt(i.maxac) + 1);
    }

    /**
     * generates affixes randomly
     * @return an array of two affixes (a prefix and a suffix)
     */
    public Affix[] generateAffix() {
        Random rng = new Random();
        Affix[] affArr = new Affix[2];

        affArr[0] = new Affix("", "", 0);
        affArr[1] = new Affix("", "", 0);

        // prefix
        if (rng.nextInt(2) == 1) {
            Affix chosenPref = prefixes.get(rng.nextInt(prefixes.size()));
            affArr[0] = chosenPref;
        }

        // suffix
        if (rng.nextInt(2) == 1) {
            Affix chosenSuf = suffixes.get(rng.nextInt(suffixes.size()));
            affArr[1] = chosenSuf;
        }
        return affArr;
    }
    
    /**
     * the main function
     * 
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        LootGenerator lootGen = new LootGenerator();

        System.out.println("This program kills monsters and generates loot!");


        while (true) {
            lootGen.clear();

            Scanner monScanner = new Scanner(new File(DATA_SET + "/monstats.txt"));
            Scanner treScanner = new Scanner(new File(DATA_SET + "/TreasureClassEx.txt"));
            Scanner itemScanner = new Scanner(new File(DATA_SET + "/armor.txt"));
            Scanner prefScanner = new Scanner(new File(DATA_SET + "/MagicPrefix.txt"));
            Scanner sufScanner = new Scanner(new File(DATA_SET + "/MagicSuffix.txt"));

            lootGen.readMonsters(monScanner);
            lootGen.readTreasure(treScanner);
            lootGen.readItems(itemScanner);
            lootGen.readPrefixes(prefScanner);
            lootGen.readSuffixes(sufScanner);

            Monster curMon = lootGen.pickMonster();
            Item genItem = lootGen.generateBaseItem(lootGen.fetchTreasureClass(curMon));
            Affix[] genAffixes = lootGen.generateAffix();
            Integer baseStat = lootGen.generateBaseStats(genItem);

            Scanner userIn = new Scanner(System.in);

            System.out.println("Fighting " + curMon.name);
            System.out.println("You have slain " + curMon.name + "!");
            System.out.println(curMon.name + " dropped:");
            System.out.println(genAffixes[0].name + " " + genItem.name + " " + genAffixes[1].name);
            System.out.println("Defense: " + baseStat);
            if (!genAffixes[0].name.equals("")) {
                System.out.println(genAffixes[0].modStat + " " + genAffixes[0].mod);
            }

            if (!genAffixes[1].name.equals("")) {
                System.out.println(genAffixes[1].modStat + " " + genAffixes[1].mod);
            }

            while (true) {
                System.out.println("Continue fighting? [y/n]");
                String nextLn = userIn.nextLine().toLowerCase();
                if (nextLn.equals("n")) {
                    return;
                } else if (nextLn.equals("y")) {
                    break;
                }
            }
        }

    }
}
