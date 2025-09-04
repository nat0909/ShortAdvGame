import java.util.ArrayList;

/**
 * Stores information about a character in the game.
 * 
 */
public class Character extends Creature {
    private String name;
    private String charClass;
    private String race;

    private ArrayList<String> options;
    private int level;  // TODO: implement experience and leveling up

    public Character(String name, String charClass, String charRace, int health, int speed, ArrayList<String> options) {
        super(health, speed, false, 1);
        
        this.name = name;
        this.charClass = charClass;
        this.race = charRace;

        level = 1;
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public String getInfo() {
         return "Name: " + name + "\nClass: " + charClass + "\nRace: " + race + "\nHealth: " + super.getHealth() + "/" + super.getMaxHealth() + "\nSpeed: " + super.getSpeed() + "\nLevel: " + level; 
    }

    public String[] getOptions() {
        String[] optionArray = new String[options.size()];        
        for(int i = 0; i < options.size(); i++) {
            optionArray[i] = options.get(i);
        }
        return optionArray;
    }   

    public boolean setHealth(int changeInHealth, boolean tempHealth) {
        boolean alive = super.setHealth(changeInHealth, tempHealth);
        if (!alive) {
            System.out.println("--------------------------------------------------\n");
            System.out.println("Game Over! " + name + " has died.");
            System.out.println("\n--------------------------------------------------");
            return false; // Indicates death
        }
        return true; // Indicates survival
    }
}