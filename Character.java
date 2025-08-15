


/**
 * Stores information about a character in the game.
 * 
 */
public class Character extends Creature {

    private String name;
    private String charClass;
    private String race;

    private int level;

    public Character(String name, String charClass, String charRace, int health, int speed) {
        super(health, speed);
        
        this.name = name;
        this.charClass = charClass;
        this.race = charRace;

        level = 1;
    }

    public String getInfo() {
         return "Name: " + name + "\nClass: " + charClass + "\nRace: " + race; 
    }

    public int getLevel() {
        return level;
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