

public class Creature {
    private int maxHealth;
    private int speed;

    private int health;  

    public Creature(int maxHealth, int speed) {
        this.maxHealth = maxHealth;
        this.speed = speed;

        health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public int getSpeed() {
        return speed;
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
