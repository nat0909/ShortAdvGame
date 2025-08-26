

public class Goblin extends Creature {
    private int level;

    public Goblin(int level) {
        super(5 + level, 5, true); // Goblins have a max health of 5 + their level and speed of 5
        this.level = level;
    }

    public void performTurn() {
               
        // Move: close to target as possible

        // If target is in range (adjacent to goblin):)
            // Action + Bonus Action: double dagger attack with dagger (range 1)
        // Else if target is in range (up to 3 spaces away):
            // Action: attack sling (range 3)
        // Else
            // It isn't able to attack

    }

    public int daggerAttack(boolean bonusAttack) {  
        if(bonusAttack) {
            return 1 + level / 2; // Goblin's dagger attack when using a bonus action
        } 
        return 3 + level; // Goblin's dagger attack when using an action
    }

    public int slingAttack() {
        return level;
    }

    public int move(int position, int charPosition) { // Distance from char
        int distance = Math.abs(position - charPosition);        
        int maxMove = super.getSpeed();
        int move = 0;

        if(distance <= maxMove + 1) { // Distance includes the sqaure the char is on
            move = distance - 1; // Can't move onto the same square as the char
        } else {
            move = maxMove;
        }

        if(charPosition < position) { // Character is behind goblin
            return position - move;
        } else { // Character is in front of goblin
            return position + move;
        }
    }
}
