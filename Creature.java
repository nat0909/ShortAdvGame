

public class Creature {
    private int maxHealth;
    private int health;
    private int speed;
    private boolean hostile; 

    public Creature(int maxHealth, int speed, boolean hostile) {
        this.maxHealth = maxHealth;
        health = maxHealth;
        this.speed = speed;
        this.hostile = hostile;
    }

    public int getHealth() {
        return health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean getHostile() {
        return hostile;
    }

    public boolean setHealth(int changeInHealth, boolean tempHealth) {
        int newHealth = health + changeInHealth;

        if (tempHealth || newHealth <= maxHealth)
            health = newHealth; // Prevents exceeding maxHealth unless it's temporary            
       
        if (health <= 0) {
            health = 0;
            return false; // Indicates death
        }

        return true; // Indicates survival
    }

}
