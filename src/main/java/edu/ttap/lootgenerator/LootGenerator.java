package edu.ttap.lootgenerator;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ttap.maps.AssociationList.Pair;

import java.util.Random;

public class LootGenerator {
    /** The path to the dataset (either the small or large set). */
    private static final String DATA_SET = "data/small";

    public ArrayList<String> result;

    public void clear() {
        result = new ArrayList<>();
    }

    public void read (Scanner s) {
        while (s.hasNext()) {
            result.add(s.nextLine());
        }
    }

    public String pickMonster() {
        Random rng = new Random();
        return result.get(rng.nextInt(result.size()));
    }

    public void fetchTreasureClass() {

    }

    public void generateBaseItem() {

    }

    public void generateBaseStats() {

    }

    public void generateAffix() {

    }
    
    public static void main(String[] args) {
        System.out.println("This program kills monsters and generates loot!");
        // TOOD: Implement me!
    }
}
