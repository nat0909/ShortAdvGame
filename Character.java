


/**
 * Stores information about a character in the game.
 * 
 */
public class Character {

    private String name;
    private String charClass;
    private String race;

    private int maxHealth;
    private int speed;

    private int health;
    private int level;

    public Character(String name, String charClass, String charRace, int health, int speed) {
        this.name = name;
        this.charClass = charClass;
        this.race = charRace;

        this.maxHealth = health;
        this.speed = speed;

        this.health = health;
        level = 1;
    }

    public String getInfo() {
         return "Name: " + name + "\nClass: " + charClass + "\nRace: " + race; 
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
    }

    public int getLevel() {
        return level;
    }

    public void setHealth(int changeInHealth, boolean tempHealth) {
        health += changeInHealth;

        if (health > maxHealth && !tempHealth) {
            health = maxHealth; // Ensures health does not exceed the char's max health unless it's temporary
        } else if (health <= 0) {
            System.out.println(name + " has died.");
        }
    }

}