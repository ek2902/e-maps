package edu.ttap.lootgenerator;

/**
 * a monster 
 */
public class Monster {
    String name;
    
    String treasureClass;
    
    /**
     * monster constructor
     * @param name monster name
     * @param treasureClass monster treasure class
     */
    public Monster(String name, String treasureClass) {
        this.name = name;
        this.treasureClass = treasureClass;
    }
}
